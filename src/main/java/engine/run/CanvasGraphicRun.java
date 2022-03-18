package engine.run;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.field.ColumnViewer;
import engine.game.GameControler;
import engine.game.GameViewer;
import engine.game.KPDPControler;
import engine.game.parameters.GameParameters;
import engine.influence.viewer.InfluenceCardContainerViewer;
import engine.influence.viewer.InfluenceCardViewer;
import engine.objective.viewer.ObjectiveCardViewer;
import engine.teams.TeamViewer;
import engine.teams.controler.PlayerConsoleControler;
import engine.teams.controler.RandomAIControler;
import engine.util.ListViewerContainer;

public class CanvasGraphicRun {
	static JFrame w;
	static Panel frame;
	public static void main(String[] args) {
		//Create windows
		w=new JFrame("2KPDP");
		frame=new Panel();
		w.setSize(600,600);
		frame.setLayout(new BoxLayout(frame,BoxLayout.Y_AXIS));
		w.add(frame);
		w.setVisible(true);
		
		//Création de la partie
		GameParameters parameters=new GameParameters();
		parameters.nb_team=4;
		GameControler game=KPDPControler.startGame(parameters);
		
		//On associe des controleurs aux équipes
		ListViewerContainer<TeamViewer> teams=game.getTeams();
		teams.get(0).setControler(new PlayerConsoleControler(CanvasGraphicRun::fieldDrawer,CanvasGraphicRun::handDrawer,CanvasGraphicRun::playerDrawer, CanvasGraphicRun::titleDrawer));
		for(int i=1; i<teams.size(); i++) teams.get(i).setControler(new RandomAIControler());
		
		//On lance la partie
		game.launch();
		
		while(!game.isFinished()) {
			game.nextTurn();
			System.out.println("TotalTour : "+game.getTotalTurns()+"; Tour : "+game.getTurn()+"; Manche : "+game.getRound());
		}
		System.out.println("Fin de la partie!");
		System.out.println("Le gagnant est le joueur "+game.idOfTeam(game.calculateWinner())+" ! ");
	}
	private static void titleDrawer(String title) {
		frame.removeAll();
		Label titlel=new Label(title);
		titlel.setMaximumSize(new Dimension(50,100));
		frame.add(titlel);
		w.revalidate();
	}
	private static void fieldDrawer(GameViewer game,TeamViewer team) {
		frame.add(createField(game));
		w.revalidate();
	}
	private static void handDrawer(GameViewer game,TeamViewer team) {
		JPanel hand=createHand(team.getHand(), game);
		frame.add(hand);
		w.revalidate();
	}
	private static void playerDrawer(GameViewer game,TeamViewer team) {
	}
	public static Panel createField(GameViewer game) {
		Panel field=new Panel();
		field.setLayout(new BoxLayout(field,BoxLayout.X_AXIS));
		for(ColumnViewer c : game.getField()) field.add(createColumn(c, game));
		return field;
	}
	public static JPanel createHand(InfluenceCardContainerViewer h, GameViewer game) {
		JPanel hand=new JPanel();
		hand.setLayout(new BoxLayout(hand,BoxLayout.X_AXIS));
		for(InfluenceCardViewer ic : h)hand.add(createCard(ic, game));
		
		return hand;
	}
	public static Panel createColumn(ColumnViewer col,GameViewer game) {
		Panel column=new Panel();
		column.setLayout(new BoxLayout(column,BoxLayout.Y_AXIS));
		column.add(createCard(col.getObjectiveCard(), game));
		for(InfluenceCardViewer ic : col)column.add(createCard(ic, game));
		
		return column;
	}
	public static Panel createCard(ObjectiveCardViewer oc,GameViewer game) {
		Panel card=new Panel();
		card.setLayout(new FlowLayout());
		Label name=new Label(KPDPControler.DOMAIN_REGISTRY.getId(oc.getDomain())); name.setBackground(Color.WHITE);
		Label points=new Label(Float.toString(oc.getObjective())); points.setBackground(Color.WHITE);
		card.add(name); card.add(points);

		card.setBackground(intToColor(oc.getDomain().hashCode()%30));
		card.setPreferredSize(new Dimension(1,1));
		
		return card;
	}
	public static Component createCard(InfluenceCardViewer ic, GameViewer game) {
		JPanel header=new JPanel();
		header.setLayout(new FlowLayout());
		Label name=new Label(KPDPControler.CARDTYPE_REGISTRY.getId(ic.getType())); name.setBackground(Color.WHITE);
		Label points=new Label(Float.toString(ic.getPoints())); points.setBackground(Color.WHITE);
		header.add(name); header.add(points);

		JPanel card=new JPanel();
		card.add(header);
		card.add(Box.createVerticalGlue());
		card.add(Box.createVerticalGlue());
		card.setLayout(new BoxLayout(card, 1));
		card.setBackground(intToColor(game.getTeams().indexOfViewing(ic.getTeam())));
		card.setBorder(BorderFactory.createLineBorder(Color.black,5));
		return card;
	}
	public static Color intToColor(int i) {
		switch(i) {
			case 0: return Color.RED;
			case 1: return Color.BLUE;
			case 2: return Color.GREEN;
			case 3: return Color.YELLOW;
			case 4: return Color.MAGENTA;
			case 5: return Color.WHITE;
			case 6: return Color.BLACK;
			case 7: return Color.CYAN;
			case 8: return Color.LIGHT_GRAY;
			case 9: return Color.ORANGE;
			case 10: return Color.PINK;
			case 11: return Color.GRAY;
			case 12: return new Color(100,0,255);
			default: return Color.getHSBColor(((float)i-12)/20f, 0.5f, 1f);
		}
	}
}

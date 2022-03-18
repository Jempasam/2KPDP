package engine.run;

import engine.field.ColumnViewer;
import engine.game.GameControler;
import engine.game.GameViewer;
import engine.game.KPDPControler;
import engine.game.parameters.GameParameters;
import engine.influence.InfluenceStat;
import engine.influence.viewer.InfluenceCardViewer;
import engine.kpdpevents.InfluenceEffectEvent;
import engine.objective.viewer.ObjectiveCardViewer;
import engine.teams.TeamViewer;
import engine.teams.controler.PlayerConsoleControler;
import engine.teams.controler.RandomAIControler;
import engine.util.ListViewerContainer;

public class DebugOnePlayerRun {
	public static void main(String[] args) {
		
		//Création de la partie
		GameParameters parameters=new GameParameters();
		parameters.nb_team=4;
		GameControler game=KPDPControler.startGame(parameters);
		
		//On associe des controleurs aux équipes
		ListViewerContainer<TeamViewer> teams=game.getTeams();
		teams.get(0).setControler(new PlayerConsoleControler(DebugOnePlayerRun::fieldDrawer,DebugOnePlayerRun::handDrawer,DebugOnePlayerRun::playerDrawer,DebugOnePlayerRun::titleDrawer));
		for(int i=1; i<teams.size(); i++) teams.get(i).setControler(new RandomAIControler());
		
		//On associe l'event listener pour les effets de carte
		KPDPControler.INFLUENCE_EVENTS.register(DebugOnePlayerRun::effectListener);
		
		//On lance la partie
		while(!game.isFinished()) {
			game.launch();
			System.out.println("TotalTour : "+game.getTotalTurns()+"; Tour : "+game.getTurn()+"; Manche : "+game.getRound());
		}
	}
	public static void effectListener(InfluenceEffectEvent event) {
		System.out.println("---"+event+"---");
		fieldDrawer(new GameControler(event.getContext().getGame()), new TeamViewer(event.getContext().getTeam()));
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void fieldDrawer(GameViewer game,TeamViewer team) {
		System.out.println("-Terrain-");
		int i=0;
		for(ColumnViewer col : game.getField()) {
			System.out.print(i+":");
			objectiveDrawer(col.getObjectiveCard());
			for(InfluenceCardViewer ic : col) {
				influenceDrawer(ic, game.getTeams().indexOfViewing(ic.getTeam()));
			}
			System.out.print("\n");
			i++;
		}
	}
	private static void titleDrawer(String title) {
		System.out.println("----- "+title+" -----");
	}
	private static void playerDrawer(GameViewer game,TeamViewer team) {
		System.out.print("Score: "+game.calculateGainScores().get(team)+" : ");
		System.out.println(team.getGains());
		System.out.println("Discard Size: "+team.getDiscardPile().size()+" | Stockpile Size: "+team.getStockPile().size());
	}
	public static void influenceDrawer(InfluenceCardViewer ic, int style) {
		if(ic.isIgnored())System.out.print("<:");
		else if(!ic.isRevealed())System.out.print(" #");
		else if(ic.getState()==InfluenceStat.ACTIVATED)System.out.print("°:");
		else if(ic.getState()==InfluenceStat.INACTIVE)System.out.print("-|");
		else System.out.print("  ");
		System.out.print(ope(style)+KPDPControler.CARDTYPE_REGISTRY.getId(ic.getType())+" "+ic.getPoints()+fer(style));
		if(ic.isIgnored())System.out.print(":>");
		else if(!ic.isRevealed())System.out.print("#");
		else if(ic.getState()==InfluenceStat.ACTIVATED)System.out.print(":°");
		else if(ic.getState()==InfluenceStat.INACTIVE)System.out.print("|-");
		else System.out.print("  ");
	}
	public static void objectiveDrawer(ObjectiveCardViewer oc) {
		System.out.print("["+oc.getDomain().toString().substring(0, 6)+" "+oc.getObjective()+"]");
	}
	private static void handDrawer(GameViewer game,TeamViewer team) {
		System.out.println("-Hand-");
		int i=0;
		for(InfluenceCardViewer ic : team.getHand()) {
			System.out.print(" "+i+":");
			influenceDrawer(ic, game.getTeams().indexOfViewing(ic.getTeam()));
			i++;
		}
		System.out.print("\n");
	}
	private static String ope(int style) {
		switch (style){
		case 0 : return "(";
		case 1 : return "[";
		case 2 : return "{";
		case 3 : return "|";
		case 4 : return "((";
		case 5 : return "[[";
		case 6 : return "{{";
		case 7 : return "||";
		case 8 : return "(|";
		case 9 : return "[|";
		default : return "{|";
		}
	}
	private static String fer(int style) {
		String ope=ope(style);
		StringBuilder ret=new StringBuilder();
		for(int i=ope.length()-1; i>=0; i--) {
			switch(ope.charAt(i)) {
				case '(' : ret.append(')');
				case '[' : ret.append(']');
				case '{' : ret.append('}');
				default : ret.append(ope.charAt(i));
			}
		}
		return ret.toString();
	}
}

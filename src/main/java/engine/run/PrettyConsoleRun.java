package engine.run;

import engine.field.ColumnViewer;
import engine.game.GameControler;
import engine.game.GameViewer;
import engine.game.KPDPControler;
import engine.game.parameters.GameParameters;
import engine.influence.InfluenceStat;
import engine.influence.viewer.InfluenceCardViewer;
import engine.kpdpevents.InfluenceEffectEvent;
import engine.kpdpevents.gameevent.EndGameEvent;
import engine.kpdpevents.gameevent.GameEvent;
import engine.kpdpevents.gameevent.NextRoundEvent;
import engine.kpdpevents.gameevent.NextTurnEvent;
import engine.kpdpevents.gameevent.PlayCardEvent;
import engine.objective.viewer.ObjectiveCardViewer;
import engine.teams.TeamViewer;
import engine.teams.controler.APLAIControler;
import engine.teams.controler.ASLAIControler;
import engine.teams.controler.BSLAIControler;
import engine.teams.controler.PlayerConsoleControler;
import engine.teams.controler.RandomAIControler;
import engine.util.ListViewerContainer;

/**
 * Permet de jouer une partie dans la console
 * @author Samuel DEMONT
 *
 */
public class PrettyConsoleRun {
	
	public static int print_count=0;
	
	public static int getPrintId() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		print_count++;
		return print_count-1;
	}
	
	public static void main(String[] args) {
		
		//Création de la partie
		GameParameters parameters=new GameParameters();
		parameters.nb_team=4;
		GameControler game=KPDPControler.startGame(parameters);
		
		//On associe des controleurs aux équipes
		ListViewerContainer<TeamViewer> teams=game.getTeams();
		teams.get(0).setControler(new PlayerConsoleControler(PrettyConsoleRun::fieldDrawer,PrettyConsoleRun::handDrawer,PrettyConsoleRun::playerDrawer, PrettyConsoleRun::titleDrawer));
		for(int i=1; i<teams.size(); i++) teams.get(i).setControler(new BSLAIControler());
		
		//->for(TeamViewer tv : game.getTeams()) tv.setControler(new RandomAIControler());
		
		//On associe l'event listener pour les effets de carte et les actions de jeu
		KPDPControler.INFLUENCE_EVENTS.register(PrettyConsoleRun::effectListener);
		KPDPControler.GAME_EVENTS.register(PrettyConsoleRun::gameListener);
		
		//On lance la partie
		game.launch();
		
		while(!game.isFinished()) {
			game.nextTurn();
			System.out.println("TotalTour : "+game.getTotalTurns()+"; Tour : "+game.getTurn()+"; Manche : "+game.getRound());
		}
		System.out.println("Fin de la partie!");
		System.out.println("Le gagnant est le joueur "+game.idOfTeam(game.calculateWinner())+" ! ");
	}
	
	public static void effectListener(InfluenceEffectEvent event) {
		System.out.println("<"+getPrintId()+">");
		fieldDrawer(new GameControler(event.getContext().getGame()), new TeamViewer(event.getContext().getTeam()));
		System.out.println("Activation : "+event);
	}
	public static void gameListener(GameEvent event) {
		System.out.println("<"+getPrintId()+">");
		fieldDrawer(event.getGame(), event.getGame().getActualTeam());
		System.out.println(event);
	}
	private static void titleDrawer(String title) {
		for(int i=0; i<5 ;i++)System.out.println();
		System.out.println("<"+getPrintId()+">");
		System.out.println("\tO>---------< "+title+" >---------<O");
	}
	private static void fieldDrawer(GameViewer game,TeamViewer team) {
		boolean label=true;
		
		//DRAW LINE
		for(ColumnViewer c : game.getField())System.out.print("|---------------|");
		System.out.print("\n");
				
		//DRAW NUMBER
		int i=0;
		for(ColumnViewer c : game.getField()) {
			System.out.print("|"+sidepad(Integer.toString(i),' ',15)+"|");
			i++;
		}
		System.out.print("\n");
		
		//DRAW OBJECTIVE
		for(ColumnViewer c : game.getField()) {
			System.out.print("|");
			objectiveDrawer(c.getObjectiveCard());
			System.out.print("|");
		}
		System.out.print("\n");
		
		//DRAW LINE
		for(ColumnViewer c : game.getField())System.out.print("|---------------|");
		System.out.print("\n");
		
		//DRAW INFLUENCE
		for(int y=0;label; y++) {
			label=false;
			for(int x=0; x<game.getField().size(); x++)
			{
				ColumnViewer c=game.getField().get(x);
				if(y<c.size()) {
					InfluenceCardViewer ic=c.get(y);
					System.out.print("|");
					influenceDrawer( ic, game.idOfTeam(ic.getTeam()) );
					System.out.print("|");
					label=true;
				}
				else System.out.print("|               |");
			}
			System.out.print("\n");
		}
	}
	private static void playerDrawer(GameViewer game,TeamViewer team) {
		System.out.println("|-----|-----|------------|-----|--------------|-----|");
		System.out.println("|Score|"+sidepad(game.calculateGainScores().get(team).toString(),' ',5)+"|Discard Size|"+sidepad(Integer.toString(team.getDiscardPile().size()),' ',5)+"|Stockpile Size|"+sidepad(Integer.toString(team.getStockPile().size()),' ',5)+"|");
		System.out.println("|-----|-----|------------|-----|--------------|-----|");
		System.out.println("|Gains|"+team.getGains().toString());
		System.out.println("|-----|");
	}
	public static void influenceDrawer(InfluenceCardViewer ic, int style) {
			
		StringBuilder card=new StringBuilder();
		card.append(ope(style));
		if(ic.isRevealed()) {
			card.append(KPDPControler.CARDTYPE_REGISTRY.getId(ic.getType()));
			card.append(" ");
			card.append(ic.getPoints());
		}
		else card.append("#####");
		card.append(fer(style));
		
		if(ic.isIgnored()) { card.insert(0, "x:"); card.append(":x");}
		else if(ic.getState()==InfluenceStat.ACTIVATED) { card.insert(0, "°"); card.append("°");}
		else if(ic.getState()==InfluenceStat.INACTIVE) { card.insert(0, "-|"); card.append("|-");}
		
		System.out.print( sidepad(stylize(card.toString(), style),' ',15) );
	}
	
	public static void objectiveDrawer(ObjectiveCardViewer oc) {
		System.out.print(sidepad("["+oc.getDomain().toString().substring(0, 6)+" "+oc.getObjective()+"]",' ',15));
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
	private static String stylize(String str, int style) {
		StringBuilder ret=new StringBuilder();
		for(int i=0; i<str.length(); i++) {
			switch(str.charAt(i)) {
				case '<' : ret.append(ope(style));
				case '>' : ret.append(fer(style));
				case ' ' : ret.append(spa(style));
				default : ret.append(str.charAt(i));
			}
		}
		return ret.toString();
	}
	private static String sidepad(String str, char padding, int maxsize) {
		String ret=str;
		boolean flag=false;
		
		while(ret.length()<maxsize) {
			if(flag)ret=padding+ret;
			else ret=ret+padding;
			flag=!flag;
		}
		
		return ret;
	}
	
	private static String spa(int style) {
		switch (style){
		case 0 : return " ";
		case 1 : return ":";
		case 2 : return ";";
		case 3 : return "-";
		case 4 : return "~";
		case 5 : return "#";
		case 6 : return "+";
		case 7 : return "•";
		case 8 : return "‡";
		case 9 : return "§";
		default : return "¤";
		}
	}
	private static String ope(int style) {
		switch (style){
		case 0 : return "(";
		case 1 : return "[";
		case 2 : return "<";
		case 3 : return "|";
		case 4 : return "((";
		case 5 : return "[[";
		case 6 : return "<{";
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
				case '<' : ret.append('>');
				default : ret.append(ope.charAt(i));
			}
		}
		return ret.toString();
	}
}


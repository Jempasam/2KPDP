package engine.serializer;

import engine.field.ColumnViewer;
import engine.field.FieldViewer;
import engine.game.GameViewer;
import engine.game.KPDPControler;
import engine.influence.InfluenceStat;
import engine.influence.viewer.InfluenceCardViewer;
import engine.objective.viewer.ObjectiveCardViewer;
import engine.teams.TeamViewer;

public class PrettySerializer implements KPDPSerializer{
	private GameViewer game;
	
	public PrettySerializer(GameViewer game) {
		super();
		this.game = game;
	}
	
	@Override
	public String serializeField(FieldViewer field){
		StringBuilder sb=new StringBuilder();
		boolean label=true;
		
		//DRAW LINE
		for(ColumnViewer c : game.getField())sb.append("|---------------|");
		sb.append("\n");
				
		//DRAW NUMBER
		int i=0;
		for(ColumnViewer c : game.getField()) {
			sb.append("|"+sidepad(Integer.toString(i),' ',15)+"|");
			i++;
		}
		sb.append("\n");
		
		//DRAW OBJECTIVE
		for(ColumnViewer c : game.getField())
			sb.append("|").append(serializeObjective(c.getObjectiveCard())).append("|");
		sb.append("\n");
		
		//DRAW LINE
		for(ColumnViewer c : game.getField())sb.append("|---------------|");
		sb.append("\n");
		
		//DRAW INFLUENCE
		for(int y=0;label; y++) {
			label=false;
			for(int x=0; x<game.getField().size(); x++)
			{
				ColumnViewer c=game.getField().get(x);
				if(y<c.size()) {
					InfluenceCardViewer ic=c.get(y);
					sb.append("|").append(serializeInfluence(ic)).append("|");
					label=true;
				}
				else sb.append("|               |");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	private static void playerDrawer(GameViewer game,TeamViewer team) {
		System.out.println("|-----|-----|------------|-----|--------------|-----|");
		System.out.println("|Score|"+sidepad(game.calculateGainScores().get(team).toString(),' ',5)+"|Discard Size|"+sidepad(Integer.toString(team.getDiscardPile().size()),' ',5)+"|Stockpile Size|"+sidepad(Integer.toString(team.getStockPile().size()),' ',5)+"|");
		System.out.println("|-----|-----|------------|-----|--------------|-----|");
		System.out.println("|Gains|"+team.getGains().toString());
		System.out.println("|-----|");
	}
	
	@Override
	public String serializeInfluence(InfluenceCardViewer ic) {
		StringBuilder card=new StringBuilder();
		card.append("(");
		if(ic.isRevealed()) {
			card.append(KPDPControler.CARDTYPE_REGISTRY.getId(ic.getType()));
			card.append(" ");
			card.append(ic.getPoints());
		}
		else card.append("#####");
		card.append(")");
		
		if(ic.isIgnored()) { card.insert(0, "x:"); card.append(":x");}
		else if(ic.getState()==InfluenceStat.ACTIVATED) { card.insert(0, "°"); card.append("°");}
		else if(ic.getState()==InfluenceStat.INACTIVE) { card.insert(0, "-|"); card.append("|-");}
		
		return stylize(card.toString(), game.idOfTeam(ic.getTeam()));
	}
	
	@Override
	public String serializeObjective(ObjectiveCardViewer oc) {
		StringBuilder sb=new StringBuilder();
		sb.append("(").append(oc.getDomain().toString()).append(" ").append(oc.getObjective()).append(")");
		return sb.toString();
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

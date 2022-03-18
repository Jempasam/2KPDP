package engine.kpdpevents.gameevent;

import engine.field.ColumnViewer;
import engine.game.Game;
import engine.objective.ObjectiveCard;
import engine.objective.viewer.ObjectiveCardViewer;
import engine.teams.Team;
import engine.teams.TeamViewer;

public class GiveGainEvent extends GameEvent{
	private ObjectiveCardViewer gain;
	private int column_num;
	private TeamViewer gainer;
	
	public GiveGainEvent(Game game, int column_num, ObjectiveCard gain, Team gainer) {
		super(game);
		this.gain = new ObjectiveCardViewer(gain);
		this.gainer = new TeamViewer(gainer);
		this.column_num=column_num;
	}
	
	public ObjectiveCardViewer getGain() {
		return gain;
	}
	
	public TeamViewer getGainer() {
		return gainer;
	}
	
	public int getColumnNum() {
		return column_num;
	}
	
	public ColumnViewer getColumn() {
		return game.getField().get(column_num);
	}
	
	@Override
	public String toString() {
		return "Le joueur "+game.idOfTeam(gainer)+" a gagné la carte objectif "+gain.toString()+" de la colonne "+column_num+".";
	}
	
}

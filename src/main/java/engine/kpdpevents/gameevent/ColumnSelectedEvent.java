package engine.kpdpevents.gameevent;

import engine.field.ColumnViewer;
import engine.game.Game;
import engine.teams.Team;
import engine.teams.TeamViewer;

public class ColumnSelectedEvent extends GameEvent{
	
	private TeamViewer player;
	private int column_selected;
	private int try_counter;
	private String reason;
	
	public ColumnSelectedEvent(Game game, Team player, int column_selected, int try_counter, String reason) {
		super(game);
		this.player = new TeamViewer(player);
		this.column_selected = column_selected;
		this.try_counter = try_counter;
		this.reason=reason;
	}

	public TeamViewer getPlayer() {
		return player;
	}

	public int getSelectedColumnIndex() {
		return column_selected;
	}
	
	public String getReason() {
		return reason;
	}

	public ColumnViewer getSelectedColumn() {
		return game.getField().get(column_selected);
	}

	public int getTryCounter() {
		return try_counter;
	}
	
	@Override
	public String toString() {
		return "Le joueur "+game.idOfTeam(player)+" a choisi la colonne n°"+column_selected+" pour "+reason+".";
	}

}
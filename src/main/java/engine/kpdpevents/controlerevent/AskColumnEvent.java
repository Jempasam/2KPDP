package engine.kpdpevents.controlerevent;

import engine.field.FieldViewer;
import engine.game.Game;
import engine.teams.Team;

public class AskColumnEvent extends ControlerEvent {
	Integer colpos;

	public AskColumnEvent(Game game, String reason, Team team, int try_counter) {
		super(game, reason, team, try_counter);
		colpos=null;
	}
	public FieldViewer getField() {
		return game.getField();
	}
	public void setColumnToUse(int column_number) {
		colpos=column_number;
	}
	public Integer getColumnToUse() {
		return colpos;
	}
}
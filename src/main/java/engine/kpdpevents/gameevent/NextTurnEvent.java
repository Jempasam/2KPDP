package engine.kpdpevents.gameevent;

import engine.game.Game;
import engine.teams.TeamViewer;

public class NextTurnEvent extends GameEvent {
	private int old_turn,now_turn;
	public NextTurnEvent(int old_turn, int now_turn, Game game) {
		super(game);
		this.old_turn=old_turn;
		this.now_turn=now_turn;
	}
	public int getTurn() {
		return now_turn;
	}
	public int getPreviousTurn() {
		return old_turn;
	}
	public TeamViewer getActualTeam() {
		return game.getTeams().get(now_turn);
	}
	public TeamViewer getPreviousTeam() {
		return game.getTeams().get(old_turn);
	}
	@Override
	public String toString() {
		return "Passage du tour du joueur "+old_turn+" au tour du joueur "+now_turn+".";
	}
}

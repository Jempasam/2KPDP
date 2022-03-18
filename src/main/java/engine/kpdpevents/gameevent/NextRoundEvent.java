package engine.kpdpevents.gameevent;

import engine.game.Game;

public class NextRoundEvent extends GameEvent {
	private int old_round, now_round;
	public NextRoundEvent(int old_round, int now_round, Game game) {
		super(game);
		this.old_round=old_round;
		this.now_round=now_round;
	}
	public int getRoundId() {
		return now_round;
	}
	public int getOldRoundId() {
		return old_round;
	}
	@Override
	public String toString() {
		return "Passage de la manche "+old_round+" à la manche "+now_round+".";
	}
}
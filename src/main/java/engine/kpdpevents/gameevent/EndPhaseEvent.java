package engine.kpdpevents.gameevent;

import engine.game.Game;

public class EndPhaseEvent extends GameEvent{

	public EndPhaseEvent(Game game) {
		super(game);
	}
	@Override
	public String toString() {
		return "Phase de r�solution de fin de manche";
	}
	
}

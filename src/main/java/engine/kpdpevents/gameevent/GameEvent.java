package engine.kpdpevents.gameevent;

import engine.events.Event;
import engine.game.Game;
import engine.game.GameViewer;

public class GameEvent extends Event{
	
	protected GameViewer game;
	
	public GameEvent(Game game) {
		this.game=new GameViewer(game);
	}
	public GameViewer getGame() {
		return game;
	}
	@Override
	public String toString() {
		return "Evenement de jeu.";
	}
}

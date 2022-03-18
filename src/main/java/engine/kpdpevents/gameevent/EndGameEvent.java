package engine.kpdpevents.gameevent;

import engine.game.Game;
import engine.teams.TeamViewer;

public class EndGameEvent extends GameEvent {

	public EndGameEvent(Game game) {
		super(game);
	}
	public TeamViewer calculateWinner() {
		return game.calculateWinner();
	}
	public int getTotalTurnNumber() {
		return game.getTotalTurns();
	}
	@Override
	public String toString() {
		return "Fin de la partie après "+game.getTotalTurns()+" tours, le gagnant est le joueur "+game.idOfTeam(calculateWinner())+" avec "+game.calculateGainScores().get(calculateWinner())+" points.";
	}

}

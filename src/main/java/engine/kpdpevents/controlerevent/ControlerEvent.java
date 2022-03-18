package engine.kpdpevents.controlerevent;

import engine.events.Event;
import engine.game.Game;
import engine.game.GameViewer;
import engine.teams.Team;
import engine.teams.TeamViewer;

public class ControlerEvent extends Event {
	
	protected GameViewer game;
	private String reason;
	private int try_counter;
	private TeamViewer asked;
	
	public ControlerEvent(Game game, String reason, Team team, int try_counter) {
		this.game=new GameViewer(game);
		this.reason=reason;
		this.asked=new TeamViewer(team);
		this.try_counter=try_counter;
	}
	
	public GameViewer getGame() {
		return game;
	}

	public String getReason() {
		return reason;
	}

	public TeamViewer getAsked() {
		return asked;
	}

	public int getTryNumber() {
		return try_counter;
	}
	
	
}

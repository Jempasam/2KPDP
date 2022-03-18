package engine.teams.controler.decorator;

import engine.game.GameViewer;
import engine.teams.TeamViewer;
import engine.teams.controler.TeamControler;

public class PauseThreadCD implements TeamControler{
	TeamControler controler;
	int pause_duration;
	public PauseThreadCD(TeamControler controler, int pause_duration) {
		this.controler=controler;
		this.pause_duration=pause_duration;
	}
	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		try {
			Thread.sleep(pause_duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return controler.askForCard(team, game, reason, try_counter);
	}
	@Override
	public int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter) {
		try {
			Thread.sleep(pause_duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return controler.askForColumn(team, game, reason, try_counter);
	}
	@Override
	public String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter) {
		try {
			Thread.sleep(pause_duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return controler.askForCommentary(team, game, reason, try_counter);
	}
	

}

package engine.teams.controler.decorator;

import java.util.List;

import engine.game.GameViewer;
import engine.teams.TeamViewer;
import engine.teams.controler.TeamControler;

public class RandomAssociativeCD implements TeamControler{
	List<TeamControler> controlers;
	TeamControler selected;
	public RandomAssociativeCD(List<TeamControler> controlers) {
		this.controlers=controlers;
	}
	
	private TeamControler getRandom() {
		return controlers.get((int)(controlers.size()*Math.random()));
	}
	
	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		TeamControler c=getRandom();
		if(reason.equals("playCard"))selected=c;
		return c.askForCard(team, game, reason, try_counter);
	}

	@Override
	public int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter) {
		if(reason.equals("playCard"))selected.askForCard(team, game, reason, try_counter);
		return getRandom().askForCard(team, game, reason, try_counter);
	}

	@Override
	public String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter) {
		return getRandom().askForCommentary(team, game, reason, try_counter);
	}
	
}

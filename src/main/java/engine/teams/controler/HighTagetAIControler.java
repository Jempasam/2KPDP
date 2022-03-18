package engine.teams.controler;

import engine.game.GameViewer;
import engine.teams.TeamViewer;

public class HighTagetAIControler implements TeamControler {

	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		return (int)(team.getHand().size()*Math.random());
	}

	@Override
	public int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter) {
		if(try_counter>0)return (int)(game.getField().size()*Math.random());
		
		int selected=0,max=0;
		for(int i=0; i<game.getField().size(); i++)
		if(game.getField().get(i).getObjective()>max) {
			selected=i;
			max=game.getField().get(i).getObjective();
		}
				
		return selected;
	}

	@Override
	public String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter) {
		return "I need to target the higher objective !";
	}

}

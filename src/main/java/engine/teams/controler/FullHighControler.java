package engine.teams.controler;

import engine.game.GameViewer;
import engine.influence.viewer.InfluenceCardViewer;
import engine.teams.TeamViewer;

public class FullHighControler extends HighTagetAIControler {

	public FullHighControler() {
		super();
	}
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		int selected=0;
		float max=0;
		for(int i=0; i<team.getHand().size(); i++) {
			InfluenceCardViewer ic=team.getHand().get(i);
			if(ic.getPoints()>max) {
				selected=i;
				max=ic.getPoints();
			}
		}
		return selected;
	}
	@Override
	public String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter) {
		return super.askForCommentary(team, game, reason, try_counter)+" I need to play the higher card !";
	}
	
}

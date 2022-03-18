package engine.teams.controler;

import engine.game.GameViewer;
import engine.teams.TeamViewer;
import jempasam.swj.prototype.loader.tags.ObjectSerializable;
/**
 * Un controleur aléatoire
 * @author Samuel Demont
 *
 */
@ObjectSerializable
public class RandomAIControler implements PrototypeTeamControler {
	
	public RandomAIControler() {
	}
	
	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		return (int)(team.getHand().size()*Math.random());
	}

	@Override
	public int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter) {
		return (int)(game.getField().size()*Math.random());
	}

	@Override
	public String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter) {
		switch((int)(Math.random()*4)) {
			case 0 : return "Nice!";
			case 1 : return "Oh no!";
			case 2 : return "Je me demande...";
			default : return "Bonjour";
		}
	}
	@Override
	public Object clone(){return new RandomAIControler();}
	
}

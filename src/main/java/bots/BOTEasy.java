package bots;


import engine.game.GameViewer;
import engine.teams.TeamViewer;
import engine.teams.controler.PrototypeTeamControler;
import engine.teams.controler.TeamControler;
/**
 * Un BOT jouant de maniere aleatoire
 * @author Equipe BOT
 *
 */
public class BOTEasy implements PrototypeTeamControler {
	
	public BOTEasy() {
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
	
	public PrototypeTeamControler clone() {
		return new BOTEasy();
	}
	
}

package engine.teams.controler.decorator;

import engine.game.GameViewer;
import engine.teams.TeamViewer;
import engine.teams.controler.HighTagetAIControler;
import engine.teams.controler.PrototypeTeamControler;
import engine.teams.controler.TeamControler;
import jempasam.swj.prototype.loader.tags.ObjectParameter;
import jempasam.swj.prototype.loader.tags.ObjectSerializable;

@ObjectSerializable
public class HighTargetCD extends HighTagetAIControler implements PrototypeTeamControler{
	
	@ObjectParameter
	public TeamControler decorated;
	
	
	public HighTargetCD(TeamControler decorated) {
		super();
		this.decorated = decorated;
	}
	
	public HighTargetCD() {
		super();
		this.decorated = null;
	}

	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		return decorated.askForCard(team, game, reason, try_counter);
	}
	
	public PrototypeTeamControler clone() {
		return new HighTargetCD(decorated);
	}
}

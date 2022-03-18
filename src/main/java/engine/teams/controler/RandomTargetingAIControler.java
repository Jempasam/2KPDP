package engine.teams.controler;

import engine.game.GameViewer;
import engine.teams.TeamViewer;
import jempasam.swj.prototype.loader.tags.ObjectParameter;
import jempasam.swj.prototype.loader.tags.ObjectSerializable;

@ObjectSerializable
public class RandomTargetingAIControler implements PrototypeTeamControler{
	@ObjectParameter
	public int targeting_time;
	@ObjectParameter
	public int targetnumber;
	private int[] targets;
	private int counter;
	
	public RandomTargetingAIControler(int target_number, int targeting_time) {
		super();
		this.targets =new int[target_number];
		this.targetnumber=target_number;
		this.targeting_time = targeting_time;
		counter=0;
	}
	
	public RandomTargetingAIControler() {
		this(1,2);
	}
	
	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		return (int)(team.getHand().size()*Math.random());
	}
	
	@Override
	public int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter) {
		if(targetnumber!=targets.length)targets=new int[targetnumber];
		
		counter--;
		if(counter<=0) {
			counter=targeting_time;
			for(int i=0; i<targets.length; i++) targets[i]=(int)(game.getField().size()*Math.random());
		}
		return targets[(int)(targets.length*Math.random())];
	}
	
	@Override
	public String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter) {
		return "I need to target "+targets.length+" then change my targets every "+targeting_time+" turns.";
	}
	
	@Override public PrototypeTeamControler clone() { return new RandomTargetingAIControler(targetnumber,targeting_time); }
	
}

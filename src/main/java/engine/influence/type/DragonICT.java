package engine.influence.type;

import engine.influence.EffectContext;
import engine.influence.InfluenceCard;
import engine.teams.Team;

public class DragonICT extends InfluenceCardType {
	float malus;
	public DragonICT(float points, float malus) {
		super(points, 5, false);
		this.malus=malus;
	}
	@Override
	public void useEndEffect(EffectContext context) {
		Team team=context.getTeam();
		for(InfluenceCard card : context.getColumn()) {
			if(card.getTeam()!=team)
			{
				card.setPoints(card.getPoints()-malus);
			}
		}
	}
}

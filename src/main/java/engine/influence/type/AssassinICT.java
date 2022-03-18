package engine.influence.type;

import engine.influence.EffectContext;
import engine.influence.InfluenceCard;

public class AssassinICT extends InfluenceCardType{

	public AssassinICT(float points) {
		super(points, 0, false);
	}
	@Override
	public void useRevealEffect(EffectContext context) {
		InfluenceCard killed=context.getCardBeneath();
		if(killed!=null) {
			context.getColumn().discard(killed);
		}
	}
}

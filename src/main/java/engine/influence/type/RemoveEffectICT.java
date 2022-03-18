package engine.influence.type;

import engine.influence.EffectContext;
import engine.influence.InfluenceCard;
import engine.influence.InfluenceStat;

public class RemoveEffectICT extends InfluenceCardType{
	public RemoveEffectICT(float points) {
		super(points, 2, false);
	}
	@Override
	public void useEndEffect(EffectContext context) {
		InfluenceCard card=context.getCard();
		for(InfluenceCard c : context.getColumn()) {
			if(c.getState()==InfluenceStat.ACTIVE)c.setState(InfluenceStat.CANCELED);
		}
	}
}

package engine.influence.type.added;

import engine.influence.EffectContext;
import engine.influence.type.InfluenceCardType;

public class HiddenICT extends InfluenceCardType {
	float discovered_malus;

	public HiddenICT(float points, float discovered_malus) {
		super(points, 0, false);
		this.discovered_malus = discovered_malus;
	}
	@Override
	public void useRevealEffect(EffectContext context) {
		context.getCard().setPoints(context.getCard().getPoints()-discovered_malus);
	}
	
}

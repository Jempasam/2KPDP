package engine.influence.type.added;

import engine.influence.EffectContext;
import engine.influence.InfluenceCard;
import engine.influence.type.InfluenceCardType;

public class AllHidedICT extends InfluenceCardType {

	public AllHidedICT(float points) {
		super(points, 0, true);
	}
	@Override
	public void useRevealEffect(EffectContext context) {
		for(InfluenceCard ic : context.getColumn()) {
			if(context.getCard()!=ic) ic.hide();
		}
	}
	@Override
	public void useEndEffect(EffectContext context) {
		for(int i=0; i<context.getLineNb(); i++) {
			context.getColumn().get(i).reveal();
		}
	}
	
}

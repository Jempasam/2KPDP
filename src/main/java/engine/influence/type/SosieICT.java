package engine.influence.type;

import engine.influence.EffectContext;
import engine.influence.InfluenceCard;

public class SosieICT extends InfluenceCardType{

	public SosieICT(float points) {
		super(points, 6, false);
	}
	@Override
	public void useEndEffect(EffectContext context) {
		InfluenceCard beneath=context.getCardBeneath();
		if(beneath.getType() instanceof SosieICT) {
			beneath.tryEndEffect(new EffectContext(context.getGame(), context.getColumnNb(), context.getLineNb()+1));
		}
		if(beneath!=null)context.getCard().setPoints(beneath.getPoints());
	}
}

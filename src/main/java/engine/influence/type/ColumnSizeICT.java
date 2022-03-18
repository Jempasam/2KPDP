package engine.influence.type;

import engine.influence.EffectContext;
import engine.influence.InfluenceCard;

public class ColumnSizeICT extends InfluenceCardType{
	float multiplier;
	public ColumnSizeICT(float points,float multiplier) {
		super(points, 5, false);
		this.multiplier=multiplier;
	}
	@Override
	public void useEndEffect(EffectContext context) { // TODO Auto-generated method stub
		InfluenceCard card=context.getCard();
		card.setPoints(card.getPoints()+(context.getColumn().size()-1)*multiplier);
	}

}

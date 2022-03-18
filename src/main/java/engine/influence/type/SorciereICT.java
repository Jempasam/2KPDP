package engine.influence.type;

import engine.influence.EffectContext;
import engine.influence.InfluenceCard;
import engine.influence.InfluenceStat;

public class SorciereICT extends InfluenceCardType{
	float max;
	public SorciereICT(float points,float max) {
		super(points, 4, false);
		this.max=max;
	}
	@Override
	public void useEndEffect(EffectContext context) { // TODO Auto-generated method stub
		boolean canceled=false;
		InfluenceCard card=context.getCard();
		for(InfluenceCard c : context.getColumn()) {
			if(c!=card && c.getType()==card.getType()) {
				canceled=true;
				break;
			}
		}
		if(!canceled)
			for(InfluenceCard c : context.getColumn())
				if(c!=card && c.getPoints()<=max && c.getType()!=this)
					c.setState(InfluenceStat.ELIMINATED);
	}

}

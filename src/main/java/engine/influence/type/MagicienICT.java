package engine.influence.type;

import engine.influence.EffectContext;
import engine.influence.InfluenceCard;
import engine.influence.InfluenceStat;

public class MagicienICT extends InfluenceCardType{
	float min;
	public MagicienICT(float points,float min) {
		super(points, 3, false);
		this.min=min;
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
				if(c!=card && c.getPoints()>=min && c.getType()!=this)
					c.setState(InfluenceStat.ELIMINATED);
	}

}

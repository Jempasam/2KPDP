package engine.influence.type;

import engine.influence.EffectContext;
import engine.influence.InfluenceCard;
import engine.objective.Domain;

public class DomainBoostICT extends InfluenceCardType{
	Domain domain;
	float bonus;
	
	public DomainBoostICT(Domain domain,float point, float bonus) {
		super(point,1,true);
		this.domain = domain;
		this.bonus = bonus;
	}

	@Override
	public void useEndEffect(EffectContext context) { // TODO Auto-generated method stub
		InfluenceCard card=context.getCard();
		if(context.getColumn().getObjectiveCard().getDomain()==domain) card.setPoints(card.getPoints()+bonus);
	}

	@Override
	public void useRevealEffect(EffectContext context) { // TODO Auto-generated method stub
	}

	public Domain getDomain() {
		return domain;
	}
	
	
}

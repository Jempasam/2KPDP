package engine.influence.type;

import engine.influence.EffectContext;
import engine.influence.InfluenceCard;

public class CombineICT extends InfluenceCardType {
	InfluenceCardType type;
	float bonus;
	public CombineICT(float points, InfluenceCardType type, float bonus) {
		super(points, 5, false);
		this.type=type;
		this.bonus=bonus;
	}
	@Override
	public void useEndEffect(EffectContext context) {
		InfluenceCard card=context.getCard();
		for(InfluenceCard c : context.getColumn()) {
			if(c!=card && c.getType()==type && c.getTeam()==card.getTeam()) {
				card.setPoints(card.getPoints()+bonus);
				break;
			}
		}
	}

}

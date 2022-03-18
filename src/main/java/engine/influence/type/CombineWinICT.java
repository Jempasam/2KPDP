package engine.influence.type;

import engine.field.ColumnStat;
import engine.influence.EffectContext;
import engine.influence.InfluenceCard;
import engine.influence.InfluenceStat;

public class CombineWinICT extends InfluenceCardType{
	
	InfluenceCardType type;
	
	public CombineWinICT(float points, InfluenceCardType type) {
		super(points, 0, false);
		this.type=type;
	}
	
	@Override
	public void useRevealEffect(EffectContext context) {
		InfluenceCard combined=null;
		InfluenceCard card=context.getCard();
		for(InfluenceCard c : context.getColumn()) {
			if(c!=card && c.getType()==type && c.getTeam()==card.getTeam()) {
				combined=c;
				break;
			}
		}
		if(combined!=null) {
			for(InfluenceCard c : context.getColumn()) {
				if(c!=null && c!=card) {
					c.setState(InfluenceStat.ELIMINATED);
					c.reveal();
					context.getColumn().setState(ColumnStat.CLOSED);
				}
			}
		}
	}

}

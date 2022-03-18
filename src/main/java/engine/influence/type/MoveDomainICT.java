package engine.influence.type;

import engine.influence.EffectContext;
import engine.objective.ObjectiveCard;

public class MoveDomainICT extends InfluenceCardType{
	
	public MoveDomainICT(float points) {
		super(points, 0, false);
	}
	@Override
	public void useRevealEffect(EffectContext context) {
		int col1,col2;
		for(int try_counter=0; try_counter<10; try_counter++) {
			col1=context.getGame().askColumnTo(context.getTeam(), "exchangeColumn", try_counter);
			col2=context.getColumnNb();
			if(0 <= col1 && col1 < context.getField().size() &&
				0 <= col2 && col2 < context.getField().size() &&
				col1!=col2) {
				ObjectiveCard oc=context.getField().get(col1).getObjectiveCard();
				context.getField().get(col1).setObjectiveCard(context.getField().get(col2).getObjectiveCard());
				context.getField().get(col2).setObjectiveCard(oc);
				break;
			}
		}
	}

}
package engine.influence.type.added;

import engine.influence.EffectContext;
import engine.influence.type.InfluenceCardType;

public class TimeBoostICT extends InfluenceCardType{
	float boost_by_turn;
	public TimeBoostICT(float points, float boost_by_turn) {
		super(points, 5, false);
		this.boost_by_turn=boost_by_turn;
	}
	@Override
	public void useEndEffect(EffectContext context) {
		context.getCard().setPoints(context.getCard().getPoints()+context.getGame().getTotalTurns()*boost_by_turn);
	}
	
}

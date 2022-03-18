package engine.influence.type;

import engine.calculator.Calculators;
import engine.influence.EffectContext;

public class MendiantICT extends InfluenceCardType {

	public MendiantICT(float points) {
		super(points, 5, false);
	}
	@Override
	public void useEndEffect(EffectContext context) {
		context.getGame().getResolutionParameters().referee.columnwinnercalculator=Calculators.WINNER_SMALLER;
	}

}

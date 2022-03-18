package engine.influence.type;

import engine.field.ColumnStat;
import engine.influence.EffectContext;

public class TempeteICT extends InfluenceCardType{

	public TempeteICT(float points) {
		super(points, 0, false);
	}
	@Override
	public void useRevealEffect(EffectContext context) { // TODO Auto-generated method stub
		context.getColumn().setState(ColumnStat.CLOSED);
	}
}

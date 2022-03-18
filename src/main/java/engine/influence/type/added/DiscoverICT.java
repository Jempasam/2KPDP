package engine.influence.type.added;

import engine.field.Column;
import engine.influence.EffectContext;
import engine.influence.type.InfluenceCardType;

public class DiscoverICT extends InfluenceCardType{
	int number;
	public DiscoverICT(float points, int number_discovered) {
		super(points, 0, false);
		number=number_discovered;
	}
	@Override
	public void useRevealEffect(EffectContext context) {
		super.useRevealEffect(context);
		for(int i=0; i<number; i++)
		{
			for(int try_counter=0; try_counter<10; try_counter++) {
				int discovered_col=context.getGame().askColumnTo(context.getTeam(), "discoverCard", try_counter);
				if(0 <= discovered_col && discovered_col < context.getField().size()) {
					Column col=context.getField().get(discovered_col);
					if(col.size()>0)col.last().reveal();
					break;
				}
			}
		}
	}

}

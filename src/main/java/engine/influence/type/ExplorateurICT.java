package engine.influence.type;

import engine.field.Column;
import engine.field.ColumnStat;
import engine.influence.EffectContext;
import engine.influence.InfluenceCard;

public class ExplorateurICT extends InfluenceCardType{
	
	public ExplorateurICT(float points) {
		super(points, 0, false);
	}
	@Override
	public void useRevealEffect(EffectContext context) {
		//Calcule la nouvelle colonne
		int newpos = context.getColumnNb();
		do {
			newpos++;
			if(newpos >= context.getField().size()) newpos=0;
		}while(context.getField().get(newpos).getState()==ColumnStat.CLOSED);
		
		//Deplace la carte
		InfluenceCard movecard=context.getCard();
		movecard.hide();
		Column newcol=context.getField().get(newpos);
		context.getColumn().moveTo(movecard, newcol);
		if(newcol.size()>1) {
			newcol.getFromEnd(1).tryRevealEffect(new EffectContext(context.getGame(), newpos, newcol.size()-2));
		}
	}

}

package engine.influence.type;

import engine.card.container.CardPile;
import engine.influence.EffectContext;
import engine.influence.InfluenceCard;

public class CapeICT extends InfluenceCardType{
	public CapeICT(float points) {
		super(points, 0, false);
	}
	@Override
	public void useEndEffect(EffectContext context) {
		InfluenceCard hidden=(InfluenceCard)context.getCard().getCustomData("hidden", InfluenceCard.class);
		if(hidden!=null) {
			context.getColumn().discard(context.getCard());
			context.getColumn().insert(context.getLineNb(), hidden);
		}
		context.getCard().removeCustomData("hidden", InfluenceCard.class);
	}
	
	public void useRevealEffect(EffectContext context) {
		InfluenceCard ic=context.getCard();
		
		for(int try_counter=0; try_counter<10 ; try_counter++) {
			Integer cardtohide=context.getGame().askCardTo(context.getTeam(), "hideCard", try_counter);
			if( cardtohide!=null && 0 <= cardtohide && cardtohide < context.getTeam().getHand().size()) {
				InfluenceCard hidden=context.getTeam().getHand().remove(cardtohide);
				ic.setCustomData("hidden", hidden);
				context.getTeam().draw();
				break;
			}
		}
	}
	public void discard(CardPile<InfluenceCard> discardpile, InfluenceCard card) {
		super.discard(discardpile, card);
		InfluenceCard hidden=(InfluenceCard)card.getCustomData("hidden", InfluenceCard.class);
		if(hidden!=null)hidden.discard();
	}

}

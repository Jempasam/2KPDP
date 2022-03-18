package engine.card.shuffler;

import engine.card.Card;
import engine.card.container.CardContainer;

/**
 * Un mélangeur de carte simple et rapide.
 * @author Samuel Demont
 *
 */
public class FastCardShuffler implements ICardShuffler{

	@Override
	public <T extends Card> void shuffle(CardContainer<T> container) {
		CardContainer<T> cards=container;
		for(int i=0; i<cards.size(); i++) {
			int from=(int)((cards.size()-i)*Math.random())+i;
			container.swap(i, from);
		}
	}

}

package engine.card.shuffler;

import engine.card.Card;
import engine.card.container.CardContainer;

/**
 * Un mélangeur de carte qui ne mélange pas les cartes
 * @author Samuel Demont
 *
 */
public class NoCardShuffler implements ICardShuffler{

	@Override
	public <T extends Card> void shuffle(CardContainer<T> container) {
	}

}

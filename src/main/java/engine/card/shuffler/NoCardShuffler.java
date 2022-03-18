package engine.card.shuffler;

import engine.card.Card;
import engine.card.container.CardContainer;

/**
 * Un m�langeur de carte qui ne m�lange pas les cartes
 * @author Samuel Demont
 *
 */
public class NoCardShuffler implements ICardShuffler{

	@Override
	public <T extends Card> void shuffle(CardContainer<T> container) {
	}

}

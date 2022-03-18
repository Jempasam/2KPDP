package engine.card.container.viewer;

import java.util.Iterator;

import engine.card.Card;
import engine.card.CardViewer;
import engine.card.container.CardContainer;

/**
 * Un itérateur utilisé par CardContainerViewer
 * @author Samuel Demont
 *
 * @param <T>
 */
public class CardViewerIterator<T extends Card> implements Iterator<CardViewer<T>>{

	Iterator<T> it;
	public CardViewerIterator(CardContainer<T> cards) {
		it=cards.iterator();
	}
	@Override
	public boolean hasNext() {return it.hasNext();}
	@Override
	public CardViewer<T> next() {return new CardViewer<T>(it.next());}
	
}

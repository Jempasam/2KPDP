package engine.card.shuffler;

import engine.card.Card;
import engine.card.container.CardContainer;

/**
 * Un mélangeur de carte possède une seule méthode qui mélange les cartes du CardContainer passé en paramètre
 * @author Samuel Demont
 *
 */
public interface ICardShuffler {
	/**
	 * 
	 * Mélange les cartes d'un CardContainer
	 * @param container Le CardContainer à mélanger
	 */
	public <T extends Card> void shuffle(CardContainer<T> container);
}

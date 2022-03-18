package engine.card.shuffler;

import engine.card.Card;
import engine.card.container.CardContainer;

/**
 * Un m�langeur de carte poss�de une seule m�thode qui m�lange les cartes du CardContainer pass� en param�tre
 * @author Samuel Demont
 *
 */
public interface ICardShuffler {
	/**
	 * 
	 * M�lange les cartes d'un CardContainer
	 * @param container Le CardContainer � m�langer
	 */
	public <T extends Card> void shuffle(CardContainer<T> container);
}

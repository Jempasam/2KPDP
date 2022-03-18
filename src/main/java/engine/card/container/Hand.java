package engine.card.container;

import engine.card.Card;

/**
 * Représente une main d'un jeu de carte
 * @author Samuel Demont
 *
 * @param <T>
 */
public class Hand<T extends Card> extends CardContainer<T> {

	public Hand() {
		super();
	}
	public Hand(Hand<T> h) {
		super(h);
	}
	
}

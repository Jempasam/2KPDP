package engine.card.container;

import engine.card.Card;

/**
 * Repr�sente une pile de cartes
 * @author Samuel Demont
 *
 * @param <T>
 */
public class CardPile<T extends Card> extends CardContainer<T> {  
	
	public CardPile() {
		super();
	}
	public CardPile(CardPile copied) {
		super(copied);
	}
	/**
     * Pioche une carte. La carte du dessus du paquet est supprim�e et retourn�e
     * @param la carte � ajout�
     */
    public T draw() {
        if(size()>0) return remove(size()-1);
        return null;
    }
    /**
     * Ajoute une carte sur le dessus de la pile.
     * @param la carte � ajout�
     */
    public void putAbove(T card) {
        add(card);
    }
    /**
     * Ajoute une carte en dessous de la pile.
     * @param la carte � ajout�
     */
    public void putBeneath(T card) {
        insert(0, card);
    }
    /**
     * Ajoute une carte � un endroit al�atoire dans le paquet
     * @param la carte � ajout�
     */
    public void putInside(T card) {
        int index=(int)(1+Math.random()*(size()-2));
        insert(index,card);
    }
}

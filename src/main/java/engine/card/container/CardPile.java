package engine.card.container;

import engine.card.Card;

/**
 * Représente une pile de cartes
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
     * Pioche une carte. La carte du dessus du paquet est supprimée et retournée
     * @param la carte à ajouté
     */
    public T draw() {
        if(size()>0) return remove(size()-1);
        return null;
    }
    /**
     * Ajoute une carte sur le dessus de la pile.
     * @param la carte à ajouté
     */
    public void putAbove(T card) {
        add(card);
    }
    /**
     * Ajoute une carte en dessous de la pile.
     * @param la carte à ajouté
     */
    public void putBeneath(T card) {
        insert(0, card);
    }
    /**
     * Ajoute une carte à un endroit aléatoire dans le paquet
     * @param la carte à ajouté
     */
    public void putInside(T card) {
        int index=(int)(1+Math.random()*(size()-2));
        insert(index,card);
    }
}

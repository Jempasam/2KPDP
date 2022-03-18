package engine.card.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import engine.card.Card;
import jempasam.swj.container.Container;

/**
* Conteneur de carte générique
* @author Samuel Demont
*/
public class CardContainer<T extends Card> implements Container<T> {
    private List<T> cards;

    public CardContainer() {
		cards=new ArrayList<>();
	}
    public CardContainer(CardContainer<T> copied) {
    	cards=new ArrayList<T>();
		cards=new ArrayList<>(copied.cards);
	}
    public T get(int index) {return cards.get(index);}
    public void set(int index,T card) {cards.set(index, card);}
    public void insert(int index,T card) {cards.add(index,card);}
    public T remove(int index) {return cards.remove(index);}
    
    public boolean moveTo(T card,CardContainer<T> target) {
    	if(cards.remove(card)) {
    		target.add(card);
    		return true;
    	}
    	return false;
    }

    public int size() {
        return cards.size();
    }
    
	@Override
	public Iterator<T> iterator() {
		return cards.iterator();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof CardContainer<?> && ((CardContainer<?>)obj).cards.equals(cards);
	}
    
	@Override
	public String toString() {
		StringBuilder ret=new StringBuilder();
		for(T c : cards)ret.append("[").append(c.toString()).append("]");
		return ret.toString();
	}
}

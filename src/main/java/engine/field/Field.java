package engine.field;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import engine.card.container.CardPile;
import engine.influence.InfluenceCard;
import engine.influence.InfluenceStat;
import engine.objective.ObjectiveCard;
import jempasam.swj.container.ReadableContainer;
import jempasam.swj.iterator.CompositeIterator;

/**
 * 
 * @author Samuel Demont
 * Classe repr�sentant un terrain compos� de colonnes
 *
 */
public class Field implements ReadableContainer<Column>{
    
    private List<Column> columns;

    public Field(int columnnum, int maxcolumnsize) {
    	columns=new ArrayList<>();
		for(int i=0; i<columnnum; i++)columns.add(new Column(null,maxcolumnsize));
	}
    
    public Field deepClone() {
    	Field clone=new Field(0,0);
    	for(Column c : this)clone.columns.add(c.deepClone());
    	return clone;
    }
    
    /**
     * Cr�e un terrain � partir d'une pioche de carte objectif
     * @param objectives la pioche de carte objectif
     * @param columnsnum le nombre de colonne
     * @return le terrain, ou null si il n'y a pas assez de cartes objectifs restantes
     */
    public static Field fieldOfPile(CardPile<ObjectiveCard> objectives, int columnsnum, int maxcolumnsize) {
    	Field f=new Field(columnsnum,maxcolumnsize);
    	for(Column c : f) {
    		if(objectives.size()==0)return null;
    		c.setObjectiveCard(objectives.draw());
    	}
    	return f;
    }
    
    /**
     * @return true si toutes les colonnes sont termin�es, sinon false
     */
    public boolean isFinished() {
    	return testIfAll(Column::isFinished);
    }
    
    /**
     * @return le nombre de colonne
     */
    public int size() {
        return columns.size();
    }
    
    /**
     * 
     * @param index
     * @return Retourne la colonne d'index indiqu�e
     */
    public Column get(int index) {
        return columns.get(index);
    }
    
    public void checkAllState() {
    	forEach(Column::checkState);
    }
    
    public Iterator<InfluenceCard> influenceCardIterator(){
    	return new CompositeIterator<>(columns.toArray(new Column[0]));
    }
    
    /**
     * Effectue l'action donn�e sur toutes les cartes influences du terrain.
     * @param action L'action � effectuer
     */
    public void forEachInfluenceCards(Consumer<InfluenceCard> action) {
    	Iterator<InfluenceCard> it=influenceCardIterator();
    	while(it.hasNext())action.accept(it.next());
    }
    
    /**
     * Effectue l'action donn�e sur toutes les cartes objectifs
     * @param action L'action � effectuer
     */
    public void forEachObjectiveCards(Consumer<ObjectiveCard> action) {
    	for(Column c : columns)action.accept(c.getObjectiveCard());
    }
    /**
     * Reactive les effets de carte d�sactiv�s
     */
    public void reactiveEffects() {
    	for(Column c : this) {
    		c.reactiveEffects();
    	}
    }
    
    /**
     * D�fausse toutes les cartes de la colonne et les envoie dans la d�fausse de leur propri�taire
     * @return Le nombre de carte d�fauss�e
     */
    public int discardAll() {
    	int ret=0;
    	for(Column c : this)ret+=c.discardAll();
    	return ret;
    }
    
	@Override
	public Iterator<Column> iterator() {
		return columns.iterator();
	}
    @Override
    public String toString() {
    	StringBuilder ret=new StringBuilder();
    	for(Column c : this) ret.append(c.toString()).append("\n");
    	return ret.toString();
    }
}

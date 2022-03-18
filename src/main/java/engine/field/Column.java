package engine.field;

import engine.card.container.CardContainer;
import engine.influence.InfluenceCard;
import engine.influence.InfluenceStat;
import engine.objective.Domain;
import engine.objective.ObjectiveCard;

/**
 * Repr�sente une colonne
 * @author Samuel Demont
 *
 */
public class Column extends CardContainer<InfluenceCard> {
    
    private ColumnStat state;
    private ObjectiveCard objective;
    private int maxsize;
    
    public Column(ObjectiveCard objective, int maxsize) {
		super();
		this.objective=objective;
		this.maxsize=maxsize;
		state=ColumnStat.OPENED;
	}
    
    /**
     * Deep clone a column
     * @param copied
     * @return
     */
    public Column deepClone() {
    	Column clone=new Column(new ObjectiveCard(objective), maxsize);
    	for(InfluenceCard ic : this)clone.add(new InfluenceCard(ic));
    	return clone;
    }
    
    /**
     * @return l'�tat de la colonne
     */
    public ColumnStat getState() { return this.state; }
    
    /**
     * Change l'�tat de la colonne
     * @param state Le nouvel �tat
     */
    public void setState(ColumnStat state) { this.state = state; }
    
    /**
     * @return la taille maximale de la colonne
     */
    public int getMaxSize() { return maxsize; }
    
    /**
     * Change la taille maximale de la colonne
     * @param maxsize La nouvelle taille
     */
    public void setMaxSize(int maxsize) { this.maxsize=maxsize; }
    
    /**
     * Renvoie la carte objectif de la colonne
     * @return La carte objectif
     */
    public ObjectiveCard getObjectiveCard() {
        return this.objective;
    }
    
    /**
     * Change la carte objectif
     * @param objective La nouvelle carte objectif
     */
    public void setObjectiveCard(ObjectiveCard objective) {
        this.objective = objective;
    } 
    
    /**
     * @return true si la colonne est finie ou ferm�e
     */
    public boolean isFinished() {
    	return state != ColumnStat.OPENED;
    }
    
    /**
     * @return le nombre de carte � atteindre pour terminer la colonne
     */
    public int getObjective() {
    	return objective.getObjective();
    }
    
    /**
     * @return le domaine de la colonne
     */
    public Domain getDomain() {
    	return objective.getDomain();
    }
    
    @Override
    public void insert(int index, InfluenceCard card) {
    	super.insert(index, card);
    }
    
    public void checkState() {
    	if(state==ColumnStat.OPENED && size()>=getObjective()) {
			state=ColumnStat.FINISHED;
		}
		if(size()>=maxsize) {
			state=ColumnStat.CLOSED;
		}
    }
    
    /**
     * Joue une carte sur la colonne sans activer les effets, en verifiant si la colonne est pleine et si elle est bloqu�e
     * @param card La carte � jouer
     * @return true si la carte a pu �tre jou�e
     */
    public boolean playCard(InfluenceCard card) {
    	if(canPlayCard(card))
    	{
    		card.hide();
    		add(card);
    		return true;
    	}
    	return false;
    }
    
    /**
     * 
     * @param card Une carte
     * @return true si on peut jouer une carte sur cette colonne
     */
    public boolean canPlayCard(InfluenceCard card) {
    	return state!=ColumnStat.CLOSED;
    }
    
    /**
     * Reactive les effets de carte d�sactiv�s
     */
    public void reactiveEffects() {
    	for(InfluenceCard c : this) {
    		if(c.getState()==InfluenceStat.INACTIVE || c.getState()==InfluenceStat.ACTIVATED) c.setState(InfluenceStat.ACTIVE);
    	}
    }
    
    /**
     * D�fausse une carte de la colonne et l'envoie dans la d�fausse de son propri�taire
     * @param card La care � d�fausser
     * @return true si la carte a bien �t� trouv�e et d�fauss�e sinon false
     */
    public boolean discard(InfluenceCard card) {
    	if(remove(card)) {
    		card.discard();
    		return true;
    	}
    	return false;
    }
    
    /**
     * D�fausse toutes les cartes de la colonne et les envoie dans la d�fausse de leur propri�taire
     * @return Le nombre de carte d�fauss�e
     */
    public int discardAll() {
    	int ret=0;
    	for(int i=size()-1; i>=0; i--) {
    		InfluenceCard card=remove(i);
    		card.discard();
    		ret++;
    	}
    	return ret;
    }
    
    @Override
    public boolean equals(Object obj) {
    	return super.equals(obj) && obj instanceof Column && objective.equals( ((Column)obj).getObjectiveCard() ) && maxsize==((Column)obj).maxsize && state==((Column)obj).state;
    }
    @Override
    public String toString() {
    	return "["+objective+"] : "+super.toString();
    }
}

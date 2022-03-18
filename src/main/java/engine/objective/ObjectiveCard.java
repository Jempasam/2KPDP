package engine.objective;

import engine.card.Card;

/**
 * @author Anthony Viano
 */
public class ObjectiveCard extends Card {

    private int objective;
    private Domain domain;
    
    //Constructor
    
    /**
     * Constructeur ObjectiveCard qui prend comme param�tres :
     * un int qui est le nombre de points que rapporte l'objectif
     * un Domain qui est le domaine de l'objectif
     */
    public ObjectiveCard(int obj, Domain dom) {
    	objective = obj;
    	domain = dom;
    }
    
    /**
     * Copy constructor
     * @param copied
     */
    public ObjectiveCard(ObjectiveCard copied) {
		objective=copied.objective;
		domain=copied.domain;
	}
    
    //Getters and Setters
    
    /**
     * Retourne la valeur de l'objectif
     */
    public int getObjective() {
        return this.objective;
    }
    
    /**
     * Donne une valeur � l'objectif
     */
    public void setObjective(int objective) {
        this.objective = objective;
    }

    /**
     * Retourne le domaine de l'objectif
     */
    public Domain getDomain() {
        return this.domain;
    }

    /**
     * Donne un domaine à l'objectif
     */
    public void setDomain(Domain domain) {
        this.domain = domain;
    }
    
    @Override
    public boolean equals(Object obj) {
    	return super.equals(obj) && obj instanceof ObjectiveCard && ((ObjectiveCard)obj).domain==domain && ((ObjectiveCard)obj).objective==objective;
    }
    
    @Override
    public String toString() {
    	return "("+domain+";"+super.toString()+")["+objective+"]";
    }
}

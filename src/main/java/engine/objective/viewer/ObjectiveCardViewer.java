package engine.objective.viewer;

import engine.card.CardViewer;
import engine.objective.Domain;
import engine.objective.ObjectiveCard;

/**
 * 
 * @author Samuel Demont
 * Permet la récupération des informations d'une carte objectif
 */
public class ObjectiveCardViewer extends CardViewer<ObjectiveCard>{

	public ObjectiveCardViewer(ObjectiveCard card) {
		super(card);
	}
	/**
	 * @return Le nombre de carte à atteindre pour compléter la colonne contenant cette carte objective
	 */
	public int getObjective() {
        return viewed.getObjective();
    }
	/**
	 * @return Le domaine de la carte objectif
	 */
    public Domain getDomain() {
        return viewed.getDomain();
    }
}

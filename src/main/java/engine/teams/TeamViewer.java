package engine.teams;

import engine.influence.viewer.InfluenceCardContainerViewer;
import engine.objective.viewer.ObjectiveCardContainerViewer;
import engine.teams.controler.TeamControler;
import engine.util.Viewer;

/**
 * Permet de r�cup�rer les informations d'une �quipes
 * @author Samuel Demont
 *
 */
public class TeamViewer extends Viewer<Team>{
	public TeamViewer(Team team) {
		super(team);
	}
	
    /**
     * @return Un viewer permettant de r�cup�rer les informations de la d�fausse du joueur
     */
    public InfluenceCardContainerViewer getDiscardPile() {
    	return new InfluenceCardContainerViewer(viewed.getDiscardpile());
    }

    /**
     * @return Un viewer permettant de r�cup�rer les informations de la pioche du joueur
     */
    public InfluenceCardContainerViewer getStockPile() {
    	return new InfluenceCardContainerViewer(viewed.getStockpile());
    }
    
    /**
     * @return Un viewer permettant de r�cup�rer les informations des gains du joueur
     */
    public ObjectiveCardContainerViewer getGains() {
    	return new ObjectiveCardContainerViewer(viewed.getGains());
    }
    
    /**
     * @return Un viewer permettant de r�cup�rer les informations de la main du joueur
     */
    public InfluenceCardContainerViewer getHand() {
    	return new InfluenceCardContainerViewer(viewed.getHand());
    }
    
    /**
	 * Associe un controleur � une �quipe
	 * @param team
	 * @param controler
	 */
	public void setControler(TeamControler controler) {
		viewed.setControler(controler);
	}
    
}

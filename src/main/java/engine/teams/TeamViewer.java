package engine.teams;

import engine.influence.viewer.InfluenceCardContainerViewer;
import engine.objective.viewer.ObjectiveCardContainerViewer;
import engine.teams.controler.TeamControler;
import engine.util.Viewer;

/**
 * Permet de récupérer les informations d'une équipes
 * @author Samuel Demont
 *
 */
public class TeamViewer extends Viewer<Team>{
	public TeamViewer(Team team) {
		super(team);
	}
	
    /**
     * @return Un viewer permettant de récupérer les informations de la défausse du joueur
     */
    public InfluenceCardContainerViewer getDiscardPile() {
    	return new InfluenceCardContainerViewer(viewed.getDiscardpile());
    }

    /**
     * @return Un viewer permettant de récupérer les informations de la pioche du joueur
     */
    public InfluenceCardContainerViewer getStockPile() {
    	return new InfluenceCardContainerViewer(viewed.getStockpile());
    }
    
    /**
     * @return Un viewer permettant de récupérer les informations des gains du joueur
     */
    public ObjectiveCardContainerViewer getGains() {
    	return new ObjectiveCardContainerViewer(viewed.getGains());
    }
    
    /**
     * @return Un viewer permettant de récupérer les informations de la main du joueur
     */
    public InfluenceCardContainerViewer getHand() {
    	return new InfluenceCardContainerViewer(viewed.getHand());
    }
    
    /**
	 * Associe un controleur à une équipe
	 * @param team
	 * @param controler
	 */
	public void setControler(TeamControler controler) {
		viewed.setControler(controler);
	}
    
}

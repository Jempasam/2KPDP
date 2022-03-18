package engine.influence.viewer;

import engine.card.CardViewer;
import engine.influence.InfluenceCard;
import engine.influence.InfluenceStat;
import engine.influence.type.InfluenceCardType;
import engine.teams.TeamViewer;

/**
 * Permet de récupérer les informations d'une carte influence
 * @author Samuel Demont
 *
 */
public class InfluenceCardViewer extends CardViewer<InfluenceCard>{

	public InfluenceCardViewer(InfluenceCard card) {
		super(card);
	}
	
	public InfluenceStat getState() {return viewed.getState(); }
	    
    public float getPoints() {return viewed.getPoints();}
    
    public InfluenceCardType getType() {return viewed.getType();}
    
    public TeamViewer getTeam() {return new TeamViewer(viewed.getTeam());}
    
    public boolean isIgnored() {return viewed.isIgnored();}
    
    public boolean isRevealed() {return viewed.isRevealed();}

}

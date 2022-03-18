package engine.teams;

import engine.card.container.CardPile;
import engine.card.container.Hand;
import engine.card.shuffler.ICardShuffler;
import engine.influence.InfluenceCard;
import engine.influence.InfluenceStat;
import engine.influence.filter.SetTeamFilter;
import engine.objective.ObjectiveCard;
import engine.teams.controler.TeamControler;

/**
 * Repr�sente une �quipe/un joueur poss�dant une main, une pioche, une d�fausse, et un tas de cartes objectifs gagn�es
 * @author Samuel Demont
 */
public class Team {
    
    private CardPile<InfluenceCard> discardpile;
    private Hand<InfluenceCard> hand;
    private CardPile<InfluenceCard> stockpile;
    private CardPile<ObjectiveCard> gains;
    private TeamControler controler;
    private ICardShuffler shuffler;
    
    public Team() {
    	discardpile=new CardPile<>();
    	hand=new Hand<>();
    	gains=new CardPile<>();
    	stockpile=new CardPile<>();
	}
    
    /**
     * Cr�e une �quipe � partir d'une pioche et d'un nombre de carte.
     * @param stockpile Une pile dont l'�quipe aura une copie
     * @param hand_size Le nombre de carte dans sa main
     * @return La nouvelle �quipe
     */
    public static Team teamOfStockpile(CardPile<InfluenceCard> stockpile, int hand_size, ICardShuffler shuffler) {
    	Team retteam=new Team();
    	CardPile<InfluenceCard> sp=new CardPile<>();
    	retteam.shuffler=shuffler;
    	
    	for(InfluenceCard c : stockpile)sp.add(new InfluenceCard(c));
    	sp.compute(new SetTeamFilter(retteam));
    	retteam.setStockpile(sp);
    	
    	shuffler.shuffle(sp);
    	
    	retteam.drawUntil(hand_size);
    	
    	return retteam;
    }
    /**
     * 
     * @return la pioche de l'�quipe
     */
    public CardPile<InfluenceCard> getStockpile() {
        return this.stockpile;
    }
    /**
     * Change la pioche de l'�quipe
     * @param stockpile La nouvelle pioche
     */
    public void setStockpile(CardPile<InfluenceCard> stockpile) {
        this.stockpile = stockpile;
    }
    /**
     * 
     * @return La d�fausse de l'�quipe
     */
    public CardPile<InfluenceCard> getDiscardpile() {
        return this.discardpile;
    }
    /**
     * 
     * @param discardpile La d�fausse de l'�quipe
     */
    public void setDiscardpile(CardPile<InfluenceCard> discardpile) {
        this.discardpile = discardpile;
    }
    /**
     * 
     * @return La main de l'�quipe
     */
    public Hand<InfluenceCard> getHand() {
        return this.hand;
    }
    /**
     * Change la main de l'�quipe
     * @param hand La nouvelle main
     */
    public void setHand(Hand<InfluenceCard> hand) {
        this.hand = hand;
    }
    
    /**
     * 
     * @return Les gains de l'�quipes / Les cartes objectif gagn�es
     */
    public CardPile<ObjectiveCard> getGains() {
        return this.gains;
    }
    
    /**
     * Change les gains du joueur
     * @param gains Les nouveaux gains
     */
    public void setGains(CardPile<ObjectiveCard> gains) {
        this.gains = gains;
    }
    
    public ICardShuffler getShuffler() {
		return shuffler;
	}
	public void setShuffler(ICardShuffler shuffler) {
		this.shuffler = shuffler;
	}
	
	/**
     * Fais piocher une carte depuis le deck et l'ajoute dans la main
     */
    public void draw() {
    	if(stockpile.size()>0)hand.add(stockpile.draw());
    	else if (discardpile.size()>0) {
    		//on rem�lange la d�fausse en pioche, si la pioche est vide
    		stockpile=discardpile;
    		if(shuffler != null) shuffler.shuffle(stockpile);
    		stockpile.compute((c)->{
    			c.setState(InfluenceStat.ACTIVE);
        		c.reveal();
        		c.setPoints(c.getType().getPoints());
    		});
    		
    		discardpile=new CardPile<>();
    		
    		hand.add(stockpile.draw());
    	}
    }
    
    /**
     * Fais piocher des cartes jusqu'� en avoir un certain nombre dans la main
     * @param nb_card Le nombre de carte � atteindre
     */
    public void drawUntil(int nb_card) {
    	while(hand.size()<nb_card && (stockpile.size()>0||discardpile.size()>0) ) {
    		draw();
    	}
    }
    
    /**
     * 
     * @return Le controler de l'�quipe
     */
	public TeamControler getControler() {
		return controler;
	}
	
	/**
	 * Change le controler de l'�quipe
	 * @param controler
	 */
	public void setControler(TeamControler controler) {
		this.controler = controler;
	}
    
    
}

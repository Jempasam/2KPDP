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
 * Représente une équipe/un joueur possédant une main, une pioche, une défausse, et un tas de cartes objectifs gagnées
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
     * Crée une équipe à partir d'une pioche et d'un nombre de carte.
     * @param stockpile Une pile dont l'équipe aura une copie
     * @param hand_size Le nombre de carte dans sa main
     * @return La nouvelle équipe
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
     * @return la pioche de l'équipe
     */
    public CardPile<InfluenceCard> getStockpile() {
        return this.stockpile;
    }
    /**
     * Change la pioche de l'équipe
     * @param stockpile La nouvelle pioche
     */
    public void setStockpile(CardPile<InfluenceCard> stockpile) {
        this.stockpile = stockpile;
    }
    /**
     * 
     * @return La défausse de l'équipe
     */
    public CardPile<InfluenceCard> getDiscardpile() {
        return this.discardpile;
    }
    /**
     * 
     * @param discardpile La défausse de l'équipe
     */
    public void setDiscardpile(CardPile<InfluenceCard> discardpile) {
        this.discardpile = discardpile;
    }
    /**
     * 
     * @return La main de l'équipe
     */
    public Hand<InfluenceCard> getHand() {
        return this.hand;
    }
    /**
     * Change la main de l'équipe
     * @param hand La nouvelle main
     */
    public void setHand(Hand<InfluenceCard> hand) {
        this.hand = hand;
    }
    
    /**
     * 
     * @return Les gains de l'équipes / Les cartes objectif gagnées
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
    		//on remélange la défausse en pioche, si la pioche est vide
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
     * Fais piocher des cartes jusqu'à en avoir un certain nombre dans la main
     * @param nb_card Le nombre de carte à atteindre
     */
    public void drawUntil(int nb_card) {
    	while(hand.size()<nb_card && (stockpile.size()>0||discardpile.size()>0) ) {
    		draw();
    	}
    }
    
    /**
     * 
     * @return Le controler de l'équipe
     */
	public TeamControler getControler() {
		return controler;
	}
	
	/**
	 * Change le controler de l'équipe
	 * @param controler
	 */
	public void setControler(TeamControler controler) {
		this.controler = controler;
	}
    
    
}

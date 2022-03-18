package engine.influence.type;

import engine.card.container.CardContainer;
import engine.game.KPDPControler;
import engine.influence.EffectContext;
import engine.influence.InfluenceCard;

/**
 * Représente un type de carte influence avec un nombre de point et un effet associé
 * @author Samuel Demont
 *
 */
public class InfluenceCardType {
    private float points;
    private int effect_priority;
    private boolean always_active;
    
	public InfluenceCardType(float points, int effect_priority, boolean always_active) {
		super();
		this.always_active=always_active;
		this.points = points;
		this.effect_priority = effect_priority;
	}
	
	public float getPoints() { return this.points; }
    public int getEffectPriority()  {return this.effect_priority; }
    public boolean isAlwaysActive() { return always_active; }
    
    /**
    * @generated
    */
    public void useEndEffect(EffectContext context){
    	
    }
    
    /**
    * @generated
    */
    public void useRevealEffect(EffectContext context) {
    	
    }
    
    public void discard(CardContainer<InfluenceCard> discardpile, InfluenceCard card) {
		discardpile.add(card);
	}

    @Override
    public String toString() {
    	String n=KPDPControler.CARDTYPE_REGISTRY.getId(this);
    	return (n==null ? "noname" : n)+"("+points+")";
    }
    
}

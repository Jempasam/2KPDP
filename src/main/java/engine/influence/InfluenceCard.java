package engine.influence;

import java.util.HashMap;
import java.util.Map;

import engine.card.Card;
import engine.influence.type.InfluenceCardType;
import engine.kpdpevents.InfluenceEffectEvent;
import engine.teams.Team;

/**
 * Représente un exemplaire d'une carte influence
 * @author Samuel Demont
 *
 */
public class InfluenceCard extends Card {
    
    private InfluenceStat state;
    private float points;
    private InfluenceCardType type;
    private Team owner;
    private Map<String,Object> data;
    
    public InfluenceCard(InfluenceCardType type, Team owner) {
    	super();
		this.type=type;
		points=type.getPoints();
		this.owner=owner;
		state=InfluenceStat.ACTIVE;
		data=new HashMap<>();
	}
    public InfluenceCard(InfluenceCard copied){
    	super(copied);
    	state=copied.state;
    	points=copied.points;
    	type=copied.type;
    	owner=copied.owner;
    	data=new HashMap<>(copied.data);
    }
    
    public InfluenceStat getState() {return this.state; }
    public void setState(InfluenceStat state) {this.state = state;}
    
    public float getPoints() {return this.points;}
    public void setPoints(float points) {this.points = points;}
    
    public InfluenceCardType getType() {return this.type;}
    public void setType(InfluenceCardType type) { this.type = type;}
    
    public Team getTeam() {return this.owner;}
    public void setTeam(Team newowner) {this.owner = newowner;}
    
    /**
     * Utilise l'effet de fin de manche
     * @param context
     */
    public void tryEndEffect(EffectContext context){
    	if(canUseEndEffect())
		{
    		context.getGame().handleIEffectEvent(new InfluenceEffectEvent(type, context,true));
    		setState(InfluenceStat.ACTIVATED);
    		type.useEndEffect(context);
		}
    }
    
    /**
     * Révèle la carte en esseyant de déclencher son effet de révélement
     * @param context Le contexte de révèlement
     */
    public void tryRevealEffect(EffectContext context) {
    	if(canUseRevealEffect())
		 {
    		 reveal();
			 setState(InfluenceStat.ACTIVATED);
			 context.getGame().handleIEffectEvent(new InfluenceEffectEvent(type, context,false));
			 type.useRevealEffect(context);
		 }
    	else {
    		reveal();
    	}
    }
    public boolean canUseRevealEffect() {
    	return (!isRevealed() && getState()==InfluenceStat.ACTIVE) || (type.isAlwaysActive() && getState()!=InfluenceStat.ELIMINATED && getState()!=InfluenceStat.ACTIVATED );
    }
    public boolean canUseEndEffect() {
    	return (isRevealed() && getState()==InfluenceStat.ACTIVE) || (type.isAlwaysActive() && getState()!=InfluenceStat.ELIMINATED && getState()!=InfluenceStat.ACTIVATED );
    }
    public boolean isIgnored() {
    	return getState()==InfluenceStat.ELIMINATED;
    }
    public void setCustomData(String key, Object obj) {
    	data.put(key, obj);
    }
    
    public Object getCustomData(String key, Class<?> type) {
    	Object obj=data.get(key);
    	if(obj!=null && type.isInstance(obj))return obj;
    	return null;
    }
    
    public Object removeCustomData(String key, Class<?> type) {
    	Object obj=data.get(key);
    	if(obj!=null && type.isInstance(obj)) {
    		return data.remove(key);
    	}
    	return null;
    }
    
    @Override
    public String toString() {
    	return "("+type+";"+super.toString()+")["+points+"]";
    }
    
    public void discard() {
    	if(type==null)owner.getDiscardpile().add(this);
    	else type.discard(owner.getDiscardpile(), this);
    }
}

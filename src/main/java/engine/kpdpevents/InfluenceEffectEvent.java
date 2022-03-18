package engine.kpdpevents;

import engine.events.Event;
import engine.game.KPDPControler;
import engine.influence.EffectContext;
import engine.influence.type.InfluenceCardType;

public class InfluenceEffectEvent extends Event {
	
	private InfluenceCardType type;
	private EffectContext context;
	private Object response;
	private boolean isEndEffect;
	
	public InfluenceEffectEvent(InfluenceCardType type, EffectContext context, boolean isEndEffect) {
		super();
		this.type = type;
		this.context = context;
		this.isEndEffect=isEndEffect;
	}
	public void respond(Object res) {
		response=res;
	}
	public Object getResponse() {
		return response;
	}
	public InfluenceCardType getType() {
		return type;
	}
	public EffectContext getContext() {
		return context;
	}
	public boolean isEndEffect()
	{
		return isEndEffect;
	}
	public boolean isRevealEffect() {
		return !isEndEffect;
	}
	@Override
	public String toString() {
		return "Activation de l'effet de "+KPDPControler.CARDTYPE_REGISTRY.getId(type)+" à la position ("+context.getColumnNb()+";"+context.getLineNb()+")"+(isEndEffect ? " en fin de manche" : "");
	}
	
}

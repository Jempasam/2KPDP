package interfaceTable.component.kpdp;

import engine.game.GameViewer;
import engine.game.KPDPControler;
import engine.objective.viewer.ObjectiveCardViewer;
import interfaceCommon.component.kpdp.ObjectiveCardComponent;

/**
 * Un composant graphique qui affiche une carte objectif en prenant les informations depuis un objet ObjectiveCardViewer
 * @author Samuel Demont
 *
 */
public class TableObjectiveCardComponent extends ObjectiveCardComponent{
	private ObjectiveCardViewer objective_card;
	
	public TableObjectiveCardComponent(ObjectiveCardViewer oc, GameViewer g) {
		super(KPDPControler.DOMAIN_REGISTRY.getId(oc.getDomain()), oc.getObjective(), oc.isRevealed());
		objective_card=oc;
		
	}
	public void update() {
		super.update(KPDPControler.DOMAIN_REGISTRY.getId(objective_card.getDomain()), objective_card.getObjective(), objective_card.isRevealed());
	}
	
	public ObjectiveCardViewer getObjectiveCard() {
		return objective_card;
	}
	
}

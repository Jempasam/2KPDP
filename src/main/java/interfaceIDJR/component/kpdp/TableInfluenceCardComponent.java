package interfaceIDJR.component.kpdp;

import engine.game.GameViewer;
import engine.game.KPDPControler;
import engine.influence.viewer.InfluenceCardViewer;
import interfaceCommon.JFXUtils.ColorHelper;
import interfaceCommon.component.kpdp.InfluenceCardComponent;

/**
 * Un composant graphique qui affiche une carte influence en prenant les informations depuis un objet InfluenceCardViewer
 * @author Samuel Demont
 *
 */
public class TableInfluenceCardComponent extends InfluenceCardComponent{
	private InfluenceCardViewer influence_card;
	private GameViewer game;
	
	public TableInfluenceCardComponent(InfluenceCardViewer ic, GameViewer g) {
		super(
				g.idOfTeam(ic.getTeam()),
				KPDPControler.CARDTYPE_REGISTRY.getId(ic.getType()),
				ColorHelper.colorOfInfluenceState(ic.getState()),
				ic.isRevealed(),
				ic.getPoints()
				);
		influence_card=ic;
		game=g;
	}
	public void update() {
		super.update(
				game.idOfTeam(influence_card.getTeam()),
				KPDPControler.CARDTYPE_REGISTRY.getId(influence_card.getType()),
				ColorHelper.colorOfInfluenceState(influence_card.getState()),
				influence_card.isRevealed(),
				influence_card.getPoints()
				);
	}
	
	public InfluenceCardViewer getInfluenceCard() {
		return influence_card;
	}
}

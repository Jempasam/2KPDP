package engine.kpdpevents.controlerevent;

import engine.game.Game;
import engine.influence.viewer.InfluenceCardContainerViewer;
import engine.teams.Team;

public class AskCardEvent extends ControlerEvent {
	Integer cardpos;

	public AskCardEvent(Game game, String reason, Team team, int try_counter) {
		super(game, reason, team, try_counter);
		cardpos=null;
	}
	public InfluenceCardContainerViewer getAskedHand() {
		return getAsked().getHand();
	}
	public void setCardToUse(int card_position_in_hand) {
		cardpos=card_position_in_hand;
	}
	public Integer getCardToUse() {
		return cardpos;
	}
}

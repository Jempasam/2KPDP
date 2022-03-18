package engine.kpdpevents.gameevent;

import engine.game.Game;
import engine.influence.viewer.InfluenceCardViewer;
import engine.teams.Team;
import engine.teams.TeamViewer;

public class CardSelectedEvent extends GameEvent{
	
	private TeamViewer player;
	private int card_selected;
	private int try_counter;
	private String reason;
	
	public CardSelectedEvent(Game game, Team player, int card_selected, int try_counter, String reason) {
		super(game);
		this.player = new TeamViewer(player);
		this.card_selected = card_selected;
		this.try_counter = try_counter;
		this.reason=reason;
	}

	public TeamViewer getPlayer() {
		return player;
	}
	
	public String getReason() {
		return reason;
	}

	public int getSelectedCardIndex() {
		return card_selected;
	}
	
	public InfluenceCardViewer getSelectedCard() {
		return player.getHand().get(card_selected);
	}

	public int getTryCounter() {
		return try_counter;
	}
	
	@Override
	public String toString() {
		return "Le joueur "+game.idOfTeam(player)+" a choisi la carte n°"+card_selected+" pour "+reason+".";
	}

}

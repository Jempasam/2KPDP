package engine.game;

import engine.card.CardViewer;
import engine.events.EventRegistry;
import engine.field.Column;
import engine.field.ColumnViewer;
import engine.influence.InfluenceCard;
import engine.influence.type.InfluenceCardType;
import engine.kpdpevents.InfluenceEffectEvent;
import engine.kpdpevents.controlerevent.ControlerEvent;
import engine.kpdpevents.gameevent.GameEvent;
import engine.util.Viewers;

public class GameControler extends GameViewer{
	
	public GameControler(Game viewed) {
		super(viewed);
	}
	
	/**
	 * Passe au prochain tour.
	 * @return false si la partie est terminée sinon true
	 */
	public synchronized boolean nextTurn() {
		return viewed.nextTurn();
	}
	
	/**
	 * Joue la carte card sur la colonne col.
	 * La colonne doit appartenir au terrain et card à la main du joueur à qui c'est le tour
	 * @param card La carte à jouer
	 * @param col La colonne sur laquelle jouer
	 * @return true si la carte a pu être jouée sinon false
	 */
	public synchronized boolean playCard(CardViewer<InfluenceCard> card,ColumnViewer col) {
		return viewed.playCard(Viewers.getOf(card), (Column)Viewers.getOf(col));
	}
	
	/**
	 * Joue la carte à l'index indiquée sur la colonne à l'index indiquée
	 * @param card L'index de la carte à jouer dans la main du joueur à qui c'est le tour
	 * @param col L'index de la colonne ou jouer, dans le terrain
	 * @return true si la carte a pu être jouée sinon false
	 */
	public synchronized boolean playCard(int index_card, int index_column) {
		return viewed.playCard(viewed.getActualHand().get(index_card), viewed.getField().get(index_column));
	}
	
	/**
	 * Joue la permière carte du type indiqué qui est dans la main du joueur, sur la colonne à l'index indiqué
	 * @param card Le type de la carte à joueur
	 * @param col L'index de la colonne ou jouer, dans le terrain
	 * @return true si la carte a pû être jouée sinon false
	 */
	public synchronized boolean playFirstOfTypeAt(InfluenceCardType type, int index_column) {
		InfluenceCard cardtoplay=viewed.getActualHand().findFor((ic)->ic.getType()==type);
		return cardtoplay==null ? null : viewed.playCard(cardtoplay, viewed.getField().get(index_column));
	}
	
	/**
	  * Joue automatiquement le tour
	  * @return true si le tour a été joué automatiquement sinon false
	  */
	public synchronized void launch() {
		if(viewed.takeDecision())nextTurn();
	}
	
	public synchronized void setInfluenceEffectEventRegistry(EventRegistry<InfluenceEffectEvent> reg) {
		viewed.INFLUENCE_EVENTS=reg;
	}
	
	public synchronized void setGameEventRegistry(EventRegistry<GameEvent> reg) {
		viewed.GAME_EVENTS=reg;
	}
	
	public synchronized void setControlerEventRegistry(EventRegistry<ControlerEvent> reg) {
		viewed.CONTROLER_EVENTS=reg;
	}
	
	public void setAutoPlay(boolean autoplay) {
		viewed.setAutoPlay(autoplay);
	}
	
}

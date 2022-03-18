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
	 * @return false si la partie est termin�e sinon true
	 */
	public synchronized boolean nextTurn() {
		return viewed.nextTurn();
	}
	
	/**
	 * Joue la carte card sur la colonne col.
	 * La colonne doit appartenir au terrain et card � la main du joueur � qui c'est le tour
	 * @param card La carte � jouer
	 * @param col La colonne sur laquelle jouer
	 * @return true si la carte a pu �tre jou�e sinon false
	 */
	public synchronized boolean playCard(CardViewer<InfluenceCard> card,ColumnViewer col) {
		return viewed.playCard(Viewers.getOf(card), (Column)Viewers.getOf(col));
	}
	
	/**
	 * Joue la carte � l'index indiqu�e sur la colonne � l'index indiqu�e
	 * @param card L'index de la carte � jouer dans la main du joueur � qui c'est le tour
	 * @param col L'index de la colonne ou jouer, dans le terrain
	 * @return true si la carte a pu �tre jou�e sinon false
	 */
	public synchronized boolean playCard(int index_card, int index_column) {
		return viewed.playCard(viewed.getActualHand().get(index_card), viewed.getField().get(index_column));
	}
	
	/**
	 * Joue la permi�re carte du type indiqu� qui est dans la main du joueur, sur la colonne � l'index indiqu�
	 * @param card Le type de la carte � joueur
	 * @param col L'index de la colonne ou jouer, dans le terrain
	 * @return true si la carte a p� �tre jou�e sinon false
	 */
	public synchronized boolean playFirstOfTypeAt(InfluenceCardType type, int index_column) {
		InfluenceCard cardtoplay=viewed.getActualHand().findFor((ic)->ic.getType()==type);
		return cardtoplay==null ? null : viewed.playCard(cardtoplay, viewed.getField().get(index_column));
	}
	
	/**
	  * Joue automatiquement le tour
	  * @return true si le tour a �t� jou� automatiquement sinon false
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

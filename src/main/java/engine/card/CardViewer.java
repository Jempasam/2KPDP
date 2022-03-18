package engine.card;

import engine.util.Viewer;

/**
 * Permet de récupérer les informations d'une carte
 * @author Samuel Demont
 *
 * @param <T>
 */
public class CardViewer<T extends Card> extends Viewer<T> {
	public CardViewer(T card) {
		super(card);
	}
	public boolean isRevealed() {
    	return viewed.isRevealed();
    }
}

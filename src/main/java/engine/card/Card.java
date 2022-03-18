package engine.card;


/**
 * Représente une carte de jeu
 * @author Samuel Demont
 *
 */
public abstract class Card{
    private boolean revealed;
    
    public Card() {
		revealed=true;
	}
	public Card(Card c) {
		revealed=c.revealed;
	}
    /**
     * Met la carte face visible.
     */
    public void reveal() {revealed=true;}
    /**
     * Met la carte face cachée.
     */
    public void hide() {revealed=false;}
    /**
     * Retourner la carte de visible à cachée et vice-versa.
     */
    public void turnBack() {revealed=!revealed;}
    /**
     * @return true si la carte est face visible.
     */
    public boolean isRevealed() {return revealed;}
    
    @Override
    public boolean equals(Object obj) {
    	return obj instanceof Card && revealed==((Card)obj).revealed;
    }
    @Override
    public String toString() {
    	return revealed ? "revealed" : "hidden";
    }
}

package engine.teams.controler;

import engine.game.GameViewer;
import engine.teams.TeamViewer;

/**
 * Permet de controler une �quipe en prenant des d�cisions.
 * @author Samuel Demont
 *
 */
public interface TeamControler {
	/**
	 * Demande une carte
	 * @param team L'�quipe que controle le controleur
	 * @param game La partie
	 * @param reason Un texte d�crivant la raison de la demande. Exemple : "playCard"
	 * @param try_counter Le nombre de fois que la demande a �t� refaite si les r�ponses pr�c�dentes n'�taient pas valide.
	 * @return l'index de la carte dans la main du joueur
	 */
	
	int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter);
	/**
	 * Demande une colonne
	 * @param team L'�quipe que controle le controleur
	 * @param game La partie
	 * @param reason Un texte d�crivant la raison de la demande. Exemple : "playCard"
	 * @param try_counter Le nombre de fois que la demande a �t� refaite si les r�ponses pr�c�dentes n'�taient pas valide.
	 * @return l'index de la colonne dans le terrain
	 */
	int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter);
	
	/**
	 * Demande un commentaire
	 * @param team L'�quipe que controle le controleur
	 * @param game La partie
	 * @param reason Un texte d�crivant la raison de la demande. Exemple : "playCard"
	 * @param try_counter Le nombre de fois que la demande a �t� refaite si les r�ponses pr�c�dentes n'�taient pas valide.
	 * @return Le commentaire
	 */
	String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter);
}

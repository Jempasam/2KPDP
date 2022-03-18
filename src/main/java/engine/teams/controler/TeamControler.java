package engine.teams.controler;

import engine.game.GameViewer;
import engine.teams.TeamViewer;

/**
 * Permet de controler une équipe en prenant des décisions.
 * @author Samuel Demont
 *
 */
public interface TeamControler {
	/**
	 * Demande une carte
	 * @param team L'équipe que controle le controleur
	 * @param game La partie
	 * @param reason Un texte décrivant la raison de la demande. Exemple : "playCard"
	 * @param try_counter Le nombre de fois que la demande a été refaite si les réponses précédentes n'étaient pas valide.
	 * @return l'index de la carte dans la main du joueur
	 */
	
	int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter);
	/**
	 * Demande une colonne
	 * @param team L'équipe que controle le controleur
	 * @param game La partie
	 * @param reason Un texte décrivant la raison de la demande. Exemple : "playCard"
	 * @param try_counter Le nombre de fois que la demande a été refaite si les réponses précédentes n'étaient pas valide.
	 * @return l'index de la colonne dans le terrain
	 */
	int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter);
	
	/**
	 * Demande un commentaire
	 * @param team L'équipe que controle le controleur
	 * @param game La partie
	 * @param reason Un texte décrivant la raison de la demande. Exemple : "playCard"
	 * @param try_counter Le nombre de fois que la demande a été refaite si les réponses précédentes n'étaient pas valide.
	 * @return Le commentaire
	 */
	String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter);
}

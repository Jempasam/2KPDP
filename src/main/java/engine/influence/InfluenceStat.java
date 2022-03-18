package engine.influence;

/**
 * Représente l'état d'une carte influence.
 * @author Samuel Demont
 *
 */
public enum InfluenceStat {
	/**
	 * Etat par défaut
	 */
	ACTIVE,
	/**
	 * L'effet de la carte est désactivé pendant un tour
	 */
	INACTIVE,
	
	CANCELED,
	/**
	 * L'effet de la carte a été activé
	 */
	ACTIVATED,
	/**
	 * L'effet de la carte est désactivé et les points de la cartes ne sont pas comptés
	 */
	ELIMINATED
}

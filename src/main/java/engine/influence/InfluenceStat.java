package engine.influence;

/**
 * Repr�sente l'�tat d'une carte influence.
 * @author Samuel Demont
 *
 */
public enum InfluenceStat {
	/**
	 * Etat par d�faut
	 */
	ACTIVE,
	/**
	 * L'effet de la carte est d�sactiv� pendant un tour
	 */
	INACTIVE,
	
	CANCELED,
	/**
	 * L'effet de la carte a �t� activ�
	 */
	ACTIVATED,
	/**
	 * L'effet de la carte est d�sactiv� et les points de la cartes ne sont pas compt�s
	 */
	ELIMINATED
}

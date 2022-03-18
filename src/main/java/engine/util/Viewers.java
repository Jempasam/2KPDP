package engine.util;

/**
 * Cette classe est réservée au groupe MDJ, son utilisation ailleurs est formellement interdit
 * @author Samuel Demont
 * 
 */
public class Viewers {
	/**
	 * Récupère l'objet regardé par un viewer
	 * @param <T>
	 * @param viewer Le viewer
	 * @return l'objet regardé
	 */
	public static <T> T getOf(Viewer<T> viewer) {
		return viewer.viewget();
	}
}

package engine.util;

/**
 * Cette classe est r�serv�e au groupe MDJ, son utilisation ailleurs est formellement interdit
 * @author Samuel Demont
 * 
 */
public class Viewers {
	/**
	 * R�cup�re l'objet regard� par un viewer
	 * @param <T>
	 * @param viewer Le viewer
	 * @return l'objet regard�
	 */
	public static <T> T getOf(Viewer<T> viewer) {
		return viewer.viewget();
	}
}

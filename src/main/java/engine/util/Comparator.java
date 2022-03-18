package engine.util;

/**
 * Une classe fonctionnelle qui permet la comparaison de deux objet du même type
 * @author Samuel Demont
 *
 * @param <T>
 */
public interface Comparator <T>{
		int compare(T a, T b);
}


package engine.util;

/**
 * Sert d'interface en permettant de récupèrer les informations d'un objet regardé, sans pouvoir le modifier.
 * @author Samuel Demont
 *
 * @param <T>
 */
public abstract class Viewer<T> {
	protected T viewed;
	
	protected Viewer(T viewed) {
		this.viewed=viewed;
	}
	
	T  viewget(){
		return viewed;
	}
	/**
	 * @return true si l'object regardé est égale au obj
	 */
	public boolean viewedEqual(T obj) { 
		return viewed.equals(obj);
	}
	/**
	 * @return true si l'object regardé est égale a l'objet regardé par vw
	 */
	public boolean viewedsEquals(Viewer<T> vw) {
    	return viewed.equals(vw.viewed);
    }
	/**
	 * @return true si l'object regardé est le même que obj
	 */
	public boolean view(T obj) {
		return viewed==obj;
	}
	/**
	 * @return true si l'object regardé est le même que l'objet regardé par vw
	 */
	public boolean viewSame(Viewer<T> vw){
		return viewed==vw.viewed;
	}
	
	/**
	 * @return Le hashcode de l'objet regardé
	 */
	public int viewedHashCode() {
		return viewed.hashCode();
	}
	
	@Override
	public String toString() {
		return viewed.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Viewer && ((Viewer<?>)obj).viewed==viewed;
	}
	
	@Override
	public int hashCode() {
		return viewed.hashCode();
	}
}

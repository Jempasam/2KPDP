package engine.util;

/**
 * Sert d'interface en permettant de r�cup�rer les informations d'un objet regard�, sans pouvoir le modifier.
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
	 * @return true si l'object regard� est �gale au obj
	 */
	public boolean viewedEqual(T obj) { 
		return viewed.equals(obj);
	}
	/**
	 * @return true si l'object regard� est �gale a l'objet regard� par vw
	 */
	public boolean viewedsEquals(Viewer<T> vw) {
    	return viewed.equals(vw.viewed);
    }
	/**
	 * @return true si l'object regard� est le m�me que obj
	 */
	public boolean view(T obj) {
		return viewed==obj;
	}
	/**
	 * @return true si l'object regard� est le m�me que l'objet regard� par vw
	 */
	public boolean viewSame(Viewer<T> vw){
		return viewed==vw.viewed;
	}
	
	/**
	 * @return Le hashcode de l'objet regard�
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

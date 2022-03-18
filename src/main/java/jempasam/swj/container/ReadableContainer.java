package jempasam.swj.container;

import java.util.Iterator;

/**
 * Un conteneur générique immuable
 * @author Samuel Demont
 *
 * @param <T>
 */
public interface ReadableContainer<T> extends SizedIterable<T>{
	
	/**
	 * @param position
	 * @return la valeur à la position donnée
	 */
	public T get(int position);
    
    /**
     * Récupère l'élément à la position indiquée mais en partant de la fin
	 * @param position
	 * @return la valeur à la position donnée
	 */
	default T getFromEnd(int position) {
		return get(size()-1-position);
	}
    
    /**
     * @return Une valeur aléatoire du conteneur
     */
    public default T getRandom() {
        return get((int)(Math.random()*size()));
    }
    
    /**
     * Récupère la position d'une valeur dans le conteneur
     * @param obj La valeur à trouver
     * @return La position de la valeur ou -1 si la valeur n'existe pas dans le conteneur
     */
    public default int indexOf(Object obj) {
    	int size=size();
    	for(int i=0; i<size; i++) {
    		if(get(i)==obj)return i;
    	}
    	return -1;
    }
    
    /**
     * @return Le dernier objet du conteneur ou null si le conteneur est vide
     */
    default T last() {
    	if(size()>0)return get(size()-1);
    	else return null;
    }
    
    /**
     * Remplie un conteneur avec les éléments avec les éléments d'index index_min à index_min+size .
     * @param c Le conteneur à remplir
     * @param index_min L'index du premier élément
     * @param size Le nombre d'élément
     * @return Le conteneur rempli.
     */
    default Container<T> subContainer(Container<T> c, int index_min, int size){
    	for(int i=0; i<size && i+index_min<size(); i++) {
    		c.add(get(index_min+i));
    	}
    	return c;
    }
    
    @Override
    default Iterator<T> iterator() {
    	return new ContainerIterator<>(this);
    }
    
    default String asString() {
    	StringBuilder sb=new StringBuilder();
    	for(T o : this)sb.append("[").append(o).append("]");
    	return sb.toString();
    }
}

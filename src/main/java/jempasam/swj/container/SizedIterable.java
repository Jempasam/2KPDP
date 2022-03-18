package jempasam.swj.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface SizedIterable<T> extends Iterable<T>{
	
	/**
	 * @return le nombre de valeurs contenues dans le conteneur
	 */
    public int size();
	
	default Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
	
	/**
     * Retourne une valeur du conteneur égale à celle passée en paramètre
     * @param comp La valeur à comparer
     * @return La valeur trouvée ou null si la valeur n'existe pas dans le conteneur
     */
    public default T findEquals(T comp){
    	for(T c : this)if(comp.equals(c))return c;
    	return null;
    }
    
    /**
     * Retourne une liste des valeurs du conteneur égales à celle passée en paramètre
     * @param comp La valeur à comparer
     * @return La liste des valeurs trouvées ou null si la valeur n'existe pas dans le conteneur
     */
    public default List<T> findAllEquals(T comp){
    	List<T> ret=new ArrayList<>();
    	for(T c : this)if(comp.equals(c))ret.add(c);
    	return ret;
    }
    
    /**
     * Trouve la première valeur qui correspondent aux conditions du prédicat
     * @param predicate Le prédicat
     * @return La première valeur trouvée
     */
    public default T findFor(Predicate<T> predicate) {
    	for(T c : this)if(predicate.test(c))return c;
    	return null;
    }
    
    /**
     * Trouve toutes les valeurs qui correspondent aux conditions du prédicat
     * @param predicate Le prédicat
     * @return Une liste des valeurs trouvées
     */
    public default List<T> findAllFor(Predicate<T> predicate) {
    	List<T> ret=new ArrayList<>();
    	for(T c : this)if(predicate.test(c))ret.add(c);
    	return ret;
    }
    
    /**
     * Teste si une valeur du conteneur correspond aux conditions du prédicat
     * @param predicate
     * @return
     */
    public default boolean testIfOne(Predicate<T> predicate) {
    	for(T c : this)if(predicate.test(c))return true;
    	return false;
    }
    
    /**
     * Teste si toutes les valeurs du conteneur correspond aux conditions du prédicat
     * @param predicate
     * @return
     */
    public default boolean testIfAll(Predicate<T> predicate) {
    	for(T c : this)if(!predicate.test(c))return false;
    	return true;
    }
    
	 /**
     * Calcule la somme des retours de la fonction sur chaque élément
     * @param func La fonction
     * @return La somme des retours
     */
    default int sum(ToIntFunction<T> func){
    	int sum=0;
    	for(T a : this)sum+=func.applyAsInt(a);
    	return sum;
    }
    
    /**
     * Compte le nombre d'élément qui réponde aux conditions du prédicat
     * @param pred Le prédicat
     * @return Le nombre d'élément
     */
    default int count(Predicate<T> pred){
    	int sum=0;
    	for(T a : this)if(pred.test(a))sum++;
    	return sum;
    }
    
    /**
     * Récupère le meilleur élément d'après un comparateur
     * @param isbetter Le comparateur qui détermine si le premier élément passé en paramètre est meilleur que le deuxième
     * @return Le meilleur
     */
    default T getBetter(BiPredicate<T,T> isbetter){
    	Iterator<T> it=iterator();
    	if(!it.hasNext())return null;
    	T better=it.next();
    	for(T a : this) if(isbetter.test(a, better)) better=a;
    	return better;
    }
    
    /**
     * Récupère le meilleur élément d'après un comparateur
     * @param isbetter Le comparateur qui détermine si le premier élément passé en paramètre est meilleur que le deuxième
     * @return Le meilleur
     */
    default T getFirstHigher(ToIntFunction<T> toint){
    	Iterator<T> it=iterator();
    	if(!it.hasNext())return null;
    	T better=it.next();
    	int betterscore=toint.applyAsInt(better);
    	
    	for(T a : this) {
    		int newscore=toint.applyAsInt(a);
    		if(newscore>betterscore) {
    			better=a;
        		betterscore=newscore;
        	}
    	}
    	return better;
    }
    
    /**
     * Test si un objet existe dans le conteneur
     * @param obj L'objet à trouver
     * @return true si l'objet existe dans le conteneur.
     */
    public default boolean contains(Object obj) {
		for(T o : this)if(o==obj)return true;
		return false;
	}
    
    /**
     * Test si un objet égal existe dans le conteneur
     * @param obj L'objet à comparer
     * @return true si un objet égal à l'objet passé en paramètre existe dans le tableau
     */
    public default boolean containsEquals(Object obj) {
		for(T o : this)if(obj.equals(o))return true;
		return false;
	}
    
    /**
     * @return true si le conteneur est vide sinon false
     */
    default boolean isEmpty() {
    	return size()==0;
    }
    
    /**
     * Test si un ensemble d'objet est inclus dans le conteneur
     * @param obj L'ensemble qui doit être inclus
     * @return true si l'ensemble est inclus
     */
    default boolean containsAll(ReadableContainer<?> c) {
    	for( Object e : c ) {
    		if(!contains(e))return false;
    	}
    	return true;
    }
    
    /**
     * @return un tableau contenant les mêmes objet de le conteneur
     */
    default Object[] toArray() {
    	Object[] ret=new Object[size()];
    	int i=0;
    	for(Object o : this) {
    		ret[i]=o;
    		i++;
    	}
    	return ret;
    }
}

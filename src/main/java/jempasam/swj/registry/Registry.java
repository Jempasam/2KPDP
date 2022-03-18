package jempasam.swj.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * Permet de lier des objets à  des identifiant
 * @author Samuel Demont
 *
 * @param <T>
 */
public class Registry<T> {
	Map<String,T> registred;
	Map<T,String> registred_reverse;
	
	public Registry() {
		registred=new HashMap<>();
		registred_reverse=new HashMap<>();
	}
	
	/**
	 * Associe un objet à  un identifiant
	 * @param identifiant
	 * @param objet
	 * @throws IdAlreadyUserException
	 * @throws AlreadyRegistredException
	 */
	public void register(String id,T obj) throws IdAlreadyUserException,AlreadyRegistredException{
		Object already=registred.get(id);
		if(already!=null) {
			throw new IdAlreadyUserException("Id "+id+" already used in this registry.", id, already);
		}
		
		String already_id=registred_reverse.get(obj);
		if(already_id!=null) {
			throw new AlreadyRegistredException(obj+" already registered.", already_id, obj);
		}
		else {
			registred.put(id, obj);
			registred_reverse.put(obj, id);
		}
	}
	
	public String getRandomKey() {
		Object[] keys=registred.keySet().toArray();
		return (String)keys[(int)(keys.length*Math.random())];
	}
	
	/**
	 * Récupère un objet associé à un id
	 * @param l'id
	 * @return
	 */
	public T get(String id) {
		return registred.get(id);
	}
	
	/**
	 * Récupère un objet associé à un id si il existe sinon renvoie une valeur par defaut
	 * @param id
	 * @param dflt Valeur par défaut
	 * @return
	 */
	public T getOrDefault(String id, T dflt) {
		T ret=get(id);
		return ret==null ? dflt : ret;
	}
	
	/**
	 * Récupère l'id assocé à  un objet
	 * @param obj
	 * @return
	 */
	public String getId(T obj) {
		return registred_reverse.get(obj);
	}
	
	/**
	 * Récupère l'id assocé à un objet si il existe sinon renvoie une valeur par defaut
	 * @param obj
	 * @param dflt Valeur par défaut
	 * @return
	 */
	public String getIdOrDefault(T obj, String dflt) {
		String ret=getId(obj);
		return ret==null ? dflt : ret;
	}
	
	@Override
	public String toString() {
		return registred.toString();
	}
}

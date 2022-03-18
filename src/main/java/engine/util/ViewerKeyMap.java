package engine.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ViewerKeyMap<E,T extends Viewer<E>, V>{
	private Map<E,V> internal;
	
	public ViewerKeyMap() {
		internal=new HashMap<>();
	}
	public ViewerKeyMap(Map<E,V> map) {
		internal=map;
	}
	
	public int size() {return internal.size();}
	public boolean isEmpty() {return internal.isEmpty();}

	public boolean containsKey(T key) {return internal.containsKey(Viewers.getOf(key));}
	public boolean containsValue(V value) {return internal.containsValue(value);}
	
	public V get(T key) {return internal.get(Viewers.getOf(key));}
	public void put(T key, V value) {internal.put(Viewers.getOf(key), value);}

	public V remove(T key) {return internal.remove(Viewers.getOf(key));}
	public void clear() {internal.clear();}
	
	public Collection<V> values(){return internal.values();}
}

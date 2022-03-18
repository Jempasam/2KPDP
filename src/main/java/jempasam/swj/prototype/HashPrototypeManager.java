package jempasam.swj.prototype;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HashPrototypeManager<T extends Prototype> implements PrototypeManager<T>{
	
	Map<String, T> content;
	
	
	public HashPrototypeManager() {
		content=new HashMap<>();
	}
	
	
	@Override
	public T get(String name) {return content.get(name);}
	
	@Override
	public T create(String name) {
		T proto=get(name);
		if(proto==null)return null;
		else return (T)proto.clone();
	}
	
	@Override
	public int size() { return content.size(); }
	
	@Override
	public Iterator<Entry<String, T>> iterator() {return content.entrySet().iterator();}
	
	@Override
	public void register(String name, T obj) {
		content.put(name, obj);
	}

}

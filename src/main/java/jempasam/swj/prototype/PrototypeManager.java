package jempasam.swj.prototype;

import java.util.Map.Entry;

import jempasam.swj.container.SizedIterable;

public interface PrototypeManager<T extends Prototype> extends SizedIterable<Entry<String,T>>{
	T get(String name);
	T create(String name);
	void register(String name, T obj);
	
}

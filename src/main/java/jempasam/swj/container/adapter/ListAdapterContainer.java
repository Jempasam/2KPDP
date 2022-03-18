package jempasam.swj.container.adapter;

import java.util.List;

import jempasam.swj.container.Container;

public class ListAdapterContainer<T>  implements Container<T>{
	List<T> handled;
	public ListAdapterContainer(List<T> handled) {
		super();
		this.handled = handled;
	}

	@Override
	public T get(int position) {
		return handled.get(position);
	}

	@Override
	public int size() {
		return handled.size();
	}

	@Override
	public void set(int position, T obj) {
		handled.set(position, obj);
	}

	@Override
	public void insert(int position, T obj) {
		handled.add(position,obj);
	}

	@Override
	public T remove(int position) {
		return handled.remove(position);
	}
	
}

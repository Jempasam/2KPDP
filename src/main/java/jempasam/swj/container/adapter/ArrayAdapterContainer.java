package jempasam.swj.container.adapter;

import jempasam.swj.container.ReadableContainer;

public class ArrayAdapterContainer<T> implements ReadableContainer<T> {
	T[] handled;

	public ArrayAdapterContainer(T[] handled) {
		super();
		this.handled = handled;
	}

	@Override
	public T get(int position) {
		return handled[position];
	}

	@Override
	public int size() {
		return handled.length;
	}
	

}

package jempasam.swj.container;

import java.util.Iterator;

public class ContainerIterator<T> implements Iterator<T>{
	ReadableContainer<T> target;
	int index;
	public ContainerIterator(ReadableContainer<T> target) {
		this.target=target;
		index=0;
	}
	@Override
	public boolean hasNext() {
		return index<target.size();
	}
	@Override
	public T next() {
		T ret=target.get(index);
		index++;
		return ret;
	}

}

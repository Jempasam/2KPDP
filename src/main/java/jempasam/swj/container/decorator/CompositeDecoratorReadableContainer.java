package jempasam.swj.container.decorator;

import jempasam.swj.container.ReadableContainer;

public class CompositeDecoratorReadableContainer<T> implements ReadableContainer<T>{
	ReadableContainer<T> down;
	ReadableContainer<T> up;
	
	public CompositeDecoratorReadableContainer(ReadableContainer<T> down, ReadableContainer<T> up) {
		super();
		this.down = down;
		this.up = up;
	}

	@Override
	public T get(int position) {
		if(position>=down.size())return up.get(position-down.size());
		else return down.get(position);
	}

	@Override
	public int size() {
		return down.size()+up.size();
	}
	
}

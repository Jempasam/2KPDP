package jempasam.swj.container.decorator;

import jempasam.swj.container.ReadableContainer;

public class ReverseDecoratorReadableContainer<T> implements ReadableContainer<T>{
	ReadableContainer<T> target;

	public ReverseDecoratorReadableContainer(ReadableContainer<T> target) {
		super();
		this.target = target;
	}

	@Override
	public T get(int position) {return target.get(size()-1-position);}

	@Override
	public int size() {return target.size();}
	
	
}

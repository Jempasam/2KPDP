package jempasam.swj.container.decorator;

import jempasam.swj.container.Container;

public class ReverseDecoratorContainer<T> implements Container<T> {
	private Container<T> target;

	public ReverseDecoratorContainer(Container<T> target) {
		super();
		this.target = target;
	}

	@Override
	public T get(int position) {return target.get(size()-1-position);}

	@Override
	public int size() {return target.size();}

	@Override
	public void set(int position, T obj) {target.set(size()-1-position, obj);}

	@Override
	public void insert(int position, T obj) {target.insert(size()-position, obj);}

	@Override
	public T remove(int position) {return target.remove(size()-1-position);}
	
}

package jempasam.swj.container.decorator;

import jempasam.swj.container.Container;

public class CompositeDecoratorContainer<T> implements Container<T>{
	Container<T> down;
	Container<T> up;
	
	public CompositeDecoratorContainer(Container<T> down, Container<T> up) {
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

	@Override
	public void set(int position, T obj) {
		if(position>=down.size()) up.set(position-down.size(),obj);
		else down.set(position,obj);
	}

	@Override
	public void insert(int position, T obj) {
		if(position>down.size()) up.insert(position-down.size(),obj);
		else down.insert(position,obj);
	}

	@Override
	public T remove(int position) {
		if(position>=down.size()) return up.remove(position-down.size());
		else return down.remove(position);
	}
	
	
}

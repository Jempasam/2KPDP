package jempasam.swj.iterator;

import java.util.Iterator;

public class CompositeIterator<T> implements Iterator<T>{
	
	Iterator<T>[] iterators;
	int itnumber;
	
	private CompositeIterator() {
		itnumber=0;
	}
	
	public CompositeIterator(Iterator<T> ...its) {
		this();
		iterators=its;
	}
	
	public CompositeIterator(Iterable<T> ...its) {
		this();
		iterators=new Iterator[its.length];
		for(int i=0; i<its.length; i++)iterators[i]=its[i].iterator();
	}
	

	@Override
	public boolean hasNext() {
		if(iterators[itnumber].hasNext())return true;
		else return itnumber<iterators.length-1;
	}

	@Override
	public T next() {
		if(!iterators[itnumber].hasNext() && itnumber<iterators.length-1) {
			itnumber++;
		}
		return iterators[itnumber].next();
	}
}

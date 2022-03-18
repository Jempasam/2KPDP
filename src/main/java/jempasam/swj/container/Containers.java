package jempasam.swj.container;

import java.util.List;

import jempasam.swj.container.adapter.ArrayAdapterContainer;
import jempasam.swj.container.adapter.ListAdapterContainer;
import jempasam.swj.container.decorator.CompositeDecoratorContainer;
import jempasam.swj.container.decorator.CompositeDecoratorReadableContainer;
import jempasam.swj.container.decorator.ReverseDecoratorContainer;
import jempasam.swj.container.decorator.ReverseDecoratorReadableContainer;

public class Containers {
	
	public static <T> Container<T> asContainer(List<T> list){
		return new ListAdapterContainer<>(list);
	}
	
	@SafeVarargs
	public static <T> ReadableContainer<T> asContainer(T ...args){
		return new ArrayAdapterContainer<>(args);
	}
	
	public static <T> ReadableContainer<T> reverse(ReadableContainer<T> rc){
		return new ReverseDecoratorReadableContainer<>(rc);
	}
	
	public static <T> Container<T> reverse(Container<T> rc){
		return new ReverseDecoratorContainer<>(rc);
	}
	
	public static <T> ReadableContainer<T> compose(ReadableContainer<T> a, ReadableContainer<T> b){
		return new CompositeDecoratorReadableContainer<>(a, b);
	}
	
	public static <T> Container<T> compose(Container<T> a, Container<T> b){
		return new CompositeDecoratorContainer<>(a, b);
	}
}

package engine.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListViewerContainer<V extends Viewer> implements ViewerContainer<V> {
	List<V> internal;
	
	public ListViewerContainer() {
		internal=new ArrayList<>();
	}
	
	@Override
	public Iterator<V> iterator() {
		return internal.iterator();
	}

	@Override
	public V get(int position) {
		return internal.get(position);
	}

	@Override
	public int size() {
		return internal.size();
	}

	@Override
	public void set(int position, V obj) {
		internal.set(position, obj);
	}

	@Override
	public void insert(int position, V obj) {
		internal.add(position, obj);
	}

	@Override
	public V remove(int position) {
		return internal.remove(position);
	}
	
}

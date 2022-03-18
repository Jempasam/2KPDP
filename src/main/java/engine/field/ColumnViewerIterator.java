package engine.field;

import java.util.Iterator;

public class ColumnViewerIterator implements Iterator<ColumnViewer> {
	Iterator<Column> it;

	public ColumnViewerIterator(Iterator<Column> it) {
		this.it=it;
	}
	@Override
	public boolean hasNext() {return it.hasNext();}

	@Override
	public ColumnViewer next() {return new ColumnViewer(it.next());}

}

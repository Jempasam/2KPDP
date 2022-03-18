package engine.field;

import java.util.Iterator;

import engine.util.Viewer;
import engine.util.ViewerContainer;

/**
 * Permet de récupérer les informations d'un terrain
 * @author Samuel Demont
 *
 */
public class FieldViewer extends Viewer<Field> implements ViewerContainer<ColumnViewer> {
	
	public FieldViewer(Field viewed) {
		super(viewed);
	}
	/**
	 * 
	 * @return Le nombre de colonne du terrain
	 */
	public int size() {
		return viewed.size();
	}
	/**
	 * 
	 * @param index
	 * @return Retourne la colonne d'index indiquÃ© en paramÃ¨tre
	 */
	public ColumnViewer get(int index) {
		return new ColumnViewer(viewed.get(index));
	}
	@Override
	public Iterator<ColumnViewer> iterator() {
		return new ColumnViewerIterator(viewed.iterator());
	}
	@Override
	public void set(int position, ColumnViewer obj) {
	}
	@Override
	public void insert(int position, ColumnViewer obj) {
	}
	@Override
	public ColumnViewer remove(int position) {
		return null;
	}
}

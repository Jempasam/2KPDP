package engine.util;

import jempasam.swj.container.Container;

public interface ViewerContainer<V extends Viewer> extends Container<V> {
	/**
	 * Renvoie l'index du premier viewer qui regarde le m�me objet que viewer
	 * @param viewer
	 * @return l'index si un viewer est trouv� sinon -1
	 */
	default int indexOfViewing(V viewer) {
		for(int i=0; i<size(); i++) {
			if(get(i).viewSame(viewer))return i;
		}
		return -1;
	}
}

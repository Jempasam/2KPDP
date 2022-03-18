package engine.field;

import engine.influence.viewer.InfluenceCardViewer;
import engine.objective.Domain;
import engine.objective.viewer.ObjectiveCardViewer;
import engine.util.Viewer;
import jempasam.swj.container.ReadableContainer;

/**
 * Permet de récupérer les informations d'une colonne.
 * @author Samuel Demont
 *
 */
public class ColumnViewer extends Viewer<Column> implements ReadableContainer<InfluenceCardViewer> {
	public ColumnViewer(Column c) {
		super(c);
	}
	
	/**
	 * @return Un viewer permettant de récupérer les informations de la carte objectif associé à la colonne
	 */
	public ObjectiveCardViewer getObjectiveCard() {
	  return new ObjectiveCardViewer(viewed.getObjectiveCard());
	}
	
	public int getObjective() {
		return viewed.getObjective();
	}
	
	public Domain getDomain() {
		return viewed.getDomain();
	}
	public ColumnStat getState() {
    	return viewed.getState();
    }

	@Override public InfluenceCardViewer get(int position) { return new InfluenceCardViewer(viewed.get(position)); }
	@Override public int size() { return viewed.size(); }
}

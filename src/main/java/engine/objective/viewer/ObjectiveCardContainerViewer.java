package engine.objective.viewer;

import engine.card.container.CardContainer;
import engine.objective.ObjectiveCard;
import engine.util.Viewer;
import jempasam.swj.container.ReadableContainer;

public class ObjectiveCardContainerViewer extends Viewer<CardContainer<ObjectiveCard>> implements ReadableContainer<ObjectiveCardViewer> {

	public ObjectiveCardContainerViewer(CardContainer<ObjectiveCard> viewed) {
		super(viewed);
	}
	@Override public ObjectiveCardViewer get(int position) { return new ObjectiveCardViewer(viewed.get(position)); }
	@Override public int size() { return viewed.size(); }

}

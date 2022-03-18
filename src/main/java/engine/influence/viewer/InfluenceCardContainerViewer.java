package engine.influence.viewer;

import engine.card.container.CardContainer;
import engine.influence.InfluenceCard;
import engine.util.Viewer;
import jempasam.swj.container.ReadableContainer;

public class InfluenceCardContainerViewer extends Viewer<CardContainer<InfluenceCard>> implements ReadableContainer<InfluenceCardViewer> {

	public InfluenceCardContainerViewer(CardContainer<InfluenceCard> viewed) {
		super(viewed);
	}
	@Override public InfluenceCardViewer get(int position) { return new InfluenceCardViewer(viewed.get(position)); }
	@Override public int size() { return viewed.size(); }

}

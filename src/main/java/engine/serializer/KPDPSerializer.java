package engine.serializer;

import engine.field.FieldViewer;
import engine.influence.viewer.InfluenceCardContainerViewer;
import engine.influence.viewer.InfluenceCardViewer;
import engine.objective.viewer.ObjectiveCardViewer;

public interface KPDPSerializer {
	
	String serializeObjective(ObjectiveCardViewer oc);
	
	String serializeInfluence(InfluenceCardViewer ic);
	
	default String serializeInfluenceContainer(InfluenceCardContainerViewer icc) {
		StringBuilder sb=new StringBuilder();
		for(InfluenceCardViewer ic: icc) {
			sb.append(serializeInfluence(ic));
		}
		return sb.toString();
	}
		
	String serializeField(FieldViewer field);
}

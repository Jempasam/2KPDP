package interfaceCommon.component;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * Une VBox dont le fils se superpose quand il y en a trop.
 * @author Samuel Demont
 *
 */
public class OverlapVBox extends VBox{
	public OverlapVBox() {
		super();
	}
	
	@Override
	protected void layoutChildren() {
		super.layoutChildren();
		List<Node> managed = getManagedChildren();
		if(managed.size()>2) {
			double contentAreaMaxY=getHeight()-getInsets().getBottom();
			Node bottom_node=managed.get(managed.size()-1);
			double contentMaxY=bottom_node.getLayoutY()+bottom_node.getLayoutBounds().getMaxY();
			if(contentAreaMaxY<contentMaxY) {
				double overlap=(contentMaxY-contentAreaMaxY)/(managed.size()-3);
				for(int i=3; i<managed.size(); i++) {
					Node node=managed.get(i);
					node.setLayoutY(node.getLayoutY()-overlap*(i-2));
				}
			}
		}
		
	}
	
	@Override
    protected double computeMinHeight(double width) {
		return getInsets().getTop()+getInsets().getBottom();
    }
}

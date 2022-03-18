package interfaceCommon.transition;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;

/**
 * Une transition qui fait un effet de flash
 * @author Samuel Demont
 *
 */
public class FlashTransition extends Transition{
	Node node;
	public FlashTransition(Node node) {
		setCycleDuration(Duration.millis(300));
		this.node=node;
	}

	@Override
	protected void interpolate(double frac) {
		if(frac<0.5)node.setEffect(new ColorAdjust(0,0,0.5*frac,0));
		else node.setEffect(new ColorAdjust(0,0,0.5*(1-frac),0));
	}
}

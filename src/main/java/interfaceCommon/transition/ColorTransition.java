package interfaceCommon.transition;

import interfaceCommon.JFXUtils.ColorHelper;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Une transition qui fait variée d'une couleur à une autres
 * @author Samuel Demont
 *
 */
public class ColorTransition extends Transition {
	Color from;
	Color to;
	Node node;
	
	public ColorTransition(Node node, Color from, Color to) {
		super();
		this.from = from;
		this.to = to;
		this.node = node;
		setCycleDuration(Duration.millis(1000));
	}

	@Override
	protected void interpolate(double frac) {
		Color color=from.interpolate(to, frac);
		double opacity=(1-frac)*from.getOpacity() + frac*to.getOpacity();
		node.setEffect(ColorHelper.whiteAdjustToColor(color));
		node.setOpacity(opacity);
	}

}

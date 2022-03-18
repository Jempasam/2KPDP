package interfaceCommon.transition;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Une transition qui change le nombre affiché par un label d'un nombre à un autres en angmentant/diminuant progressivement sa valeur.
 * @author Samuel Demont
 *
 */
public class NumberLabelTransition extends Transition{
	
	Label label;
	float from;
	float to;
	
	
	public NumberLabelTransition(Label label, float from, float to) {
		this.label=label;
		this.from=from;
		this.to=to;
		setCycleDuration(Duration.millis(1000));
	}

	@Override
	protected void interpolate(double frac) {
		int actual=(int)(from+(to-from)*frac);
		label.setText(Integer.toString(actual));
	}

}

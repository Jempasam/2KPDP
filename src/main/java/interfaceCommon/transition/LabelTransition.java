package interfaceCommon.transition;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Une transition qui change un label d'un text à un autres en effaçant puis réécrivant les lettres une par une
 * @author Samuel Demont
 *
 */
public class LabelTransition extends Transition{

	Label label;
	String from;
	String to;
	
	public LabelTransition(Label label, String from, String to) {
		super();
		this.label = label;
		this.from = from;
		this.to = to;
		
		setCycleDuration(Duration.millis(1000));
	}
	@Override
	protected void interpolate(double frac) {
		if(frac<0.5) {
			if(from.length()>0) {
				String text=from.substring(0, (int)(from.length()*(1-frac*2)));
				label.setText(text);
			}
		}else if(to.length()>0){
			String text=to.substring(0, (int)(to.length()*((frac-0.5)*2)));
			label.setText(text);
		}
	}
}

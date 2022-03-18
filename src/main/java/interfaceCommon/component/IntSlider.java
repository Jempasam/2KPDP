package interfaceCommon.component;

import javafx.scene.control.Slider;

/**
 * Un Slider � cran qui permet de choisir une valeur enti�re
 * @author Samuel Demont
 *
 */
public class IntSlider extends Slider{
	
	public IntSlider(int min, int def, int max, int inc) {
		setShowTickLabels(true);
		setShowTickMarks(true);
		setMajorTickUnit(1);
		setBlockIncrement(inc);
		valueProperty().addListener((obs,ol,nw)->setValue(Math.round(nw.doubleValue())));
		setValue(def);
	}
	
}

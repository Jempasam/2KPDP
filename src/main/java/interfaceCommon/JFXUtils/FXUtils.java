package interfaceCommon.JFXUtils;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 * Permet différentes opérations en rapport avec les fonds.
 * @author Samuel Demont
 *
 */
public class FXUtils {
	/**
	 * Crée un fond avec une couleur aléatoire
	 * @return le fond
	 */
	public static Background randomBG() {
		return new Background(new BackgroundFill(Color.hsb(Math.random()*360, 1, 0.8),CornerRadii.EMPTY, Insets.EMPTY));
	}
	
	/**
	 * Crée un fond avec une nouvelle couleur unique à chaque appel et constrastant bien avec les fonds précédents.
	 * @return le fond
	 */
	public static Background uniqueBG() {
		num++;
		return new Background(new BackgroundFill(ColorHelper.colorOfInt(num),CornerRadii.EMPTY, Insets.EMPTY));
	}
	private static int num=0;
	
	/**
	 * Crée un contour avec une nouvelle couleur unique à chaque appel et constrastant bien avec les fonds précédents.
	 * @return le fond
	 */
	public static Border uniqueBorder() {
		numc++;
		return new Border(new BorderStroke(ColorHelper.colorOfInt(numc), BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(20), Insets.EMPTY));
	}
	private static int numc=5;
	
	/**
	 * Assignes un fond uniques à plusieurs éléments
	 * @param nodes les éléments
	 */
	public static void setUniqueBGForAll(Region ... nodes) {
		for(Region n : nodes) {
			n.setBackground(uniqueBG());
		}
	}
}

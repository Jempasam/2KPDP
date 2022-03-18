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
 * Permet diff�rentes op�rations en rapport avec les fonds.
 * @author Samuel Demont
 *
 */
public class FXUtils {
	/**
	 * Cr�e un fond avec une couleur al�atoire
	 * @return le fond
	 */
	public static Background randomBG() {
		return new Background(new BackgroundFill(Color.hsb(Math.random()*360, 1, 0.8),CornerRadii.EMPTY, Insets.EMPTY));
	}
	
	/**
	 * Cr�e un fond avec une nouvelle couleur unique � chaque appel et constrastant bien avec les fonds pr�c�dents.
	 * @return le fond
	 */
	public static Background uniqueBG() {
		num++;
		return new Background(new BackgroundFill(ColorHelper.colorOfInt(num),CornerRadii.EMPTY, Insets.EMPTY));
	}
	private static int num=0;
	
	/**
	 * Cr�e un contour avec une nouvelle couleur unique � chaque appel et constrastant bien avec les fonds pr�c�dents.
	 * @return le fond
	 */
	public static Border uniqueBorder() {
		numc++;
		return new Border(new BorderStroke(ColorHelper.colorOfInt(numc), BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(20), Insets.EMPTY));
	}
	private static int numc=5;
	
	/**
	 * Assignes un fond uniques � plusieurs �l�ments
	 * @param nodes les �l�ments
	 */
	public static void setUniqueBGForAll(Region ... nodes) {
		for(Region n : nodes) {
			n.setBackground(uniqueBG());
		}
	}
}

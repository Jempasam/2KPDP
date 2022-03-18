package interfaceCommon.component;

import interfaceCommon.image.ImageManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Screen;

public class ComponentUtil {
	
	public static Background  SOFT_SCROLL_BACKGROUND=new Background(new BackgroundImage(ImageManager.getImage("misc", "bg_paper2"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, true, true, false, false)));;
	public static Background  PLANK_BACKGROUND=new Background(new BackgroundImage(ImageManager.getImage("misc", "plank"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, true, true, false, false)));;
	public static Background  BUTTON_BACKGROUND=new Background(new BackgroundImage(ImageManager.getImage("misc", "button"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, true, true, false, false)));;
	public static Background  BRICK_BACKGROUND=new Background(new BackgroundImage(ImageManager.getImage("misc", "bg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(ComponentUtil.getBaseUnit()*30, ComponentUtil.getBaseUnit()*30, false, false, false, false)));
	
	
	public static double baseTextSize(float size) {
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		return screenBounds.getWidth()/80*size;
	}
	public static double getBaseUnit() {
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		return screenBounds.getWidth()/40;
	}
}

package interfaceCommon.component;

import interfaceCommon.image.ImageManager;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SmallLabel extends Label{
	public SmallLabel(String text, String font, float size) {
		super(text);
		setFont(Font.font(font,FontWeight.NORMAL,ComponentUtil.baseTextSize(size)));
	}
	public SmallLabel(String text) {
		this(text,"Arial",1);
	}
	public SmallLabel() {
		this(null);
	}
	public SmallLabel(String text, String font) {
		this(text,font,1);
	}
	
	public SmallLabel(String text, float size) {
		this(text,"Arial",size);
	}
	
	public SmallLabel(float size) {
		this("", "Arial", size);
	}
	
	public static SmallLabel createBigTitle(String text) {
		SmallLabel ret= new SmallLabel(text,"Vivaldi",6);
		ret.setPadding(new Insets(ComponentUtil.getBaseUnit()*1.5));
		ret.setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", "title"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false))));
		return ret;
	}
	
	public static SmallLabel createTitle(String text) {
		SmallLabel ret=new SmallLabel(text,"Vivaldi",4);
		ret.setPadding(new Insets(ComponentUtil.getBaseUnit()/2));
		ret.setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", "button"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false))));
		return ret;
	}
	
	public static SmallLabel createSubTitle(String text) {
		return new SmallLabel(text,"Vivaldi",2);
	}

}

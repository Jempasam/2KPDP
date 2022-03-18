package interfaceCommon.component;

import interfaceCommon.JFXUtils.ColorHelper;
import interfaceCommon.image.ImageManager;
import interfaceCommon.sound.SoundManager;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Un bouton qui s'adapte tout seul à la taille de l'écran
 * @author Samuel Demont
 *
 */
public class SmallButton extends Button {
	private Color color;
	
	private SmallButton(String text, String font, float size, String background) {
		super(text);
		setFont(Font.font(font,FontWeight.NORMAL,ComponentUtil.baseTextSize(size)));
		setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", background), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false))));
		hoverProperty().addListener((obs,o,n)->{
			if(n) {
				setCursor(Cursor.HAND);
				this.setEffect(new ColorAdjust(0.7, 0, 0, 0));
				if(color==null)this.setEffect(new ColorAdjust(0.7, 0, 0, 0));
				else{
					ColorAdjust coladj=ColorHelper.whiteAdjustToColor(color);
					coladj.setBrightness(coladj.getBrightness()+0.2);
					coladj.setSaturation(coladj.getSaturation()+0.2);
					this.setEffect(coladj);
				}
			}
			else {
				setCursor(Cursor.NONE);
				if(color==null)this.setEffect(null);
				else this.setEffect(ColorHelper.whiteAdjustToColor(color));
			}
		});
		setOnMouseClicked((e)->SoundManager.playSound("effect", "click", "effect", 0));
	}
	
	public SmallButton(String text, String font, float size) {
		this(text,font,size,"button");
	}
	
	public static SmallButton createWooden(String text, String font, Color color, float size) {
		SmallButton ret=new SmallButton(text,font,size,"plank2");
		ret.setColor(color);
		return ret;
	}
	
	public static SmallButton createWooden(String text, Color color) {
		return SmallButton.createWooden(text, "Arial", color, 1);
	}
	
	public static SmallButton createWooden(String text, Color color, float size) {
		return SmallButton.createWooden(text, "Arial", color, size);
	}
	
	public SmallButton() {
		this("");
	}
	public SmallButton(String text) {
		this(text, "Arial",1);
	}
	
	public SmallButton(String text, float size) {
		this(text, "Arial", size);
	}
	
	public SmallButton(String text, String font) {
		this(text, font,1);
	}
	
	public void setColor(Color color) {
		this.color=color;
		this.setEffect(ColorHelper.whiteAdjustToColor(color));
	}
	
	public Color getColor() {
		return color;
	}
}

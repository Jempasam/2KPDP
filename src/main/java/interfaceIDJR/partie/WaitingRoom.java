package interfaceIDJR.partie;

import interfaceCommon.cd.View;
import interfaceCommon.component.ComponentUtil;
import interfaceCommon.image.ImageManager;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WaitingRoom extends View {
	
	Label Titre;
	
	public WaitingRoom() {		
		this.setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", "bg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(ComponentUtil.getBaseUnit()*30, ComponentUtil.getBaseUnit()*30, false, false, false, false))));
			
		Border maBordure = new Border(new BorderStroke(Color.FIREBRICK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(20)));
					
				HBox centre = new HBox();
				Titre = new Label("La partie va bientôt commencer");
				Titre.setFont(Font.font("Vivaldi", FontWeight.BOLD, 100));
				centre.setMinSize(300,300);
				centre.setAlignment(Pos.CENTER);
				centre.setSpacing(25);
				centre.setBorder(maBordure);
		}
}
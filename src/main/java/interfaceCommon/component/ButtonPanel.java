package interfaceCommon.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ButtonPanel extends VBox{
	String[] values;
	Button[] buttons;
	public ButtonPanel(String[] values, int size) {
		setAlignment(Pos.CENTER);
		this.values=values;
		buttons=new Button[values.length];
		for(int i=0; i<buttons.length; i++) {
			buttons[i]=new SmallButton(values[i],size);
			buttons[i].setMaxWidth(Double.MAX_VALUE);
			Button b=buttons[i];
			VBox.setMargin(buttons[i], new Insets(10,10,10,10));
			getChildren().add(buttons[i]);
		}
	}
	
	public String[] getValues() {
		return values;
	}
	
	public Button[] getButtons() {
		return buttons;
	}
}

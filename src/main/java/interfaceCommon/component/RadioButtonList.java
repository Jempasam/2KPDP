package interfaceCommon.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class RadioButtonList extends HBox{
	private String[] values;
	private RadioButton[] radios;
	private ToggleGroup group;
	private int default_selected;
	
	public RadioButtonList(String[] values, int default_selected) {
		super();
		group = new ToggleGroup();
		
		this.default_selected=default_selected;
		this.values=values;
		radios=new RadioButton[values.length];
		for(int i=0; i<radios.length; i++) {
			radios[i]=new RadioButton(values[i]);
			radios[i].setFont(Font.font("Arial", ComponentUtil.getBaseUnit()/3));
			radios[i].setToggleGroup(group);
			HBox.setMargin(radios[i], new Insets(4,4,4,4));
			getChildren().add(radios[i]);
		}
		radios[default_selected].setSelected(true);
		
		setPadding(new Insets(2,2,2,2));
		setAlignment(Pos.CENTER);
	}
	
	public int getSelectedNum() {
		for(int i=0; i<radios.length; i++) {
			if(radios[i].isSelected())return i;
		}
		return default_selected;
	}
	
	public String getSelectedValue() {
		return values[getSelectedNum()];
	}
	
	public int getDefaultSelectedNum() {
		return default_selected;
	}
	
	public String getDefaultSelectedValue() {
		return values[getDefaultSelectedNum()];
	}
	
	public String[] getValues() {
		return values;
	}
	
	public RadioButton[] getButtons() {
		return radios;
	}
}

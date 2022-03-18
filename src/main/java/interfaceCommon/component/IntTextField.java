package interfaceCommon.component;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;

/**
 * Equivalent au TextField mais on ne peut n'y écrire que des nombres.
 * @author Samuel Demont
 *
 */
public class IntTextField extends TextField{
	
	private int default_value;
	private IntegerProperty value;
	private int minimum=Integer.MIN_VALUE, maximum=Integer.MAX_VALUE;
	
	public IntTextField(int default_value) {
		super();
		
		value=new SimpleIntegerProperty();
		
		this.default_value=default_value;
		setValue(default_value);
		
		
		//Check value change
		textProperty().addListener((o, old_value, new_value)->{
			if(!new_value.isEmpty()) {
				try {
					int value=Integer.parseInt(new_value);
					setValue(value);
				}catch(NumberFormatException e) {
					setText(old_value);
				}
			}
		});
		
		//Check key press
		this.setOnKeyPressed((e)->{
			if(e.getCode()==KeyCode.UP)addToValue(1);
			else if(e.getCode()==KeyCode.DOWN)addToValue(-1);
			else if(e.getCode()==KeyCode.LEFT)addToValue(-10);
			else if(e.getCode()==KeyCode.RIGHT)addToValue(10);
		});
		
		
		setFont(Font.font("Arial",ComponentUtil.getBaseUnit()/2));
		
		int int_size=Integer.toString(default_value).length()+2;
		setMaxWidth(ComponentUtil.getBaseUnit()/2*int_size);
		focusedProperty().addListener((o,old_value,new_value)->{
			if(new_value==false && getText().isEmpty())setValue(default_value);
		});
	}
	
	
	
	public IntTextField(int default_value, int minimum_value, int maximum_value) {
		this(default_value);
		
		int int_size=Integer.toString(maximum_value).length()+2;
		setMaxWidth(ComponentUtil.getBaseUnit()/3*int_size);
		
		minimum=minimum_value;
		maximum=maximum_value;
	}
	
	public int getValue() {
		return this.value.get();
	}
	
	public void setValue(int value) {
		if(value<minimum) value=minimum;
		else if(value>maximum) value=maximum;
		this.value.set(value);
		setText(Integer.toString(value));
	}
	
	public int getDefaultValue() {
		return default_value;
	}
	
	public void addToValue(int v) {
		setValue(getValue()+v);
	}
	
	public IntegerProperty valueProperty() {
		return value;
	}
}

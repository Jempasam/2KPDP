package interfaceCommon.component;

public class VRadioButtonList<T> extends RadioButtonList {
	
	T[] objects;
	
	public VRadioButtonList( T default_selected, String[] values, T[] objects) {
		super(values, 0);
		this.objects=objects;
		if(values.length!=objects.length) {
			System.out.println("Bade VRadioButtonList.");
			System.exit(1);
		}
		for(int i=0; i<objects.length; i++) {
			if(objects[i]==default_selected) getButtons()[i].setSelected(true);
		}
	}
	
	public T getSelectedObject() {
		return objects[getSelectedNum()];
	}
	

}

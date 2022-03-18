package interfaceTable.component;


import java.util.Arrays;
import java.util.Iterator;

import engine.field.FieldViewer;
import engine.game.GameViewer;
import interfaceCommon.component.ResizableImageView;
import interfaceCommon.image.ImageManager;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class FieldComponent extends StackPane implements Iterable<ColumnComponent>{
	public ColumnComponent[] columns;
	private GameViewer game;
	private FieldViewer field;
	private HBox field_box;
	
	public FieldComponent(GameViewer game){
		super();
		
		this.game=game;
		field=new FieldViewer(null);
		
		//Image de fond
		ResizableImageView image=new ResizableImageView(ImageManager.getImage("misc", "bg"));
		image.setMinSize(0);
		image.setPreserveRatio(false);
		getChildren().add(image);
		
		//Colonnes
		field_box=new HBox();
		getChildren().add(field_box);
		
		update();
	}
	
	@Override
	public Iterator<ColumnComponent> iterator() {
		return Arrays.stream(columns).iterator();
	}
	
	public void update() {
		if(!game.getField().viewSame(field)) {
			field=game.getField();
			
			field_box.getChildren().clear();
			columns=new ColumnComponent[field.size()];
			for(int i=0; i<field.size(); i++) {
				columns[i]=new ColumnComponent(field.get(i), game);
				field_box.getChildren().add(columns[i]);
				field_box.setHgrow(columns[i], Priority.SOMETIMES);
			}
		}else {
			for(ColumnComponent c : columns)c.update();
		}
	}
}

package interfaceCommon.component;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class EntryOrderer extends Region{
	private VBox content;
	private List<Entry> entries;
	public EntryOrderer(){
		entries=new ArrayList<Entry>();
		content=new VBox();
		getChildren().add(content); 
	}
	
	@Override
	protected double computeMinWidth(double height) {
		return content.getMinWidth();
	}
	
	@Override
	protected double computeMinHeight(double width) {
		return content.getMinHeight();
	}
	

	private void generateEntries() {
		VBox newcontent=new VBox();
		int i=0;
		for(Entry entry : entries) {
			HBox pEntry=new HBox();
			
			VBox bpMove=new VBox();
			int ii=i;
			SmallButton bUp=SmallButton.createWooden(" ",entry.getColor(),0.45f);
			bUp.setOnAction((e)->move(ii, ii-1));
			SmallButton bDown=SmallButton.createWooden(" ",entry.getColor(),0.45f);
			bDown.setOnAction((e)->move(ii, ii+1));
			bpMove.getChildren().addAll(bUp,bDown);
			
			SmallButton lEntry=SmallButton.createWooden(entry.getText(),entry.getColor());
			lEntry.setMaxWidth(Double.MAX_VALUE);
			
			HBox.setHgrow(lEntry, Priority.ALWAYS);
			
			pEntry.getChildren().addAll(lEntry,bpMove);
			
			newcontent.getChildren().add(pEntry);
			i++;
		}
		content=newcontent;
		getChildren().set(0, content);
	}
	private void move(int from,int to) {
		System.out.println("A"+from+"A"+to+"A");
		if(from>=0 && from<entries.size() && to>=0 && to<entries.size()) {
			Entry buffer=entries.get(from);
			entries.set(from,entries.get(to));
			entries.set(to, buffer);
			generateEntries();
		}
	}
	
	@Override
	protected void layoutChildren() {
		content.resize(getWidth(), getHeight());
		content.setLayoutX(0);
		content.setLayoutY(0);
	}
	
	
	public static class Entry {
		private String text;
		private Color color;
		
		public Entry(String text, Color color) {
			super();
			this.text = text;
			this.color = color;
		}

		public String getText() {return text;}

		public Color getColor() {return color;}
	}
	
	public void set(List<Entry> entries) {
		this.entries=entries;
		generateEntries();
	}
}

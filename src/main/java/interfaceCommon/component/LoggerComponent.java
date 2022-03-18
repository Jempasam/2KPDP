package interfaceCommon.component;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LoggerComponent extends VBox{
	
	private Label[] description_texts;
	private int counter;
	private int cursor;
	
	public LoggerComponent(int size) {
		//Création des labels
		description_texts=new Label[size];
		for(int i=0; i<description_texts.length; i++)
			description_texts[i]=new SmallLabel("----",0.9f);
		getChildren().addAll(description_texts);
		
		//Intitialisation du curseur et du compteur
		counter=0; cursor=0;
		
	}
	
	private void _log(String message, Color color) {
		Label target=description_texts[cursor];
		target.setText(message);
		target.setTextFill(color);
		
		target.setBackground(new Background(new BackgroundFill(Color.hsb(counter/description_texts.length%18*20, 0.7, 1),CornerRadii.EMPTY,Insets.EMPTY)));
		
		cursor++;
		counter++;
		if(cursor>=description_texts.length)cursor=0;
	}
	
	public void log(String message) {
		_log(message,Color.BLACK);
	}
	
	public void errlog(String message) {
		_log(message,Color.DARKRED);
	}
}

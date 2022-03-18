package interfaceCommon.component.kpdp;

import interfaceCommon.JFXUtils.ColorHelper;
import interfaceCommon.image.ImageManager;
import interfaceCommon.lang.LanguageManager;
import interfaceCommon.transition.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Un composant graphique qui affiche une carte influence
 * @author Samuel Demont
 *
 */
public class InfluenceCardComponent extends Pane{
	private int team_num=-1;
	private String id="";
	private Color effect_color=Color.rgb(255, 255, 255, 0);
	private boolean revealed=false;
	private float points=0;
	
	private ImageView ivArtwork;
	private ImageView ivFrame;
	private ImageView ivFrame_overlay;
	private ImageView ivEffect;
	
	private Label[] lPoints;
	private Label lName;
	
	public InfluenceCardComponent(int team_num, String id, Color effect_color, boolean revealed, float points) {
		super();
		
		setuppane();
		update(team_num, id, effect_color, revealed, points);
	}
	
	
	@Override
	protected void layoutChildren() {
		double content_width=getWidth()-getInsets().getLeft()-getInsets().getRight();
		
		//Images
		ivArtwork.setFitWidth(content_width/20*19.5);
		ivArtwork.setLayoutX(content_width/90);
		ivArtwork.setLayoutY(content_width/35);
		ivFrame.setFitWidth(content_width);
		ivFrame_overlay.setFitWidth(content_width);
		ivEffect.setFitWidth(content_width);
		
		//Score
		for(int i=0; i<2; i++) {
			lPoints[i].setLayoutY(getInsets().getTop()+content_width/20);
			lPoints[i].resize(content_width/4, content_width/4);
			lPoints[i].setFont(Font.font("Arial", FontWeight.NORMAL, content_width/8));
		}
		lPoints[0].setLayoutX(getInsets().getLeft()+content_width/20);
		lPoints[1].setLayoutX(getInsets().getLeft()+content_width/20*14);
		
		//Name
		lName.setFont(Font.font("Vivaldi", FontWeight.NORMAL, content_width/6));
		lName.resize(content_width/20*18, content_width/4);
		lName.setLayoutX(getInsets().getLeft()+content_width/20);
		lName.setLayoutY(getInsets().getLeft()+content_width/16*11);
	}
	@Override
	protected double computeMaxHeight(double width) {
		return getWidth();
	}
	
	@Override
	protected double computeMinHeight(double width) {
		return getWidth();
	}
	
	private void setuppane() {
		//Images
		ivArtwork=new ImageView();
		ivArtwork.setPreserveRatio(true);
		
		ivFrame=new ImageView(ImageManager.getImage("misc", "inf"));
		ivFrame.setPreserveRatio(true);
		
		ivFrame_overlay=new ImageView(ImageManager.getImage("misc", "inf_dos"));
		ivFrame_overlay.setPreserveRatio(true);
		ivFrame_overlay.setEffect(ColorHelper.whiteAdjustToColor(Color.WHITE));
		
		ivEffect=new ImageView(ImageManager.getImage("misc", "effect"));
		ivEffect.setPreserveRatio(true);
		
		//Nom
		lName=new Label();
		lName.setAlignment(Pos.CENTER);
		
		//Points
		lPoints=new Label[2];
		lPoints[0]=new Label("0");
		lPoints[1]=new Label("0");
		for(int i=0; i<2; i++)lPoints[i].setAlignment(Pos.CENTER);
		setPrefWidth(300);
		
		getChildren().addAll(ivArtwork, ivFrame, lName, lPoints[0], lPoints[1], ivFrame_overlay, ivEffect);
	}
	
	public void update(int team_num, String id, Color effect_color, boolean revealed, float points) {
		if(this.team_num!=team_num) {
			ColorTransition tr = new ColorTransition(ivFrame_overlay,ColorHelper.colorOfTeam(this.team_num),ColorHelper.colorOfTeam(team_num));
			tr.play();
			
			this.team_num=team_num;
		}
		if(!this.id.equals(id)) {
			String name=LanguageManager.get("carte."+id);
			LabelTransition tr = new LabelTransition(lName, lName.getText(), name);
			tr.play();
			
			ImageTransition tr2=new ImageTransition(ivArtwork, ImageManager.getImage("influence", id));
			tr2.play();
			
			this.id=id;
		}
		if(!this.effect_color.equals(effect_color)) {
			ColorTransition tr = new ColorTransition(ivEffect,this.effect_color,effect_color);
			tr.play();
			
			this.effect_color=effect_color;
		}
		if(this.revealed!=revealed) {
			this.revealed=revealed;
			
			if(revealed)ivFrame_overlay.setImage(ImageManager.getImage("misc", "inf2"));
			else ivFrame_overlay.setImage(ImageManager.getImage("misc", "inf_dos"));
			
			
			FlashTransition tr=new FlashTransition(this);
			tr.play();
		}
		if(this.points!=points) {
			NumberLabelTransition tr = new NumberLabelTransition(lPoints[0], this.points, points);
			NumberLabelTransition tr2 = new NumberLabelTransition(lPoints[1], this.points, points);
			
			tr.play();
			tr2.play();
			this.points=points;
		}
	}
	
	
	
}

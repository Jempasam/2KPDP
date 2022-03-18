package interfaceCommon.component.kpdp;

import interfaceCommon.image.ImageManager;
import interfaceCommon.lang.LanguageManager;
import interfaceCommon.transition.FlashTransition;
import interfaceCommon.transition.ImageTransition;
import interfaceCommon.transition.LabelTransition;
import interfaceCommon.transition.NumberLabelTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Un composant graphique qui affiche une carte objectif
 * @author Samuel Demont
 *
 */
public class ObjectiveCardComponent extends Pane{
	private String id="";
	private boolean revealed=false;
	private float points=0;
	
	private ImageView ivDomain;
	private ImageView ivFrame;
	
	private Label lPoints;
	private Label lName;
	
	public ObjectiveCardComponent(String id, int points, boolean revealed) {
		super();
		
		setuppane();
		update(id, points, revealed);
	}
	
	
	@Override
	protected void layoutChildren() {
		double content_width=getWidth()-getInsets().getLeft()-getInsets().getRight();
		
		//Images
		ivDomain.setFitWidth(content_width/6*2);
		ivDomain.setLayoutX(content_width/24*13);
		ivDomain.setLayoutY(content_width/13);
		
		ivFrame.setFitWidth(content_width);
		
		//Score
		lPoints.setLayoutY(content_width/10);
		lPoints.resize(content_width/3, content_width/3);
		lPoints.setFont(Font.font("Arial", FontWeight.NORMAL, content_width/4));
		lPoints.setLayoutX(content_width/10);
		
		//Name
		lName.setFont(Font.font("Vivaldi", FontWeight.NORMAL, content_width/9));
		lName.resize(content_width/20*18, content_width/5);
		lName.setLayoutX(content_width/20);
		lName.setLayoutY(content_width/20*7);
	}
	@Override
	protected double computeMaxHeight(double width) {
		return getWidth()*3/5;
	}
	
	@Override
	protected double computeMinHeight(double width) {
		return getWidth()*3/5;
	}
	
	private void setuppane() {
		
		//Images
		ivDomain=new ImageView();
		ivDomain.setPreserveRatio(true);
		
		ivFrame=new ImageView(ImageManager.getImage("misc", "obj"));
		ivFrame.setPreserveRatio(true);
		
		//Nom
		lName=new Label();
		lName.setAlignment(Pos.CENTER);
		
		//Points
		lPoints=new Label();
		lPoints.setAlignment(Pos.CENTER);
		
		getChildren().addAll(ivFrame, ivDomain, lPoints, lName);
	}
	
	public void update(String id, int points, boolean revealed) {
		if(!this.id.equals(id)) {
			String name=LanguageManager.get("domain."+id);
			LabelTransition tr = new LabelTransition(lName, lName.getText(), name);
			tr.play();
			
			ImageTransition tr2=new ImageTransition(ivDomain, ImageManager.getImage("domain", id));
			tr2.play();
			
			this.id=id;
		}
		if(this.revealed!=revealed) {
			this.revealed=revealed;
			
			if(revealed) {
				lName.setVisible(true);
				lPoints.setVisible(true);
				ivFrame.setImage(ImageManager.getImage("misc", "obj"));
			}
			else {
				lName.setVisible(false);
				lPoints.setVisible(false);
				ivFrame.setImage(ImageManager.getImage("misc", "obj_dos"));
			}
			
			
			FlashTransition tr=new FlashTransition(this);
			tr.play();
		}
		if(this.points!=points) {
			NumberLabelTransition tr = new NumberLabelTransition(lPoints, this.points, points);
			
			tr.play();
			this.points=points;
		}
	}
	
	
	
}

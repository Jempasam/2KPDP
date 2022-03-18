package interfaceTable.regles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import interfaceCommon.JFXUtils.FXUtils;
import interfaceCommon.cd.View;
import interfaceCommon.component.ComponentUtil;
import interfaceCommon.component.SmallButton;
import interfaceCommon.component.SmallLabel;
import interfaceCommon.image.ImageManager;
import interfaceCommon.lang.LanguageManager;
import interfaceTable.app.App;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Here's the page for the game's rules
 * @author Léa CUMONT
 *
 */
public class GamesRules extends View {
	
	private ScrollPane sp;

	Label lTitle;

	//Labels titles for the summary
	Label lTitleButJeu;
	Label lTitleMateriel;
	Label lTitleMiseenplace;
	Label lTitleDeroulement;
	Label lTitleFinmanche;
	Label lTitleFinjeu;
	Label lTitleCarteinflu;
	Label lTitleCarteinflu1;
	Label lTitleCarteinflu2;
	Label lTitleCarteinflu3;
	Label lTitleCredits;
	
	Button bBack;
	Button bQuit;

	VBox vbRight;
	VBox vbLeft;
	VBox vbCenter;
	HBox hbUp;
	
	private ArrayList<String> section; 

	public GamesRules(){
		this.section = new ArrayList<String>(); 

		this.setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", "bg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(ComponentUtil.getBaseUnit()*30, ComponentUtil.getBaseUnit()*30, false, false, false, false))));
				
		creationWidgets();
		creationContainers();
		placementWidgets();
		placementContainersInPane();
		summary();
		buildRules(0);

		// this.setVisible(false);
		bBack.setOnAction(event -> App.ctrlView().goBack());
	}

	/** Creation of some widgets, a title and a button to quit this page (not the game) **/
	private void creationWidgets() {
		lTitle = SmallLabel.createTitle("title");
		lTitle.textProperty().bind(LanguageManager.createBinding("regletitre"));
		lTitle.setFont(Font.font("Vivaldi", FontWeight.BOLD, 110));

		bBack = new SmallButton();
		bBack.textProperty().bind(LanguageManager.createBinding("bouton.retour"));
		bBack.setFont(Font.font(30));
		bBack.setPrefWidth(350);
	}

	/** Creation of major containers (up, left, right, and a scrollpane) **/
	private void creationContainers() {
		hbUp = new HBox();
		hbUp.setAlignment(Pos.CENTER);

		vbRight = new VBox();
		vbRight.setAlignment(Pos.BOTTOM_RIGHT);
		vbRight.setPrefWidth(0);

		vbLeft = new VBox();
		vbLeft.setAlignment(Pos.BOTTOM_LEFT);
		vbLeft.setPadding(new Insets(30, 30, 30, 40));

		vbCenter = new VBox();
		vbCenter.setAlignment(Pos.TOP_LEFT);
		vbCenter.setPadding(new Insets(ComponentUtil.getBaseUnit()));
		vbCenter.setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", "bg_paper2"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1,1,true,true, false, false))));
		
		sp = new ScrollPane();
		sp.setFitToWidth(true);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
	}

	private void placementContainersInPane() {
		this.setTop(hbUp);
		this.setRight(vbRight);
		this.setLeft(vbLeft);
		this.setCenter(sp);
		sp.setContent(vbCenter);
	}
	
	/** Rules written in the Scrollpane **/
	private void buildRules(int i) {
		vbCenter.getChildren().clear();
				
		ArrayList<String> p  = new ArrayList<>(); 
		
		Collection<String> keys = LanguageManager.getKeys();
		
		for(String key : keys) {
			
			if(key.contains("regle." + section.get(i))) {
				if(key.contains("text") || key.contains("textlist")) {
					Label t=new SmallLabel();
					
					if (key.contains("intro")) {
						t=SmallLabel.createSubTitle("intro");
					}
					
					if (key.contains("texttitre")) {
						t=SmallLabel.createTitle("texttitre");
					}
					
					//Putting important notes in red
					if (key.contains("IMP")) {
						t.setTextFill(Color.RED);
					}
					
					t.textProperty().bind(LanguageManager.createBinding(key));
					t.setMaxWidth(ComponentUtil.getBaseUnit()*30);
					t.setWrapText(true);
					
					vbCenter.getChildren().add(t);
					//vbCenter.getChildren().add(new Label(key));
				} 
				
				else if (key.contains("puce")) {
					Collections.sort(p);
					p.add(key); 
				}
			}
		}
		vbCenter.getChildren().add(new ListStyle(p)); 	
	}

	/** Summary container, including titles of paragraphes.
	 * Those titles are clickable,
	 * When the user click one of a title, it leads to the corresponding part
	 * of the title. **/
	private void summary() {
		VBox summary = new VBox();

		//Get keys properties
		Collection<String> keys = LanguageManager.getKeys();
	
		//For each keys
		for(String key : keys) {
			//If the key is a summary title
			if (key.contains("titre.sommaire")) {
				String s = key.split("\\.")[1];
				//Titles
				section.add(s); 
			}
		}
		
		//Stylizing titles for the summary
		lTitleButJeu = stylizing(0, "regle.Abutjeu.titre.sommaire");
		
		lTitleMateriel = stylizing(1, "regle.Bmateriel.titre.sommaire");
		
		lTitleMiseenplace = stylizing(2, "regle.Cmiseenplace.titre.sommaire");
		
		lTitleDeroulement = stylizing(3, "regle.Dderoulement.titre.sommaire");
		
		lTitleFinmanche = stylizing(4, "regle.Efinmanche.titre.sommaire");
		
		lTitleFinjeu = stylizing(5, "regle.Ffinjeu.titre.sommaire");
		
		lTitleCarteinflu = stylizing(6, "regle.Gcarteinflu.titre.sommaire");
		
		lTitleCarteinflu1 = stylizing(7, "regle.Hcartesanscapa.titre.sommaire");
		
		lTitleCarteinflu2 = stylizing(8, "regle.Icartesaveccapaspect.titre.sommaire");
		
		lTitleCarteinflu3 = stylizing(9, "regle.Jcartesaveccapfinmanche.titre.sommaire");
		
		lTitleCredits = stylizing(10, "regle.Kcredit.titre.sommaire");
		
		
		summary.getChildren().addAll(lTitleButJeu, lTitleMateriel, lTitleMiseenplace,
				lTitleDeroulement,lTitleFinmanche,lTitleFinjeu, lTitleCarteinflu,
				lTitleCarteinflu1,lTitleCarteinflu2,lTitleCarteinflu3,lTitleCredits);
		summary.setSpacing(10);
		summary.setAlignment(Pos.TOP_CENTER);
		
		//sorting
		Collections.sort(section);
		
		vbLeft.setAlignment(Pos.TOP_LEFT);
		vbLeft.getChildren().addAll(summary);
		vbLeft.getChildren().add(bBack);
	}
	

	private void placementWidgets() {
		hbUp.getChildren().add(lTitle);
		vbLeft.setBackground(ComponentUtil.SOFT_SCROLL_BACKGROUND);
		vbRight.setBackground(new Background(new BackgroundFill(Color.FIREBRICK, CornerRadii.EMPTY, null)));	
	}
	
	//Stylizing labels for the summary
	public Label stylizing(int nbPage, String propertiesText) {
		Label l = new SmallLabel();
		
		l.textProperty().bind(LanguageManager.createBinding(propertiesText));
		l.setWrapText(true);
		l.setMinWidth(0);
		l.setOnMouseClicked(e -> buildRules(nbPage));
			
		l.setOnMouseEntered(event ->  l.setTextFill(Color.BEIGE));
		l.setOnMouseExited(event ->  l.setTextFill(Color.BLACK));
		
		return l;
	}

}

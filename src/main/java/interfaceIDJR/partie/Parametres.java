package interfaceIDJR.partie;

import interfaceCommon.cd.View;
import interfaceCommon.component.ComponentUtil;
import interfaceCommon.component.SmallButton;
import interfaceCommon.component.SmallLabel;
import interfaceCommon.image.ImageManager;
import interfaceCommon.lang.Language;
import interfaceCommon.lang.LanguageManager;
import interfaceCommon.sound.SoundManager;
import interfaceIDJR.app.App;
import interfaceIDJR.cd.ControleurDonnees;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Parametres extends View {
	
	Label lTitle;
	Label lTitleLangue;
	Label lTitleMusic;
	
	Button bBack;
	Button bQuit;
	Button bLangFr;
	Button bLangEn;
	
	Button bIconMus;
	Label lMusicOn;
	Label lMusicOff;
	Label lMusicTxt;
	Slider sMusic;
	
	VBox vbRight;
	VBox vbLeft;
	VBox vbCenter;
	HBox hbUp;
	VBox vbLang;
	HBox HbGLang;
	VBox hbMusic;
	
	public Parametres() {
		
		this.setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", "bg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(ComponentUtil.getBaseUnit()*30, ComponentUtil.getBaseUnit()*30, false, false, false, false))));
		
		creationWidgets();
		creationContainers();
		placementWidgets();
		placementContainersDansPane();
		musique();
		langues();
		
		//Quit & Go back
		bQuit.setOnAction(event -> Platform.exit());
		bBack.setOnAction(event -> App.ctrlView().goBack());	

		//this.setVisible(false);
	}
	
	
	/** Creation of some widgets, a title and a button to quit this page and a button to quit the game**/
	private void creationWidgets() {
		lTitle = SmallLabel.createTitle(null);
		lTitle.textProperty().bind(LanguageManager.createBinding("parametres.titre"));
		
		bQuit = new SmallButton();
		bQuit.textProperty().bind(LanguageManager.createBinding("bouton.quitter"));
		bQuit.setPrefWidth(200);
		
		bBack = new SmallButton();
		bBack.textProperty().bind(LanguageManager.createBinding("bouton.retour"));
		bBack.setPrefWidth(200);
		
	}
	
	/** Creation of major containers (up, left, right and center) **/
	private void creationContainers() {
		hbUp = new HBox();
		hbUp.setAlignment(Pos.CENTER);
		
		vbRight = new VBox();
		vbRight.setAlignment(Pos.BOTTOM_RIGHT);
		
		vbLeft = new VBox();
		vbLeft.setAlignment(Pos.BOTTOM_LEFT);
		
		vbCenter = new VBox();
		vbCenter.setAlignment(Pos.CENTER);
	}

	private void placementWidgets() {
		hbUp.getChildren().add(lTitle);
		vbRight.getChildren().add(bQuit);
		vbLeft.getChildren().add(bBack);
	}
	
	private void placementContainersDansPane()
	{
		this.setTop(hbUp);
		this.setRight(vbRight);
		this.setLeft(vbLeft);	
		this.setCenter(vbCenter);
	}
	
	
	private void langues() {
		lTitleLangue = SmallLabel.createSubTitle(null);
		lTitleLangue.textProperty().bind(LanguageManager.createBinding("parametres.langue"));
		
		vbLang = new VBox();
		vbLang.setAlignment(Pos.CENTER);
		
		HbGLang = new HBox();
		HbGLang.setAlignment(Pos.CENTER);
		HbGLang.setPadding(new Insets(10, 50, 50, 50));
	    HbGLang.setSpacing(20);
	    
	    for(Language lang : LanguageManager.getLanguages()) {
			SmallButton langbutton = new SmallButton(lang.getName());
			langbutton.setPrefWidth(200);
			langbutton.setOnMouseClicked(event -> LanguageManager.select(lang));
			HbGLang.getChildren().add(langbutton);
		}
			
		vbLang.getChildren().add(lTitleLangue);
		vbLang.getChildren().add(HbGLang);
		
		vbCenter.getChildren().add(vbLang);
		
	}
	
	private void volumeSelector(String tradkey, String track) {
		Label lTitleMusic = SmallLabel.createSubTitle(null);
		lTitleMusic.textProperty().bind(LanguageManager.createBinding(tradkey));
		
		VBox hbMusic = new VBox();
		hbMusic.setAlignment(Pos.CENTER);
		
		Slider sMusic = new Slider(0,100,ControleurDonnees.volumeMusique);
		sMusic.setMaxWidth(400);
		sMusic.setShowTickLabels(true);
		sMusic.setShowTickMarks(true);
		sMusic.setMajorTickUnit(20);
		sMusic.setMinorTickCount(5);
		sMusic.setBlockIncrement(10);
		sMusic.setValue(100);
		String[] tracks=track.split(";");
		sMusic.valueProperty().addListener((obs,o,n)->{for(String t : tracks)SoundManager.setVolume(t,n.floatValue()/100);});
		
		hbMusic.getChildren().add(lTitleMusic);
		hbMusic.getChildren().add(sMusic);
		
		vbCenter.getChildren().add(hbMusic);
	}
	private void musique() {
		volumeSelector("parametres.musique", "background_music");
		volumeSelector("parametres.effetsonore", "effect;longeffect");
	}
	
	
	public void definirLangue(Labeled n) {
	}

}

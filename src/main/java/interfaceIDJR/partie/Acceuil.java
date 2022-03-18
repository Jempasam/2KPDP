package interfaceIDJR.partie;

import engine.card.CardContainers;
import engine.game.KPDPControler;
import engine.influence.InfluenceCard;
import interfaceCommon.cd.View;
import interfaceCommon.component.ButtonPanel;
import interfaceCommon.component.ComponentUtil;
import interfaceCommon.component.SmallButton;
import interfaceCommon.component.SmallLabel;
import interfaceCommon.component.kpdp.InfluenceCardComponent;
import interfaceCommon.image.ImageManager;
import interfaceCommon.lang.LanguageManager;
import interfaceCommon.sound.SoundManager;
import interfaceIDJR.app.App;
import interfaceIDJR.cd.ControleurDonnees.Ecrans;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Acceuil extends View {
	Label lTitle;
	Button bQuit;
	Button bCreateGame;
	Button bOptions;
	Button bRules;
	InfluenceCardComponent dailycard;
	InfluenceCardComponent dailycard2;
	
	public Acceuil() {
		this.setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", "bg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(ComponentUtil.getBaseUnit()*30, ComponentUtil.getBaseUnit()*30, false, false, false, false))));
		SoundManager.playSound("music", "background","background_music",MediaPlayer.INDEFINITE);
		
		//Haut
		HBox haut = new HBox();
		haut.setAlignment(Pos.CENTER);
		this.setTop(haut);
		
		lTitle = SmallLabel.createBigTitle("KPDP");
		lTitle.textProperty().bind(LanguageManager.createBinding("texte.titre"));
		haut.getChildren().add(lTitle);
				
		//Centre
		HBox centre = new HBox();
		
		centre.setSpacing(20);
		centre.setAlignment(Pos.CENTER);
		this.setCenter(centre);
		
		InfluenceCard card=CardContainers.DEFAULT_STOCKPILE.getRandom();
		dailycard=new InfluenceCardComponent((int)(Math.random()*10), KPDPControler.CARDTYPE_REGISTRY.getId(card.getType()), Color.TRANSPARENT, true, card.getPoints());
		
		card=CardContainers.DEFAULT_STOCKPILE.getRandom();
		dailycard2=new InfluenceCardComponent((int)(Math.random()*10), KPDPControler.CARDTYPE_REGISTRY.getId(card.getType()), Color.TRANSPARENT, true, card.getPoints());
		
		ButtonPanel bpButtons = new ButtonPanel(new String[] {"Créer un lobby", "Options", "Aide à la configuration"}, 2);
		bpButtons.getButtons()[0].textProperty().bind(LanguageManager.createBinding("bouton.partie"));
		bpButtons.getButtons()[0].setOnAction(event -> {App.ctrlView().goTo(Ecrans.SEARCHROOM);});
		bpButtons.getButtons()[1].textProperty().bind(LanguageManager.createBinding("bouton.parametre"));
		bpButtons.getButtons()[1].setOnAction(event -> {App.ctrlView().goTo(Ecrans.PARAMETRES);});
		bpButtons.getButtons()[2].textProperty().bind(LanguageManager.createBinding("bouton.rules"));
		bpButtons.getButtons()[2].setOnAction(event -> {App.ctrlView().goTo(Ecrans.REGLES);});
		Region spacing=new Region(), spacing2=new Region();
		HBox.setHgrow(spacing, Priority.SOMETIMES);
		HBox.setHgrow(spacing2, Priority.SOMETIMES);
		centre.getChildren().addAll(spacing, dailycard, bpButtons, dailycard2, spacing2);
		
		//Bas
		VBox bas = new VBox();
		setBottom(bas);
		
		bQuit = new SmallButton();
		bQuit.textProperty().bind(LanguageManager.createBinding("bouton.quitter"));
		bQuit.setOnAction(e -> {Platform.exit();});
		bas.getChildren().add(bQuit);
	}
	
	@Override
	public void open(View from) {
		InfluenceCard card=CardContainers.DEFAULT_STOCKPILE.getRandom();
		dailycard.update((int)(Math.random()*10), KPDPControler.CARDTYPE_REGISTRY.getId(card.getType()), Color.TRANSPARENT, true, card.getPoints());
		
		card=CardContainers.DEFAULT_STOCKPILE.getRandom();
		dailycard2.update((int)(Math.random()*10), KPDPControler.CARDTYPE_REGISTRY.getId(card.getType()), Color.TRANSPARENT, true, card.getPoints());
	}
}
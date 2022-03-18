package interfaceTable.partie;

import interfaceCommon.cd.View;
import interfaceCommon.component.ComponentUtil;
import interfaceCommon.component.RadioButtonList;
import interfaceCommon.component.SmallButton;
import interfaceCommon.component.SmallLabel;
import interfaceCommon.image.ImageManager;
import interfaceCommon.lang.LanguageManager;
import interfaceTable.app.App;
import interfaceTable.cd.ControleurDonnees.Ecrans;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Le menu d'aide à la configuration d'une partie
 * @author Jessica BOTTO
 *
 */
public class AideConfig extends View {
    Label Titre;
    Button bRetour;
    VBox vbRight;
	VBox vbLeft;
	VBox vbCenter;
	HBox hbUp;
	Button bBack;
	Button bQuit;
	HBox hbCenter;

    
    public AideConfig () {        
    	
    	this.setBackground(ComponentUtil.BRICK_BACKGROUND);
		
    	creationWidgets();
		creationContainers();
		placementWidgets();
		placementContainersInPane();
		
    	Border maBordure = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(8)));
    	
        HBox Haut = new HBox();
        Titre = SmallLabel.createTitle(null);
        Titre.textProperty().bind(LanguageManager.createBinding("aide.haut.titre"));
        Titre.setFont(Font.font("Vivaldi", FontWeight.BOLD, 80));
        Haut.setPrefHeight(130);
        Haut.setAlignment(Pos.CENTER);
        Haut.getChildren().add(Titre);
        
       	
       	VBox CD = new VBox();
		CD.setPrefHeight(150);
		CD.setPrefWidth(850);
		CD.setBorder(maBordure);
		CD.setAlignment(Pos.CENTER);
		
		CheckBox BotOn = new CheckBox("Activer les BOTs"+"\n");
		BotOn.textProperty().bind(LanguageManager.createBinding("aide.bot.0"));
		BotOn.setFont(Font.font("Calibri", FontWeight.NORMAL, 25));
		CD.getChildren().add(BotOn);
	
		
		Label tBot = new SmallLabel();
		tBot.setWrapText(true);
		tBot.textProperty().bind(LanguageManager.createBinding("aide.bot.1"));
		tBot.setFont(Font.font("Calibri", FontWeight.NORMAL, 25));
		CD.getChildren().add(tBot);
		
		RadioButtonList DiffBot=new RadioButtonList(new String[]{"Facile", "Intermédiaire", "Difficile"}, 1);
		DiffBot.getButtons()[0].textProperty().bind(LanguageManager.createBinding("config.botdif.facile"));
		DiffBot.getButtons()[1].textProperty().bind(LanguageManager.createBinding("config.botdif.inter"));
		DiffBot.getButtons()[2].textProperty().bind(LanguageManager.createBinding("config.botdif.diffi"));
		CD.getChildren().add(DiffBot);
		
		Label tChoix = new SmallLabel();
		tChoix.setWrapText(true);
		tChoix.textProperty().bind(LanguageManager.createBinding("aide.bot.2"));
		tChoix.setFont(Font.font("Calibri", FontWeight.NORMAL, 25));
		CD.getChildren().add(tChoix);
       	
		Label tPA = new SmallLabel();
		tPA.setWrapText(true);
		tPA.textProperty().bind(LanguageManager.createBinding("aide.bot.3"));
		tPA.setFont(Font.font("Calibri", FontWeight.NORMAL, 25));
		CD.getChildren().add(tPA);
				
		
		Label PA = new SmallLabel();
		PA.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
		CD.getChildren().add(PA);
		
		
		
       	VBox CG = new VBox();
		CG.setPrefHeight(150);
		CG.setPrefWidth(1060);
		CG.setBorder(maBordure);
		CG.setAlignment(Pos.TOP_RIGHT);
		
        HBox HautG = new HBox();
		HautG.setPrefWidth(150);
        HautG.setPrefHeight(430);
        HautG.setBorder(maBordure);
        CG.getChildren().add(HautG);
        
        
		
        VBox ImgG = new VBox();
        ImgG.setPrefWidth(410);
        ImgG.setPrefHeight(400);
        ImgG.setBorder(maBordure);
        HautG.getChildren().add(ImgG);        
	
       	
		VBox gConfig = new VBox();
		gConfig.setMinWidth(640);
		gConfig.setPrefHeight(400);
		gConfig.setSpacing(20);
		gConfig.setBorder(maBordure);
		gConfig.setAlignment(Pos.CENTER_LEFT);
		HautG.getChildren().add(gConfig);
		
		Label OP = new SmallLabel();
		OP.textProperty().bind(LanguageManager.createBinding("aide.modifordre.titre"));
        OP.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 25));
        OP.setTextFill(Color.BLACK);
	    OP.setPadding(new Insets(0,0,0,50));
        gConfig.getChildren().add(OP);
        
		
		
        HBox DrHaut = new HBox();
        DrHaut.setPrefWidth(50);
        DrHaut.setPrefHeight(120);
        DrHaut.setSpacing(50);
		DrHaut.setBorder(maBordure);
        DrHaut.setAlignment(Pos.CENTER_LEFT);
        gConfig.getChildren().add(DrHaut);
        
        Label tOP = new SmallLabel();
        tOP.setWrapText(true);
        tOP.textProperty().bind(LanguageManager.createBinding("aide.modifordre.1"));
        tOP.setFont(Font.font("Calibri", FontWeight.NORMAL, 25));
        tOP.setTextFill(Color.BLACK);
	    tOP.setPadding(new Insets(0,0,0,50));
        DrHaut.getChildren().add(tOP);
        

        HBox BoxA = new HBox();
        BoxA.setPrefWidth(50);
        BoxA.setPrefHeight(50);
        BoxA.setSpacing(50);
		BoxA.setBorder(maBordure);
        BoxA.setAlignment(Pos.CENTER);
		gConfig.getChildren().add(BoxA);

        
        Button bAlea = new SmallButton("Aléatoire");
        bAlea.textProperty().bind(LanguageManager.createBinding("aide.modifordre.alea"));
        bAlea.setFont(Font.font(30));
		bAlea.setPrefSize(200, 40);
		bAlea.setAlignment(Pos.CENTER);
		BoxA.getChildren().add(bAlea);
        
       
		
		HBox BoxtA = new HBox();
		BoxtA.setPrefWidth(50);
        BoxtA.setPrefHeight(100);
        BoxtA.setSpacing(50);
		BoxtA.setBorder(maBordure);
        BoxtA.setAlignment(Pos.CENTER);
		gConfig.getChildren().add(BoxtA);
		
		Label tAlea = new SmallLabel();
		tAlea.setWrapText(true);
		tAlea.textProperty().bind(LanguageManager.createBinding("aide.modifordre.2"));
		tAlea.setFont(Font.font("Calibri", FontWeight.NORMAL, 25));
        tAlea.setTextFill(Color.BLACK);
        BoxtA.getChildren().add(tAlea);
		
        
        HBox BoxCN = new HBox();
        BoxCN.setMinWidth(450);
		BoxCN.setMinHeight(320);
		BoxCN.setBorder(maBordure);
		CG.getChildren().add(BoxCN);

		VBox tColor = new VBox();
		tColor.setMinWidth(600);
		tColor.setMinHeight(200);
		tColor.setBorder(maBordure);
		BoxCN.getChildren().add(tColor);
		
		Label tiCN = new SmallLabel();
		tiCN.setWrapText(true);
		tiCN.textProperty().bind(LanguageManager.createBinding("aide.couleurnom.titre"));
		tiCN.setFont(Font.font("Calibri", FontWeight.EXTRA_BOLD, 25));
	    tiCN.setTextFill(Color.BLACK);
	    tiCN.setPadding(new Insets(20,0,0,30));
	    tColor.getChildren().add(tiCN);
	    
	    Label tC = new SmallLabel();
	    tC.setWrapText(true);
	    tC.textProperty().bind(LanguageManager.createBinding("aide.couleurnom.1"));
	    tC.setFont(Font.font("Calibri", FontWeight.NORMAL, 25));
	    tC.setTextFill(Color.BLACK);
	    tC.setPadding(new Insets(0,0,0,30));
	    tColor.getChildren().add(tC);
	    
	    VBox imgD = new VBox();
	    imgD.setMinWidth(450);
	    imgD.setMinHeight(100);
	    imgD.setBorder(maBordure);
	    BoxCN.getChildren().add(imgD);
				
	    
		
		HBox BoxN = new HBox();
		BoxN.setMinWidth(450);
		BoxN.setMinHeight(80);
		BoxN.setBorder(maBordure);
		BoxN.setAlignment(Pos.CENTER);
		CG.getChildren().add(BoxN);
		
		Label tN = new SmallLabel();
		tN.textProperty().bind(LanguageManager.createBinding("aide.couleurnom.2"));
		tN.setFont(Font.font("Calibri", FontWeight.NORMAL, 25));
		tN.setTextFill(Color.BLACK);
		BoxN.getChildren().add(tN);
		
		
        
        HBox centre = new HBox();
        centre.setMinWidth(50);
        centre.setBorder(maBordure);
        centre.getChildren().add(CG);
        centre.getChildren().add(CD);
	
        
        
        VBox bas = new VBox();
		bas.setPrefHeight(100);
		bas.setPrefWidth(75);
		bas.setAlignment(Pos.CENTER);
		
		bRetour = new SmallButton("J'ai compris !");
		bRetour.textProperty().bind(LanguageManager.createBinding("aide.bas.button"));
		bRetour.setFont(Font.font(30));
		bRetour.setPrefSize(250, 75);
		bRetour.setOnAction(event -> App.ctrlView().goTo(Ecrans.ACCUEIL));
		bas.getChildren().add(bRetour);
		
		
		

        this.setTop(Haut);
		this.setCenter(centre);
		this.setBottom(bas);
    }
    private void creationContainers() {
		hbUp = new HBox();
		hbUp.setAlignment(Pos.CENTER);

		vbRight = new VBox();
		vbRight.setAlignment(Pos.BOTTOM_RIGHT);
		vbRight.setPrefWidth(0);

		vbLeft = new VBox();
		vbLeft.setAlignment(Pos.BOTTOM_LEFT);
		vbLeft.setPrefWidth(350);
		vbLeft.setPadding(new Insets(30, 30, 30, 40));

		vbCenter = new VBox();
		vbCenter.setAlignment(Pos.TOP_LEFT);
		vbCenter.setSpacing(20);
	}
    private void creationWidgets(){		
		bQuit = new SmallButton();
		bQuit.textProperty().bind(LanguageManager.createBinding("bouton.quitter"));
		bQuit.setPrefWidth(200);
			
		bBack = new SmallButton();
		bBack.textProperty().bind(LanguageManager.createBinding("bouton.retour"));
		bBack.setPrefWidth(200);
    }
		private void placementContainersInPane(){
			this.setTop(hbUp);
			this.setRight(vbRight);
			this.setLeft(vbLeft);
			this.setCenter(hbCenter);	
		}

    private void placementWidgets() {
		vbLeft.setBackground(new Background(new BackgroundFill(Color.FIREBRICK, CornerRadii.EMPTY, null)));
		vbRight.setBackground(new Background(new BackgroundFill(Color.FIREBRICK, CornerRadii.EMPTY, null)));	
	}
}
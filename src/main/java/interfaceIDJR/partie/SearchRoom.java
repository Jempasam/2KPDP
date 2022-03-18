package interfaceIDJR.partie;

import interfaceCommon.cd.View;
import interfaceCommon.component.ComponentUtil;
import interfaceCommon.component.SmallButton;
import interfaceCommon.image.ImageManager;
import interfaceCommon.lang.LanguageManager;
import interfaceIDJR.app.App;
import interfaceIDJR.cd.ControleurDonnees.Ecrans;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Searching room for parties
 * @author Léa CUMONT
 */
public class SearchRoom extends View {
	
	Label lTitle;
	Label lTitleGamesNames;
	Label lTitleNbPlayers;
	Label lTitleNbBots;
	
	HBox hbUp;
	VBox vbRight;
	VBox vbLeft;
	HBox hbCenter;
	VBox vbBottom;
	
	VBox vbGamesNames;
	VBox vbNbPlayers;
	VBox vbNbBots;
	
	Button bBack;
	Button bQuit;
	Button bInterfaceJ;
	Button bWaiting;
	
	Line line1;
	Line line2;
	Line separator1;
	Line separator2;
	Line separator3;
	
	public SearchRoom() {
		//Background images
		this.setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", "bg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(ComponentUtil.getBaseUnit()*30, ComponentUtil.getBaseUnit()*30, false, false, false, false))));
	
		creationWidgets();
		creationContainers();
		placementWidgets();
		placementContainersInPane();
		searchTable();
		
		bInterfaceJ = new SmallButton("Interface joueur (cest pour tester)");
		bInterfaceJ.setPrefWidth(400);
		hbCenter.getChildren().add(bInterfaceJ);
		
		bWaiting = new SmallButton("Salle d'attente (cest aussi pour tester)");
		bWaiting.setPrefWidth(400);
		hbCenter.getChildren().add(bWaiting);
		
		bInterfaceJ.setOnAction(event -> App.ctrlView().goTo(Ecrans.JOUEURINT));
		bInterfaceJ.setOnAction(event -> App.ctrlView().goTo(Ecrans.WAITINGROOM));
		
		bQuit.setOnAction(event -> Platform.exit());
		bBack.setOnAction(event -> App.ctrlView().goBack());
	}
	

	private void creationWidgets(){
		lTitle = new Label();
		lTitle.textProperty().bind(LanguageManager.createBinding("titre.recherchepartie"));
		lTitle.setFont(Font.font("Vivaldi", FontWeight.BOLD, 100));
		
		bQuit = new SmallButton();
		bQuit.textProperty().bind(LanguageManager.createBinding("bouton.quitter"));
		bQuit.setPrefWidth(200);
			
		bBack = new SmallButton();
		bBack.textProperty().bind(LanguageManager.createBinding("bouton.retour"));
		bBack.setPrefWidth(200);
		
		//CREATING SEPARATORS LINES 
		//Vertical line
		line1 = new Line(50, 0, 50, 315);
		line1.setStrokeWidth(2);
		line2 = new Line(50, 0, 50, 315);
		line2.setStrokeWidth(2);
				
		//Horizontal lines
		separator1 = new Line(0, 50, 400, 50);
		separator1.setStrokeWidth(2);
		separator2 = new Line(0, 50, 200, 50);
		separator2.setStrokeWidth(2);
		separator3 = new Line(0, 50, 200, 50);
		separator3.setStrokeWidth(2);
		
		//Adding titles and text ===TEST===
		lTitleGamesNames = new Label();
		lTitleNbPlayers = new Label();
		lTitleNbBots = new Label();
	
		lTitleGamesNames.textProperty().bind(LanguageManager.createBinding("recherchepartie.titre.nompartie"));
		lTitleNbPlayers.textProperty().bind(LanguageManager.createBinding("recherchepartie.titre.nbjoueur"));
		lTitleNbBots.textProperty().bind(LanguageManager.createBinding("recherchepartie.titre.nbbots"));
    	
		lTitleGamesNames.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
		lTitleNbPlayers.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
		lTitleNbBots.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
		
		lTitleGamesNames.setPadding(new Insets(10,10,10,10));
		lTitleNbPlayers.setPadding(new Insets(10,10,10,10));
		lTitleNbBots.setPadding(new Insets(10,10,10,10));
	}
	
	
	private void creationContainers(){
		hbUp = new HBox();
		hbUp.setMinHeight(150);
		hbUp.setAlignment(Pos.CENTER);
				
		vbRight = new VBox();
		vbRight.setAlignment(Pos.BOTTOM_RIGHT);
		vbRight.setPrefWidth(100);
		
		vbLeft = new VBox();
		vbLeft.setAlignment(Pos.BOTTOM_LEFT);
		vbLeft.setPrefWidth(100);
		
		hbCenter = new HBox();
		hbCenter.setAlignment(Pos.CENTER);
		hbCenter.setPadding(new Insets(40,40,40,40));
		
		vbGamesNames = new VBox();
		vbGamesNames.setAlignment(Pos.CENTER);
		vbGamesNames.setPrefWidth(100);
		        
		vbNbPlayers = new VBox();
		vbNbPlayers.setAlignment(Pos.CENTER);
		vbNbPlayers.setPrefWidth(100);
		
		vbNbBots = new VBox();
		vbNbBots.setAlignment(Pos.CENTER);
		vbNbBots.setPrefWidth(100);
	}
	
	private void placementWidgets(){
		hbUp.getChildren().add(lTitle);
		vbLeft.getChildren().add(bBack);
		vbRight.getChildren().add(bQuit);
	}
	
	private void placementContainersInPane(){
		this.setTop(hbUp);
		this.setRight(vbRight);
		this.setLeft(vbLeft);
		this.setCenter(hbCenter);	
	}
	
	
	private void searchTable() {			
		vbGamesNames.getChildren().addAll(lTitleGamesNames,separator1);
		vbNbPlayers.getChildren().addAll(lTitleNbPlayers, separator2);
		vbNbBots.getChildren().addAll(lTitleNbBots, separator3);
		        
		for (int i = 0; i < 5; i++)
		{
			//creating local variables
		    Label gameName = new Label("Button " + (i+1));
		    Label nbPlayer = new Label((i+1) + " / 6"); 
		    Label nbBots =  new Label((i+1) + " / 6"); 
		    gameName.setPadding(new Insets(10,10,10,10));
		    nbPlayer.setPadding(new Insets(10,10,10,10));
		    nbBots.setPadding(new Insets(10,0,10,0));
		    gameName.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
		    nbPlayer.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
		    nbBots.setFont(Font.font("Arial", FontWeight.NORMAL, 25)); 
		    
		    //Local horizontal lines separating between labels
		    Line separator4 = new Line(0, 150, 400, 150);
		    separator4.setStrokeWidth(2);
		    Line separator5 = new Line(0, 150, 200, 150);
		    separator5.setStrokeWidth(2);
		    Line separator6 = new Line(0, 150, 200, 150);
		    separator6.setStrokeWidth(2);
		    	
		    //Change colors if hovering
		    gameName.setOnMouseEntered(event ->  gameName.setTextFill(Color.DEEPSKYBLUE));
			gameName.setOnMouseExited(event ->  gameName.setTextFill(Color.BLACK));
			
			//En gros lorsqu'on clique sur ce le nom d'une partie, on doit pouvoir y aller
		    //gameName.setOnMouseClicked(e -> GoToThisGame(game));
		    
		    vbGamesNames.getChildren().addAll(gameName, separator4);
		    vbNbPlayers.getChildren().addAll(nbPlayer, separator5);
		    vbNbBots.getChildren().addAll(nbBots,separator6);
		}
		         
		hbCenter.getChildren().addAll(vbGamesNames, line1, vbNbPlayers, line2, vbNbBots);
	}
}
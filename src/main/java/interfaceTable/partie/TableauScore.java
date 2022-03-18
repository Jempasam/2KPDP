package interfaceTable.partie;

import engine.game.GameViewer;
import interfaceCommon.cd.View;
import interfaceCommon.component.ComponentUtil;
import interfaceCommon.component.SmallButton;
import interfaceCommon.component.SmallLabel;
import interfaceCommon.image.ImageManager;
import interfaceTable.app.App;
import interfaceTable.cd.ControleurDonnees.Ecrans;
import interfaceTable.component.ScorePanel;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class TableauScore extends View{
	
	Label Titre;
	Button bNouvellePartie;
	ScorePanel pScores;
	
	VBox gauche;
	VBox droite;
	VBox centre;
	HBox haut;
	HBox bas;
	
	private SmallLabel lGagnant;
	
	private GameViewer game;
	
	public TableauScore() {
	
		this.setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", "bg_wood"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(ComponentUtil.getBaseUnit()*15, ComponentUtil.getBaseUnit()*15, false, false, false, false))));
		
		//Centre
		centre=new VBox();
		centre.setAlignment(Pos.CENTER);
			//Equipe
			lGagnant=SmallLabel.createTitle("");
			centre.getChildren().add(lGagnant);
		
		//Haut
		haut = new HBox();
		Titre = SmallLabel.createBigTitle("Félicitations !!");
		haut.setMinHeight(150);
		haut.setAlignment(Pos.CENTER);
		haut.getChildren().add(Titre);
		
		//Gauche
		gauche = new VBox();
		gauche.setMinHeight(150);
		gauche.setAlignment(Pos.CENTER_LEFT);
		
		//Bas
		bas=new HBox();
		bas.setAlignment(Pos.CENTER_RIGHT);
			//Rejouer
			SmallButton bRejouer = new SmallButton("Rejouer",2);
			bRejouer.cancelButtonProperty();
			bRejouer.setOnAction(e -> {App.ctrlView().goTo(Ecrans.CONFIG);});
			//Menu
			SmallButton bMenu = new SmallButton("Menu Principal",2);
			bMenu.cancelButtonProperty();
			bMenu.setOnAction(e -> {App.ctrlView().goTo(Ecrans.ACCUEIL);});
			//Fermer le jeu
			SmallButton bQuitter = new SmallButton("Quitter",2);
			bQuitter.cancelButtonProperty();
			bQuitter.setOnAction(e -> {Platform.exit();});
			bas.getChildren().addAll(bRejouer, new Region(), bMenu, new Region(), bQuitter);
		
		//Scores
		this.setTop(haut);
		this.setBottom(bas);
		this.setLeft(gauche);
		this.setRight(droite);
		this.setCenter(centre);
	}
	public void setScoreGame(GameViewer game) {
		gauche.getChildren().clear();
		gauche.getChildren().add(new ScorePanel(game));
		this.game=game;
		lGagnant.setText("Le gagnant est le joueur "+game.idOfTeam(game.calculateWinner()));
	}
}

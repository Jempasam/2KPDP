package interfaceTable.app;

import java.util.function.Supplier;

import engine.game.GameControler;
import engine.game.GameViewer;
import engine.game.KPDPControler;
import engine.game.parameters.GameParameters;
import engine.teams.TeamViewer;
import engine.teams.controler.TeamControler;
import interfaceCommon.cd.CtrlView;
import interfaceCommon.cd.View;
import interfaceCommon.image.ImageManager;
import interfaceTable.cd.ControleurDonnees.Ecrans;
import interfaceTable.controler.ClickControler;
import interfaceTable.partie.Acceuil;
import interfaceTable.partie.AideConfig;
import interfaceTable.partie.ConfigPartie;
import interfaceTable.partie.EnPartie;
import interfaceTable.partie.Parametres;
import interfaceTable.partie.TableauScore;
import interfaceTable.regles.GamesRules;
import interfaceTable.util.KeyListener;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
	
	private StackPane root = new StackPane();
	Scene sc = new Scene(root);
	private Node ecranCourant = null;
	private static EnPartie ecranPartie=null;
	private static TableauScore ecranScore=null;
	private static BooleanProperty advanced_mode=new SimpleBooleanProperty(false);
	
	private static CtrlView<Ecrans> ctrlView;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		KeyListener.init(sc);
		
		ImageManager.setUnfindedHandler("domain", ImageManager.AUTOGEN_UNDEFINED_HANDLER);
		ImageManager.setUnfindedHandler("influence", ImageManager.AUTOGEN_UNDEFINED_HANDLER);
		ImageManager.setUnfindedHandler("misc", (bundle,name)->{
			System.err.println("Texture "+name+" is missing.");
			Platform.exit();
			return null;
		});
		
		primaryStage.setTitle("De cape et d'épée (version Table)");
		primaryStage.setScene(sc);
		
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitHint("");
		
		ecranPartie=new EnPartie();
		ecranScore=new TableauScore();
		
		ctrlView = new CtrlView(primaryStage);
		View a=new Acceuil();
		ctrlView.saveScreen(Ecrans.ACCUEIL, a);
		ctrlView.saveScreen(Ecrans.CONFIG, new ConfigPartie());
		ctrlView.saveScreen(Ecrans.AIDECONFIG, new AideConfig());
		ctrlView.saveScreen(Ecrans.PARAMETRES, new Parametres());
		ctrlView.saveScreen(Ecrans.RULES, new GamesRules());
		ctrlView.saveScreen(Ecrans.TABSCORE, ecranScore);
		ctrlView.saveScreen(Ecrans.ENPARTIE, ecranPartie);
		
		ctrlView().goTo(Ecrans.ACCUEIL);
		
		primaryStage.show();
	}
	public static void lancement(String[] args) {
		App.launch(args);
	}
	
	public void afficherEcran(Node n) {
		if (ecranCourant != null)
			ecranCourant.setVisible(false);
		n.setVisible(true);
		ecranCourant = n;

	}
	
	public static CtrlView<Ecrans> ctrlView(){
		return ctrlView;
	}
	
	public static void createPartie(GameParameters parameters, Supplier<TeamControler> botsupplier, int nb_bot) {
		GameControler newgame=KPDPControler.startGame(parameters);
		int i=0;
		for(TeamViewer team : newgame.getTeams()) {
			if(i<nb_bot)team.setControler(botsupplier.get());
			else team.setControler(new ClickControler(ecranPartie));
			i++;
		}
		ecranPartie.setGame(newgame);
	}
	public static void setScoreGame(GameViewer game) {
		ecranScore.setScoreGame(game);
	}
	public static void setPartieText(String text) {
		ecranPartie.setCenterText(text);
	}
	public static void setHandVisible(boolean visible) {
		ecranPartie.getHand().setVisible(visible);
	}
	
	public static void setAdvancedMode(boolean b) {
		advanced_mode.set(b);
	}
	
	public static boolean getAdvancedMode() {
		return advanced_mode.get();
	}
	
	public static void switchAdvancedMode() {
		advanced_mode.set(!advanced_mode.get());
	}
	
	public static BooleanProperty advancedModeProperty() {
		return advanced_mode;
	}
}

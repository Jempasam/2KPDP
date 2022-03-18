package interfaceTable.partie;

import java.util.function.Supplier;

import engine.card.CardContainers;
import engine.card.container.CardPile;
import engine.game.parameters.GameParameters;
import engine.influence.InfluenceCard;
import engine.objective.ObjectiveCard;
import engine.teams.controler.TeamControler;
import interfaceCommon.addon.AddonManager;
import interfaceCommon.cd.View;
import interfaceCommon.component.ActivableRollComponent;
import interfaceCommon.component.ComponentUtil;
import interfaceCommon.component.IntSlider;
import interfaceCommon.component.IntTextField;
import interfaceCommon.component.RadioButtonList;
import interfaceCommon.component.SmallButton;
import interfaceCommon.component.SmallLabel;
import interfaceCommon.component.TransScrollPane;
import interfaceCommon.component.VRadioButtonList;
import interfaceCommon.image.ImageManager;
import interfaceCommon.lang.LanguageManager;
import interfaceCommon.sound.SoundManager;
import interfaceTable.app.App;
import interfaceTable.bots.Bots;
import interfaceTable.cd.ControleurDonnees.Ecrans;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.ColorAdjust;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ConfigPartie extends View {
	int nbBots;
	Label lTitre;
	VBox vbCardPile;
	VRadioButtonList<CardPile<?>> rblObjectif;
	VRadioButtonList<CardPile<?>> rblInfluence;
	ActivableRollComponent roller;

	@Override
	public void open(View from) {
		roller.close();
		
		vbCardPile.getChildren().clear();
		//Pioche de carte objectifs
		rblObjectif=addVRadioButtonField(vbCardPile, "config.piocheobj", CardContainers.DEFAULT_OBJECTIVES,
				AddonManager.getObjectiveStockpiles().keySet().toArray(new String[0]),
				AddonManager.getObjectiveStockpiles().values().toArray(new CardPile<?>[0]));
		//Pioche de carte influences
		rblInfluence=addVRadioButtonField(vbCardPile, "config.piocheinf", CardContainers.DEFAULT_STOCKPILE,
				AddonManager.getInfluenceStockpiles().keySet().toArray(new String[0]),
				AddonManager.getInfluenceStockpiles().values().toArray(new CardPile<?>[0]));
	}
	
	public IntTextField addIntTextField(Pane p, String name, int min, int def, int max) {
		Label l = new SmallLabel();
		l.textProperty().bind(LanguageManager.createBinding(name));
		IntTextField tf = new IntTextField(def,min,max);
		p.getChildren().addAll(l,tf);
		return tf;
	}
	public RadioButtonList addRadioButtonField(Pane p, String name, int def, String[] strs) {
		Label l = new SmallLabel();
		l.textProperty().bind(LanguageManager.createBinding(name));
		RadioButtonList rd=new RadioButtonList(strs, def);
		p.getChildren().addAll(l,rd);
		return rd;
	}
	public <T> VRadioButtonList<T> addVRadioButtonField(Pane p, String name, T def, String[] strs, T[] objs) {
		Label l = new SmallLabel();
		l.textProperty().bind(LanguageManager.createBinding(name));
		VRadioButtonList<T> rd=new VRadioButtonList<>(def, strs, objs);
		p.getChildren().addAll(l,rd);
		return rd;
	}
	public ConfigPartie() {
		Button bLancer;
		Button bRetour;
		
		this.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,null)));
        
    	Border bLight = new Border(new BorderStroke(Color.DIMGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2)));
		

		// Haut (Titre)
		HBox hbHaut = new HBox();
		hbHaut.setBackground(ComponentUtil.BRICK_BACKGROUND);
		hbHaut.setAlignment(Pos.CENTER);
		this.setTop(hbHaut);
			//Titre
			lTitre =SmallLabel.createTitle("titre");
			lTitre.textProperty().bind(LanguageManager.createBinding("config.titre"));
	        hbHaut.getChildren().add(lTitre);
	       
        HBox centre = new HBox();
        centre.setBorder(bLight);
        setCenter(centre);
		
		// Panneau des joueurs
		VBox pPlayer = new VBox();
		pPlayer.setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", "bg_wood"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0 , true,  true, false, false))));
		pPlayer.setBorder(bLight);
		pPlayer.setAlignment(Pos.CENTER);
		centre.getChildren().add(pPlayer);
		HBox.setHgrow(pPlayer, Priority.SOMETIMES);
			// Bouton aléatoire
			/*Button bAlea = new SmallButton("Aléatoire");
			pPlayer.getChildren().add(bAlea);*/
		

		//Paramètres de partie
        VBox pParametre = new VBox();
        pParametre.setBorder(bLight);
        pParametre.setAlignment(Pos.TOP_CENTER);
        pParametre.setBackground(ComponentUtil.SOFT_SCROLL_BACKGROUND);
        centre.getChildren().add(pParametre);
        HBox.setHgrow(pParametre, Priority.SOMETIMES);
	        //Paramètre de partie haut
			VBox pParametreHaut = new VBox();
			pParametreHaut.setBorder(bLight);
			pParametreHaut.setPadding(new Insets(5,5,5,5));
			pParametreHaut.setAlignment(Pos.CENTER);
			pParametreHaut.setBackground(ComponentUtil.SOFT_SCROLL_BACKGROUND);
			pParametre.getChildren().add(pParametreHaut);
				//Titre
				Label lParametre = SmallLabel.createSubTitle("");
				lParametre.textProperty().bind(LanguageManager.createBinding("config.parametres"));
				pParametreHaut.getChildren().add(lParametre);
				//Separateur
				pParametreHaut.getChildren().add(new Separator());
				//Difficulté des bots

				VRadioButtonList<Supplier<?>> rblDifficulty=addVRadioButtonField(pParametreHaut, "config.botdif.titre", Bots.factory.get("medium")::clone ,
						new String[]{"Facile", "Intermédiare", "Difficile"},
						new Supplier<?>[] {Bots.factory.get("easy")::clone, Bots.factory.get("medium")::clone, Bots.factory.get("hard")::clone});
				rblDifficulty.getButtons()[0].textProperty().bind(LanguageManager.createBinding("config.botdif.facile"));
				rblDifficulty.getButtons()[1].textProperty().bind(LanguageManager.createBinding("config.botdif.inter"));
				rblDifficulty.getButtons()[2].textProperty().bind(LanguageManager.createBinding("config.botdif.diffi"));
				//Nombre de joueurs
				IntTextField nbPlayerNumber=addIntTextField(pParametreHaut, "config.playernumber", 2, 6, 6);
				//Nombre de bot
				SmallLabel lBot=new SmallLabel();
				lBot.textProperty().bind(LanguageManager.createBinding("config.botnumber"));
				IntSlider nbBotNumber = new IntSlider(0,5,6,1);
				nbBotNumber.setEffect(new ColorAdjust(0, 0, -0.8, 0));
				nbBotNumber.maxProperty().bind(nbPlayerNumber.valueProperty());
				pParametreHaut.getChildren().addAll(lBot,nbBotNumber);
			//Paramètre de partie bas
			VBox pParametreBas = new VBox();
			pParametreBas.setAlignment(Pos.CENTER);
			pParametreBas.setBorder(bLight);
			pParametre.getChildren().add(pParametreBas);
			pParametreBas.visibleProperty().bind(App.advancedModeProperty());
				//Titre
				Label lParametresAvances = SmallLabel.createSubTitle("");
				pParametreBas.getChildren().add(lParametresAvances);
				//Paramètres avancés
				roller=new ActivableRollComponent();
				lParametresAvances.textProperty().bind(Bindings.createStringBinding(()->LanguageManager.get(roller.unrolled() ? "config.advparametres" : "config.advparametres2" ), roller.unrolledProperty()));
				pParametreBas.getChildren().add(roller);
				
				ScrollPane spParametreAvances = new TransScrollPane();
				spParametreAvances.setHbarPolicy(ScrollBarPolicy.NEVER);
				spParametreAvances.setFitToWidth(true);
				roller.getContents().add(spParametreAvances);
				VBox pParametresAvances=new VBox();
				pParametresAvances.setBackground(ComponentUtil.SOFT_SCROLL_BACKGROUND);
				pParametresAvances.setPadding(new Insets(10,10,10,10));
				pParametresAvances.setAlignment(Pos.CENTER);
				spParametreAvances.setContent(pParametresAvances);
					//Nombre de manches
					IntTextField nbRoundNumber=addIntTextField(pParametresAvances, "config.roundnumber", 1, 6, Integer.MAX_VALUE);
					//Taille maximale des colonnes
					IntTextField nbColumnSize=addIntTextField(pParametresAvances, "config.maxcolsize", 1, 25, 30);
					//Taille de la main
					IntTextField nbHandSize=addIntTextField(pParametresAvances, "config.handsize", 1, 3, 10);
					//Nombre de colonne par joueur
					IntTextField nbColonneNum=addIntTextField(pParametresAvances, "config.columnnumber", 1, 1, 4);
					
					vbCardPile=new VBox();
					vbCardPile.setAlignment(Pos.CENTER);
					pParametresAvances.getChildren().add(vbCardPile);
		//Bas de l'écran
		HBox bas = new HBox();
		bas.setBackground(ComponentUtil.BRICK_BACKGROUND);
		bas.setSpacing(100);
		setBottom(bas);
			//Bouton retour
			bRetour = new SmallButton("",2f);
			bRetour.textProperty().bind(LanguageManager.createBinding("bouton.retour"));
			bRetour.setOnAction(event -> App.ctrlView().goBack());
			bas.getChildren().add(bRetour);
			//Espace
			Region spacing =new Region();
			HBox.setHgrow(spacing, Priority.ALWAYS);
			bas.getChildren().add(spacing);
			// Bouton de lancement
			bLancer = new SmallButton("Lancer",2f);
			bLancer.textProperty().bind(LanguageManager.createBinding("config.lancer"));
			bLancer.setOnAction(event -> {
				//LANCEMENT D'UNE PARTIE
				GameParameters parameters=new GameParameters();
				
				//On récupères les paramètres
				// - Parmamètres de base
				// - - Nombre de joueur et bots
				parameters.nb_team=nbPlayerNumber.getValue();
				// - - Nombre de bots
				int nb_bot=((int)nbBotNumber.getValue());
				// - - IA des bots
				Supplier<TeamControler> bot_factory=(Supplier<TeamControler>)rblDifficulty.getSelectedObject();
				//- Paramètres avancés
				if(roller.unrolled()) {
					// - - Nombre de manches
					parameters.max_round=nbRoundNumber.getValue();
					// - - Taille maximum d'une colonne
					parameters.max_column_size=nbColumnSize.getValue();
					// - - Taille de la main
					parameters.round.hand_size=nbHandSize.getValue();
					// - - Nombre de colonne
					parameters.nb_column=parameters.nb_team*nbColonneNum.getValue();
					// - - Pioche des cartes objectif
					parameters.objectives=(CardPile<ObjectiveCard>)rblObjectif.getSelectedObject();
					// - Pioche des cartes influences
					parameters.base_deck=(CardPile<InfluenceCard>)rblInfluence.getSelectedObject();
				}
				
				//On lance la partie
				App.createPartie(parameters,bot_factory, nb_bot);
				App.ctrlView().goTo(Ecrans.ENPARTIE);
				SoundManager.playSound("effect","debutPartie", "longeffect", 1);
			});
			bas.getChildren().add(bLancer);
	}
	

}

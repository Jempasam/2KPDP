package interfaceIDJR.partie;

import java.util.function.Consumer;

import engine.events.EventRegistry;
import engine.game.GameControler;
import engine.game.KPDPControler;
import engine.influence.viewer.InfluenceCardViewer;
import engine.kpdpevents.InfluenceEffectEvent;
import engine.kpdpevents.gameevent.EndGameEvent;
import engine.kpdpevents.gameevent.GameEvent;
import engine.kpdpevents.gameevent.NextRoundEvent;
import engine.kpdpevents.gameevent.PlayCardEvent;
import engine.teams.Team;
import engine.teams.TeamViewer;
import engine.util.ViewerKeyMap;
import interfaceCommon.cd.View;
import interfaceCommon.component.ComponentUtil;
import interfaceCommon.component.LoggerComponent;
import interfaceCommon.component.SmallLabel;
import interfaceCommon.image.ImageManager;
import interfaceCommon.lang.LanguageManager;
import interfaceCommon.sound.SoundManager;
import interfaceIDJR.app.App;
import interfaceIDJR.cd.ControleurDonnees.Ecrans;
import interfaceIDJR.component.FieldComponent;
import interfaceIDJR.component.ScorePanel;
import interfaceIDJR.component.kpdp.TableInfluenceCardComponent;
import interfaceIDJR.thread.GameThread;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;

public class InterfaceJoueur extends View {
		private Consumer<String> logger;
		private GameControler game=null;
		private FieldComponent field;
		private StackPane center;
		private Label center_text;
		private LoggerComponent loggercomp;
		private FlowPane hand;
		private GameThread game_thread;
		private EventRegistry<GameEvent> game_event_registry;
		private EventRegistry<InfluenceEffectEvent> card_effect_registry;
		private Background leftPannelBackground=new Background(new BackgroundFill(new Color(0.6, 0.6, 0.6, 1), CornerRadii.EMPTY, Insets.EMPTY));
		
		public InterfaceJoueur() {
			this.setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", "bg"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(ComponentUtil.getBaseUnit()*30, ComponentUtil.getBaseUnit()*30, false, false, false, false))));
			Rectangle2D screenBounds = Screen.getPrimary().getBounds();
			
			VBox rightPannel=new VBox();

			
			//On crée le registres des events de jeu
			game_event_registry=new EventRegistry<GameEvent>();
			card_effect_registry=new EventRegistry<InfluenceEffectEvent>();
			int speed=200;
			
			//Ajout de l'écran de centre
			center=new StackPane();
			center_text=SmallLabel.createTitle("");
			center_text.setVisible(false);
			center_text.setOpacity(0.5);
			center_text.setPickOnBounds(false);
			center.getChildren().addAll(new Region(), center_text);
			setCenter(center);
			
			//Main
			hand=new FlowPane();
			hand.setPickOnBounds(false);
			hand.setAlignment(Pos.BOTTOM_CENTER);
			hand.setOpacity(0.5);
			hand.setVisible(false);
			setFocusTraversable(true);
			center.getChildren().add(hand);
			
			//Ajout de la descriptions des événements
			loggercomp=new LoggerComponent(35);
			loggercomp.setVisible(false);
			loggercomp.setBackground(new Background(new BackgroundFill(new Color(0.8, 0.8, 0.8, 0.5),CornerRadii.EMPTY,Insets.EMPTY)));
			center.getChildren().add(loggercomp);
			logger=(s)->loggercomp.log(s);
			
			//Ajout du listener pour les événements de jeu
			game_event_registry.register((e)->{
				String message=e.toString();
				
				Platform.runLater(new Runnable() {
	                @Override public void run() {
	                	reloadTerrain();
	                	reloadHand();
	                	logger.accept(message);
	                }
				});
				
				if(e instanceof NextRoundEvent||e instanceof EndGameEvent) {
					try {
						SoundManager.playSound("effect","cleanTerrain", "longeffect", 1);
						Thread.sleep(speed*10);
					} catch (InterruptedException ee) { }
					Platform.runLater(new Runnable() {
		                @Override public void run() {
		                	reloadScore();
		                }
					});
				}
				
				if(e instanceof EndGameEvent) {
					try {
						Thread.sleep(speed*10);
					} catch (InterruptedException ee) { }
					Platform.runLater(new Runnable() {
		                @Override public void run() {
		                	//App.setScoreGame(game);
		                	//App.ctrlView().goTo(Ecrans.TABSCORE);
		                	//SoundManager.playSound("effect","finPartie", "longeffect", 1);
		                }
					});
				}
				if(e instanceof PlayCardEvent) {
					SoundManager.playSound("effect","card", "effect", 1);
					try {
						Thread.sleep(speed);
					} catch (InterruptedException ee) { }
				}
				
				System.out.println(e.toString());
				try {
					Thread.sleep(speed);
				} catch (InterruptedException ee) { }
			});
			
			//Ajout du litener pour les effets de carte
			card_effect_registry.register((e)->{
				String message=e.toString();
				
				Platform.runLater(new Runnable() {
	                @Override public void run() {
	                	reloadTerrain();
	                	displayHand(new TeamViewer(e.getContext().getTeam()));
	                	logger.accept(message);
	                }
				});
				try {
					Thread.sleep(speed*1);
				} catch (InterruptedException ee) { }
				SoundManager.playSound("influence", KPDPControler.CARDTYPE_REGISTRY.getId(e.getType()), "effect", 1);
				try {
					Thread.sleep(speed*4);
				} catch (InterruptedException ee) { }
			});
			
		}
		
		public void reloadScore() {
			ViewerKeyMap<Team, TeamViewer, Float> scores=game.calculateGainScores();
			//Left pannel
			VBox leftPannel=new VBox();
			leftPannel.setOnMouseClicked((e)->loggercomp.setVisible(!loggercomp.isVisible()));
			leftPannel.setBackground(leftPannelBackground);
				//Info pannel
				VBox info_pannel=new VBox();
				leftPannel.getChildren().add(info_pannel);
				//Score pannel
				ScorePanel score_pannel=new ScorePanel(game);
				leftPannel.getChildren().add(score_pannel);
			
			//MANCHE
			HBox manche_box=new HBox();
			Label manche_value=new SmallLabel(String.format("%02d",game.getRound()+1),2);
			HBox.setMargin(manche_value, new Insets(10, 10, 10, 10));
			Label manche=new SmallLabel(LanguageManager.get("partie.manche"),2);
			HBox.setMargin(manche, new Insets(10, 10, 10, 10));
			manche_box.getChildren().addAll(manche,manche_value);
			info_pannel.getChildren().add(manche_box);
			
			//TOUR
			HBox tour_box=new HBox();
			Label tour_value=new SmallLabel(String.format("%02d",game.getTotalTurns()+1),1.5f);
			HBox.setMargin(tour_value, new Insets(10, 10, 10, 10));
			Label tour=new SmallLabel(LanguageManager.get("partie.tour"),1.5f);
			HBox.setMargin(tour, new Insets(10, 10, 10, 10));
			tour_box.getChildren().addAll(tour,tour_value);
			info_pannel.getChildren().add(tour_box);
		
			setLeft(leftPannel);
		}
		
		public void reloadHand() {
			displayHand(game.getActualTeam());
		}
		
		public void displayHand(TeamViewer team) {
			hand.getChildren().clear();
			int num=0;
			for(InfluenceCardViewer ic : team.getHand()) {
				VBox card=new VBox();
				card.setAlignment(Pos.CENTER);
				card.getChildren().add(new SmallLabel(Integer.toString(num),1f));
				card.getChildren().add(new TableInfluenceCardComponent(ic, game));
				hand.getChildren().add(card);
				num++;
			}
		}
		public FlowPane getHand() {
			return hand;
		}
		public FieldComponent getField() {
			return field;
		}
		
		public void reloadTerrain() {
			if(field!=null) {
				field.update();
			}else {
				field=new FieldComponent(game);
				center.getChildren().set(0,field);
			}
		}
		
		public void setCenterText(String text) {
			Rectangle2D screenBounds = Screen.getPrimary().getBounds();
			if(text.length()>0)center_text.setFont(Font.font("Vivaldi", FontWeight.BOLD, screenBounds.getWidth()/(text.length()*1.2)));
			center_text.setText(text);
			if(text.isEmpty())center_text.setVisible(false);
			else center_text.setVisible(true);
		}

		public GameControler getGame() {
			return game;
		}
		
		public void setGame(GameControler game) {
			if(game_thread!=null)game_thread.askToStop();
			this.game = game;
			field=null;
			game.setGameEventRegistry(game_event_registry);
			game.setInfluenceEffectEventRegistry(card_effect_registry);
			reloadTerrain();
			reloadScore();
			reloadHand();
			game_thread=new GameThread(game);
			game_thread.start();
		}
}

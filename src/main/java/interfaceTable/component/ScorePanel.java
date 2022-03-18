package interfaceTable.component;

import engine.game.GameViewer;
import engine.teams.Team;
import engine.teams.TeamViewer;
import engine.util.ViewerKeyMap;
import interfaceCommon.JFXUtils.ColorHelper;
import interfaceCommon.component.SmallLabel;
import interfaceCommon.image.ImageManager;
import interfaceCommon.lang.LanguageManager;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ScorePanel extends VBox{

	private GameViewer game;
	
	public ScorePanel(GameViewer g) {
		game=g;
		
		update();
	}
	public void update() {
		ViewerKeyMap<Team, TeamViewer, Float> scores=game.calculateGainScores();
		
		int i=0;
		for(TeamViewer team : game.getTeams()) {
			HBox score=new HBox();
			Label score_value=new SmallLabel(String.format("%03d",scores.get(team).intValue()),2);
			HBox.setMargin(score_value, new Insets(10, 10, 10, 10));
			Label score_name=new SmallLabel(LanguageManager.get("partie.joueur")+" "+Integer.toString(i),"Vivaldi",1.9f);
			score.setBackground(new Background(new BackgroundImage(ImageManager.getImage("misc", "plank2"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, true,  true, false, false))));
			HBox.setMargin(score_name, new Insets(10, 10, 10, 10));
			
			score.getChildren().addAll(score_name,score_value);
			score.setEffect(ColorHelper.whiteAdjustToColor(ColorHelper.colorOfTeam(game.idOfTeam(team))));
			
			getChildren().add(score);
			
			i++;
		}
	}
	
}

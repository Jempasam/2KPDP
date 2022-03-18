package interfaceTable.controler;

import engine.game.GameViewer;
import engine.teams.TeamViewer;
import engine.teams.controler.TeamControler;
import interfaceCommon.lang.LanguageManager;
import interfaceTable.app.App;
import interfaceTable.component.ColumnComponent;
import interfaceTable.partie.EnPartie;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;

/**
 * Un TeamControler qui utilise la souris.
 * @author Samuel Demont
 *
 */
public class ClickControler implements TeamControler{
	private EnPartie screen;
	private int selected;

	
	public ClickControler(EnPartie screen) {
		super();
		this.screen = screen;
	}
	
	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { }
		
		selected=-1;
		int i=0;
		
		Platform.runLater(()->{
			App.setHandVisible(true);
			App.setPartieText(LanguageManager.get("controler.card."+reason));
			});
		
		//Set mouse detection
		for(Node n : screen.getHand().getChildren()) {
			int toset=i;
			n.setEffect(new ColorAdjust(0,0,0.1,0));
			n.setOnMouseReleased((e)->selected=toset);
			i++;
		}
		
		while(selected==-1) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { }
		}
		
		//Remove mouse detection
		for(Node n : screen.getHand().getChildren()) {
			n.setOnMouseClicked(null);
			n.setEffect(null);
		}
		
		Platform.runLater(()->{
			App.setHandVisible(false);
			App.setPartieText("");
		});
		
		return selected;
	}
	
	@Override
	public int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter) {
		selected=-1;
		int i=0;
		
		Platform.runLater(()->{
			App.setHandVisible(true);
			App.setPartieText(LanguageManager.get("controler.column."+reason));
		});
		
		//Set mouse detection
		for(ColumnComponent n : screen.getField()) {
			int toset=i;
			n.setOnMouseClicked((e)->selected=toset);
			n.setOnMouseDragOver((e)->n.setEffect(new ColorAdjust(0,0,0.1,0)));
			n.setEffect(new ColorAdjust(0,0,0.1,0));
			i++;
		}
		
		while(selected==-1) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { }
		}
		
		//Remove mouse detection
		for(ColumnComponent n : screen.getField()) {
			n.setOnMouseClicked(null);
			n.setEffect(null);
		}
		
		Platform.runLater(()->{
			App.setHandVisible(false);
			App.setPartieText("");
		});
		
		return selected;
	}


	@Override
	public String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter) {
		return "Aucun commentaire";
	}
}

package interfaceTable.controler;

import engine.game.GameViewer;
import engine.teams.TeamViewer;
import engine.teams.controler.TeamControler;
import interfaceTable.app.App;
import interfaceTable.util.KeyListener;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

/**
 * Un TeamControler qui permet de controler un joueur grâce aux touches du clavier
 * @author Samuel Demont
 *
 */
public class KeyboardControler implements TeamControler{
	
	public KeyboardControler() {
	}
	
	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		Platform.runLater(new Runnable() {
			@Override public void run() { 
				App.setPartieText("Joueur "+game.idOfTeam(team)+": Choississez une carte avec NUMPAD0->NUMPAD9 pour "+reason);
				App.setHandVisible(true);
			}
		});
		int ret=KeyListener.waitNewKeyPressBetween(KeyCode.NUMPAD0.getCode(),KeyCode.NUMPAD0.getCode()+team.getHand().size()).getCode()-KeyCode.NUMPAD0.getCode();
		Platform.runLater(new Runnable() {
			@Override public void run() {
				App.setPartieText("");
				App.setHandVisible(false);
			}
		});
		return ret;
	}

	@Override
	public int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter) {
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	App.setPartieText("Joueur "+game.idOfTeam(team)+": Choississez une colonne avec NUMPAD0->NUMPAD9 pour "+reason); 
            	App.setHandVisible(true);
            }
		});
		int ret=KeyListener.waitNewKeyPressBetween(KeyCode.NUMPAD0.getCode(),KeyCode.NUMPAD0.getCode()+game.getField().size()).getCode()-KeyCode.NUMPAD0.getCode();
		Platform.runLater(new Runnable() {
			@Override public void run() {
				App.setPartieText("");
				App.setHandVisible(false);
			}
		});
		return ret;
	}

	@Override
	public String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter) {
		return "Je contrôle au clavier";
	}

}

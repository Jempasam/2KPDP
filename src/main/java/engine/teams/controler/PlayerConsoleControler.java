package engine.teams.controler;

import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import engine.game.GameViewer;
import engine.teams.TeamViewer;
/**
 * Un controleur qui permet de controler le jeu via la console.
 * @author Samuel Demont
 *
 */
public class PlayerConsoleControler implements TeamControler {
	private BiConsumer<GameViewer,TeamViewer> field_drawer;
	private BiConsumer<GameViewer,TeamViewer>hand_drawer;
	private BiConsumer<GameViewer,TeamViewer>player_drawer;
	private Consumer<String> title_drawer;
	
	public PlayerConsoleControler(BiConsumer<GameViewer,TeamViewer> field_drawer, BiConsumer<GameViewer,TeamViewer> hand_drawer, BiConsumer<GameViewer,TeamViewer> player_drawer, Consumer<String> title_drawer) {
		super();
		this.field_drawer = field_drawer;
		this.hand_drawer = hand_drawer;
		this.player_drawer = player_drawer;
		this.title_drawer=title_drawer;
	}

	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		//On affiche le terrain et la main
		title_drawer.accept(reason);
		player_drawer.accept(game, team);
		field_drawer.accept(game, team);
		hand_drawer.accept(game, team);
		
		//On demande le choix du joueur
		Scanner sc = new Scanner(System.in);
		int choice=sc.nextInt();
		sc.nextLine();
		return choice;
	}

	@Override
	public int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter) {
		//On affiche le terrain
		title_drawer.accept(reason);
		field_drawer.accept(game, team);
		
		//On demande le choix du joueur
		Scanner sc = new Scanner(System.in);
		int choice=sc.nextInt();
		sc.nextLine();
		return choice;
	}

	@Override
	public String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter) {
		title_drawer.accept("Commentary for : "+reason);
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
}

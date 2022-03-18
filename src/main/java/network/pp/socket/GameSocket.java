package network.pp.socket;

import java.net.MulticastSocket;
import java.net.ServerSocket;

import engine.game.GameViewer;

public class GameSocket{
	private ServerSocket listener;
	private int id;
	private MulticastSocket announcer;
	
	public GameSocket() {
	}
	
	public void connectGame(GameViewer viewer) {
	}

}

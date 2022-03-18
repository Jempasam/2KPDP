package interfaceIDJR.thread;

import engine.game.GameControler;

public class GameThread extends Thread{
	GameControler game;
	boolean stop=false;
	public GameThread(GameControler game) {
		this.game=game;
		setDaemon(true);
	}
	@Override
	public void run() {
		game.launch();
		if(!game.isFinished()&&!stop) {
			game.nextTurn();
		}
		
	}
	public void askToStop() {
		stop=true;
	}
}

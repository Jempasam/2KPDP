package interfaceTable.thread;

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
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		game.launch();
		if(!game.isFinished()&&!stop) {
			game.nextTurn();
		}
		
	}
	public void askToStop() {
		stop=true;
	}
}

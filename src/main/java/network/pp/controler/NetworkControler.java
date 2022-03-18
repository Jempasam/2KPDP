package network.pp.controler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import engine.game.GameViewer;
import engine.teams.TeamViewer;
import engine.teams.controler.TeamControler;

public class NetworkControler implements TeamControler{
	Socket socket;
	BufferedWriter writer;
	BufferedReader reader;
	public NetworkControler(Socket socket) {
		this.socket=socket;
		try {
			socket.setTcpNoDelay(true);
			writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
			reader=new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	@Override
	public int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter) {
		// TODO Auto-generated method stub
		return null;
	}

}

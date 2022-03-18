package engine.teams.controler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import engine.game.GameViewer;
import engine.teams.TeamViewer;

public class TelnetTeamControler implements TeamControler {

	public static void main(String[] args) {
		try {
			ServerSocket server=new ServerSocket(8954);
			Socket client=server.accept();
			BufferedReader reader=new BufferedReader(new InputStreamReader(client.getInputStream()));
			String line;
			do {
				//clear screen
				BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				writer.append((char)27).append("[2J");
				
				
				
				writer.flush();
				
			}while((line=reader.readLine())!=null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		// TODO Auto-generated method stub
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

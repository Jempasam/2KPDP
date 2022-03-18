package engine.kpdpevents.gameevent;

import engine.game.Game;
import engine.influence.viewer.InfluenceCardViewer;
import engine.teams.Team;
import engine.teams.TeamViewer;

public class PlayCardEvent extends GameEvent {
	
	private int column_num, line_num;
	private TeamViewer player;
	
	public PlayCardEvent(Game game, int column_num, int line_num, Team player) {
		super(game);
		this.column_num = column_num;
		this.line_num = line_num;
		this.player = new TeamViewer(player);
	}
	
	public InfluenceCardViewer getCardPlayed() {
		return game.getField().get(column_num).get(line_num);
	}
	
	public int getColumnNum() {return column_num;}
	
	public int getLineNum() {return column_num;}
	
	public TeamViewer getPlayer() {
		return player;
	}
	@Override
	public String toString() {
		return "Le joueur "+game.idOfTeam(player)+" joue la carte "+getCardPlayed()+" dans la colonne "+column_num+".";
	}
}
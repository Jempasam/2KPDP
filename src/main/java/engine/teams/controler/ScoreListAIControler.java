package engine.teams.controler;

import engine.field.ColumnViewer;
import engine.game.GameViewer;
import engine.influence.viewer.InfluenceCardViewer;
import engine.teams.TeamViewer;

public abstract class ScoreListAIControler extends RandomAIControler{
	private int column_selected;
	
	protected abstract int calculateScoreOf(InfluenceCardViewer ic, ColumnViewer col, TeamViewer player, GameViewer game);
	
	@Override
	public int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter) {
		if(!reason.equals("playCard"))return super.askForColumn(team, game, reason, try_counter);
		return column_selected;
	}
	
	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		if(!reason.equals("playCard"))return super.askForCard(team, game, reason, try_counter);
		
		int[] move=getBestMove(team, game, try_counter);
		if(move!=null) {
			column_selected=move[0];
			return move[1];
		}else {
			column_selected=super.askForColumn(team, game, reason, try_counter);
			return super.askForCard(team, game, reason, try_counter);
		}
	}
	
	public int[] getBestMove(TeamViewer t,GameViewer g, int try_counter) {
		int[] max_scores=new int[g.getField().size()];
		int[][] best_plays=new int[g.getField().size()][2];
		
		for(int i=0; i<max_scores.length; i++) max_scores[i]=0;
		for(int i=0; i<best_plays.length; i++) best_plays[i]=null;
		
		for(int x=0; x<g.getField().size(); x++)
		for(int h=0; h<t.getHand().size(); h++){
			ColumnViewer col=g.getField().get(x);
			InfluenceCardViewer card=t.getHand().get(h);
			
			int score=calculateScoreOf(card,col,t,g);
			
			for(int i=0; i<max_scores.length; i++) {
				if(score>max_scores[i]) {
					max_scores[i]=score;
					best_plays[i]=new int[]{x,h};
					break;
				}else if(score==max_scores[i]){
					if(Math.random()>0.5)best_plays[i]=new int[]{x,h};
					break;
				}
			}
		}
		
		if(try_counter<g.getField().size())return best_plays[try_counter];
		else return null;
	}
}

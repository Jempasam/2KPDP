package engine.teams.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import engine.field.ColumnViewer;
import engine.game.GameViewer;
import engine.influence.viewer.InfluenceCardViewer;
import engine.teams.TeamViewer;

public abstract class PredicateListAIControler extends RandomAIControler{
	int column_selected;
	
	protected abstract void fillPredicates(List<BiPredicate<InfluenceCardViewer, ColumnViewer>> predicates);
	
	@Override
	public int askForCard(TeamViewer team, GameViewer game, String reason, int try_counter) {
		if(!reason.equals("playCard"))return super.askForCard(team, game, reason, try_counter);
		
		List<BiPredicate<InfluenceCardViewer, ColumnViewer>> predicates=new ArrayList<>();
		fillPredicates(predicates);
		
		int[] ret;
		for(BiPredicate<InfluenceCardViewer, ColumnViewer> pred : predicates) {
			if((ret=findColumnFor(team, game, pred))!=null){
				column_selected=ret[0];
				return ret[1];
			}
		}
		
		column_selected=super.askForColumn(team, game, reason, try_counter);
		return super.askForCard(team, game, reason, try_counter);
	}
	@Override
	public int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter) {
		if(!reason.equals("playCard"))return super.askForColumn(team, game, reason, try_counter);
		return column_selected;
	}
	public int[] findColumnFor(TeamViewer t,GameViewer g,BiPredicate<InfluenceCardViewer, ColumnViewer> pred) {
		for(int x=0; x<g.getField().size(); x++)
		for(int h=0; h<t.getHand().size(); h++){
			ColumnViewer col=g.getField().get(x);
			InfluenceCardViewer card=t.getHand().get(h);
			if(pred.test(card, col))return new int[]{x,h};
		}
		return null;
	}
}

package engine.calculator.column;

import java.util.HashMap;
import java.util.Map;

import engine.field.Column;
import engine.influence.InfluenceCard;
import engine.teams.Team;

/**
 * Un calculateur de colonne qui attribue à chaque joueur un score correspondant au points de la carte qui a le plus de points.
 * @author Samuel Demont
 *
 */
public class MaxedColumnCalculator implements IColumnCalculator {
	public MaxedColumnCalculator() {
	}
	@Override
	public Map<Team, Float> calculate(Column column) {
		Map<Team, Float> scores=new HashMap<>();
		for(InfluenceCard card : column)
		if(!card.isIgnored()) {
			float value=0;
			
			if(scores.containsKey(card.getTeam()))value=scores.get(card.getTeam());
			
			if(card.getPoints()>value)
				scores.put(card.getTeam(), card.getPoints());
			else
				scores.put(card.getTeam(), value);
		}
		return scores;
	}
}

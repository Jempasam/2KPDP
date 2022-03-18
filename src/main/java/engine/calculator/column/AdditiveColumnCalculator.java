package engine.calculator.column;

import java.util.HashMap;
import java.util.Map;

import engine.field.Column;
import engine.influence.InfluenceCard;
import engine.teams.Team;

/**
 * Un calculateur de colonne qui attribue à chaque joueur la somme des points de ses cartes présentes dans la colonne.
 * Il correspond au règles du jeu de base.
 * @author Samuel Demont
 *
 */
public class AdditiveColumnCalculator implements IColumnCalculator {
	public AdditiveColumnCalculator() {
	}
	@Override
	public Map<Team, Float> calculate(Column column) {
		Map<Team, Float> scores=new HashMap<>();
		for(InfluenceCard card : column)
		if(!card.isIgnored()) {
			float value=0;
			if(scores.containsKey(card.getTeam()))value=scores.get(card.getTeam());
			value+=card.getPoints();
			scores.put(card.getTeam(), value);
		}
		return scores;
	}
}

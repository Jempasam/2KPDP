package engine.calculator.fnal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import engine.card.container.CardPile;
import engine.objective.ObjectiveCard;
import engine.teams.Team;

/**
 * Permet de calculer les points d'un joueur à partir de ses gains
 * @author Samuel Demont
 *
 */
public interface IGainCalculator {
	float calculate(CardPile<ObjectiveCard> gains);
	public default float calculate(Team team) {
		return calculate(team.getGains());
	}
	public default Map<Team,Float> calculate(Collection<Team> teams){
		Map<Team,Float> total=new HashMap<>();
		for(Team t : teams) total.put(t, Float.valueOf(calculate(t)) );
		return total;
	}
}

package engine.calculator.winner;

import java.util.Map;

import engine.teams.Team;

/**
 * Le calculateur qui décide du vainqueur d'une partie à partir des scores des joueurs
 * @author Samuel Demont
 *
 */
public interface IWinnerSelector {
	Team calculate(Map<Team,Float> scores);
}

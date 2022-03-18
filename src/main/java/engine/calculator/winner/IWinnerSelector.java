package engine.calculator.winner;

import java.util.Map;

import engine.teams.Team;

/**
 * Le calculateur qui d�cide du vainqueur d'une partie � partir des scores des joueurs
 * @author Samuel Demont
 *
 */
public interface IWinnerSelector {
	Team calculate(Map<Team,Float> scores);
}

package engine.calculator;

import java.util.Collection;

import engine.calculator.column.IColumnCalculator;
import engine.calculator.fnal.IGainCalculator;
import engine.calculator.winner.IWinnerSelector;
import engine.field.Column;
import engine.teams.Team;

/**
 * Un arbitre contient les calcuolateurs qui décide calculent les scores des joueurs et décident du vainqueur.
 * @author Samuel Demont
 *
 */
public class Referee {
	/**Calculateur qui détermine le score de chaque joueur dans une colonne*/
	public IColumnCalculator columncalculator;
	
	/**Calculateur qui détermine le score final de chaque joueur à partir de ses gains*/
	public IGainCalculator gaincalculator;
	
	/**Calculateur qui détermine le joueur qui gagne la partie à partir de ses gains*/
	public IWinnerSelector finalwinnercalculator;
	
	/**Calculateur qui détermine le joueur qui gagne la colonne à partir de son score*/
	public IWinnerSelector columnwinnercalculator;
		
	
	public Referee(IColumnCalculator columncalculator, IGainCalculator gaincalculator,IWinnerSelector fwinnercalculator, IWinnerSelector cwinnercalculator) {
		this.columncalculator = columncalculator;
		this.gaincalculator = gaincalculator;
		this.finalwinnercalculator = fwinnercalculator;
		this.columnwinnercalculator = cwinnercalculator;
	}

	public Referee(Referee copied) {
		this.columncalculator = copied.columncalculator;
		this.gaincalculator = copied.gaincalculator;
		this.finalwinnercalculator = copied.finalwinnercalculator;
		this.columnwinnercalculator = copied.columnwinnercalculator;
	}
	
	public Team getColumnWinner(Column column) {
		return columnwinnercalculator.calculate(columncalculator.calculate(column));
	}
	public Team getGameWinner(Collection<Team> teams) {
		return finalwinnercalculator.calculate( gaincalculator.calculate(teams) );
	}
	
}

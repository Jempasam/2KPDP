package engine.calculator;

import engine.calculator.column.AdditiveColumnCalculator;
import engine.calculator.column.IColumnCalculator;
import engine.calculator.column.MaxedColumnCalculator;
import engine.calculator.fnal.BaseGainCalculator;
import engine.calculator.fnal.DomainGainCalculator;
import engine.calculator.fnal.IGainCalculator;
import engine.calculator.winner.ComparatorWinnerSelector;
import engine.calculator.winner.IWinnerSelector;

/**
 * Contient des instances statiques de calculateurs et arbitres
 * @author Samuel Demont
 *
 */
public class Calculators {
	
	/**
	 * Le calculateur de gain du jeu de base
	 */
	public static IGainCalculator GAIN_BASE=new BaseGainCalculator(6,2,-1);
	
	/**
	 * Le calculateur de gain du jeu de base mais les cartes en trop ne retirent pas des points mais en donnent.
	 */
	public static IGainCalculator GRAIN_RESTRICTIVE=new BaseGainCalculator(6,2,1);
	
	/**
	 * Si le joueur a 5 domaines différents, alors on multiplie la somme de toutes ses cartes par 2
	 */
	public static IGainCalculator GAIN_POSITIVE=new BaseGainCalculator(6,2,2);
	
	/**
	 * Uniquement les cartes des deux domaines qui donnent le plus de points au joueur sont comptabilisés.
	 */
	public static IGainCalculator GAIN_DOMAIN=new DomainGainCalculator(2,1);
	
	
	public static IColumnCalculator COLUMN_ADDITIVE=new AdditiveColumnCalculator();
	public static IColumnCalculator COLUMN_MAXED=new MaxedColumnCalculator();
	
	public static IWinnerSelector WINNER_BIGGER=new ComparatorWinnerSelector((Float a, Float b)->{if(a>b)return 1; else if(a==b)return 0; else return -1;});
	public static IWinnerSelector WINNER_SMALLER=new ComparatorWinnerSelector((Float a, Float b)->{if(a>b)return -1; else if(a==b)return 0; else return 1;});
	
	public static Referee REFEREE_BASE=new Referee(COLUMN_ADDITIVE, GAIN_BASE, WINNER_BIGGER, WINNER_BIGGER);
	
}

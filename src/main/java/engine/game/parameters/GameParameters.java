package engine.game.parameters;

import engine.card.CardContainers;
import engine.card.container.CardPile;
import engine.card.shuffler.CardShufflers;
import engine.card.shuffler.ICardShuffler;
import engine.influence.InfluenceCard;
import engine.objective.ObjectiveCard;

/**
 * Contient toutes les informations d'une partie, les paramètres non-renseignés sont complétés automatiquements selon les autres.
 * ( Par exemple, par défaut le nombre de colonne est égal au nombre de joueur. )
 * @author Samuel Demont
 *
 */
public class GameParameters {
	/**
	 * Les paramètres d'une manche
	 */
	public RoundParameters round;
	/**
	 * Le nombre d'équipe/joueur dans la partie
	 * Par défaut=6
	 */
    public Integer nb_team;
    
    /**
     * Le nombre de colonne sur le terrain
     * Par défaut=nb_team
     */
    public Integer nb_column;
    
    /**
     * Le nombre de manche maximal dans la partie. Une fois atteint, la partie se termine même si il reste des cartes objectifs dans la colonne.
     * Par défaut=100
     */
    public Integer max_round;
    
    /**
     * La pioche de carte influence qui sera donnée à chaque joueur.
     * Par défaut=La pioche par défaut avec toutes les cartes en 1 exemplaire.
     */
    public CardPile<InfluenceCard> base_deck;
    
    /**
     * La pioche de carte objectif.
     * Par défaut=La pioche par défaut avec pour chaque domaine de base les cartes de 1 à 5 points.
     */
    public CardPile<ObjectiveCard> objectives;
    
    /**
     * Le mélangeur de carte qui mélangera la pioche de chaque joueur en début de partie.
     * Par défaut=Un mélangeur aléatoire simple
     */
    public ICardShuffler stockpile_shuffler;
    
    /**
     * Le mélangeur de carte qui mélangera la pioche de carte objectif.
     * Par défaut=Un mélangeur aléatoire simple
     */
    public ICardShuffler objective_shuffler;
    
    /**
     * Le nombre de carte maximal dans une colonne.
     * Par défaut=50
     */
    public Integer max_column_size;
    
    public GameParameters() {
    	round=new RoundParameters();
	}
    
    /**
     * Remplie les valeurs non-remplies avec leur valeur par défaut.
     */
    public void fill() {
    	round.fill();
    	if(nb_team==null)nb_team=6;
    	if(nb_column==null)nb_column=nb_team;
    	if(max_round==null)max_round=6;
    	if(max_column_size==null)max_column_size=50;
    	
    	if(base_deck==null)base_deck=CardContainers.DEFAULT_STOCKPILE;
    	if(objectives==null)objectives=CardContainers.DEFAULT_OBJECTIVES;
    	
    	if(stockpile_shuffler==null)stockpile_shuffler=CardShufflers.FAST;
    	if(objective_shuffler==null)objective_shuffler=CardShufflers.FAST;
    }
}

package engine.game.parameters;

import engine.card.CardContainers;
import engine.card.container.CardPile;
import engine.card.shuffler.CardShufflers;
import engine.card.shuffler.ICardShuffler;
import engine.influence.InfluenceCard;
import engine.objective.ObjectiveCard;

/**
 * Contient toutes les informations d'une partie, les param�tres non-renseign�s sont compl�t�s automatiquements selon les autres.
 * ( Par exemple, par d�faut le nombre de colonne est �gal au nombre de joueur. )
 * @author Samuel Demont
 *
 */
public class GameParameters {
	/**
	 * Les param�tres d'une manche
	 */
	public RoundParameters round;
	/**
	 * Le nombre d'�quipe/joueur dans la partie
	 * Par d�faut=6
	 */
    public Integer nb_team;
    
    /**
     * Le nombre de colonne sur le terrain
     * Par d�faut=nb_team
     */
    public Integer nb_column;
    
    /**
     * Le nombre de manche maximal dans la partie. Une fois atteint, la partie se termine m�me si il reste des cartes objectifs dans la colonne.
     * Par d�faut=100
     */
    public Integer max_round;
    
    /**
     * La pioche de carte influence qui sera donn�e � chaque joueur.
     * Par d�faut=La pioche par d�faut avec toutes les cartes en 1 exemplaire.
     */
    public CardPile<InfluenceCard> base_deck;
    
    /**
     * La pioche de carte objectif.
     * Par d�faut=La pioche par d�faut avec pour chaque domaine de base les cartes de 1 � 5 points.
     */
    public CardPile<ObjectiveCard> objectives;
    
    /**
     * Le m�langeur de carte qui m�langera la pioche de chaque joueur en d�but de partie.
     * Par d�faut=Un m�langeur al�atoire simple
     */
    public ICardShuffler stockpile_shuffler;
    
    /**
     * Le m�langeur de carte qui m�langera la pioche de carte objectif.
     * Par d�faut=Un m�langeur al�atoire simple
     */
    public ICardShuffler objective_shuffler;
    
    /**
     * Le nombre de carte maximal dans une colonne.
     * Par d�faut=50
     */
    public Integer max_column_size;
    
    public GameParameters() {
    	round=new RoundParameters();
	}
    
    /**
     * Remplie les valeurs non-remplies avec leur valeur par d�faut.
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

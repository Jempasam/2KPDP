package engine.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import engine.calculator.Referee;
import engine.card.container.CardPile;
import engine.card.container.Hand;
import engine.events.EventRegistry;
import engine.field.Column;
import engine.field.Field;
import engine.game.parameters.GameParameters;
import engine.game.parameters.ResolutionParameters;
import engine.game.parameters.RoundParameters;
import engine.influence.EffectContext;
import engine.influence.InfluenceCard;
import engine.kpdpevents.InfluenceEffectEvent;
import engine.kpdpevents.controlerevent.AskCardEvent;
import engine.kpdpevents.controlerevent.AskColumnEvent;
import engine.kpdpevents.controlerevent.ControlerEvent;
import engine.kpdpevents.gameevent.CardSelectedEvent;
import engine.kpdpevents.gameevent.ColumnSelectedEvent;
import engine.kpdpevents.gameevent.EndGameEvent;
import engine.kpdpevents.gameevent.EndPhaseEvent;
import engine.kpdpevents.gameevent.GameEvent;
import engine.kpdpevents.gameevent.GiveGainEvent;
import engine.kpdpevents.gameevent.NextRoundEvent;
import engine.kpdpevents.gameevent.NextTurnEvent;
import engine.kpdpevents.gameevent.PlayCardEvent;
import engine.objective.Domain;
import engine.objective.ObjectiveCard;
import engine.teams.Team;
import engine.teams.TeamViewer;
import engine.teams.controler.TeamControler;

/**
 * 
 * @author Samuel Demont
 *
 */
public class Game {
	
	/**
     * Le registre des événments pour les effets de carte influence.
     */
    public static EventRegistry<InfluenceEffectEvent> INFLUENCE_EVENTS;
    
    /**
	 * Le registre des événements de jeu divers
	 */
	public static EventRegistry<GameEvent> GAME_EVENTS;
	 
	 /**
	  * Le registre qui permet de récupérer les cartes et colonnes à jouer
	  */
	public static EventRegistry<ControlerEvent> CONTROLER_EVENTS;
     
	private GameParameters game_parameters;
	private RoundParameters round_parameters;
	private ResolutionParameters resolution_parameters;
	
	private CardPile<ObjectiveCard> objectives;
    private int round, turn, total_turns;
    private Field field;
    private List<Team> teams;
    private boolean finished;
    private int max_controller_try=20;
    private boolean autoplay=true;
    
    public Game(GameParameters parameters) {
    	parameters.fill();
    	this.game_parameters=parameters;
		round=0; total_turns=0;
		finished=false;
		
		//Prepare la pile de carte objectif
		objectives=new CardPile<>(parameters.objectives);
		parameters.objective_shuffler.shuffle(objectives);
		
		//Prepare la première manche
		prepareRound();
		
		//Prepare les équipes
		teams=new ArrayList<>();
		for(int i=0; i<parameters.nb_team; i++)
			teams.add(Team.teamOfStockpile(parameters.base_deck, parameters.round.hand_size, parameters.stockpile_shuffler));
	}
    
    void setAutoPlay(boolean autoplay) {
    	this.autoplay=autoplay;
    }
    
    /**
     * Prépare une nouvelle manche
     */
    private void prepareRound() {
    	//Prepare le terrain
    	rebuildField();
    	//Reinitialise les paramètre de manche
    	round_parameters=new RoundParameters(game_parameters.round);
    	//Reinitialise les tours
    	turn=0; 
    }
    
    public CardPile<ObjectiveCard> getGainsPile() { return objectives; }

	public void setGainsPile(CardPile<ObjectiveCard> gains_pile) { this.objectives = gains_pile; }

	public int getRound() { return this.round; }
    public void setRound(int round) { this.round = round; }
    
    public int getTurn() { return this.turn; }
    public void setTurn(int turn) {this.turn = turn;}
    
    public int getTotalTurns() {return total_turns;}
	public void setTotalTurns(int total_turns) {this.total_turns = total_turns;}
    
    public List<Team> getTeams() { return this.teams; }
    
    public Field getField() { return this.field; }
    public void setField(Field field) { this.field = field; }
    
    public Map<Team,Float> calculateColumnScores(Column col){    	
    	//Récupère la position de la colonne
    	int x=field.indexOf(col);
    	if(x==-1)return null;
    	
    	//Utilise les effets de fin de partie des cartes
		// - Tant que tout les effets ne sont pas activés
		boolean one_effect_remaining=true;
		for(int priority_level=0; one_effect_remaining ; priority_level++) {
			one_effect_remaining=false;
			// - Parcours les cartes de la colonne
			for(int y=0; y<col.size(); y++){
				InfluenceCard card=col.get(y);
				//On utilise l'effet
				if(card.canUseEndEffect()) {
					if( card.getType().getEffectPriority()<=priority_level ) {
						EffectContext context=new EffectContext(this, x, y);
						card.tryEndEffect(context);
					}
					else one_effect_remaining=true;
				}
			}
		}
		
		return resolution_parameters.referee.columncalculator.calculate(col);
    }
    public Team calculateColumnWinner(Column col){
    	resolution_parameters=new ResolutionParameters(round_parameters.resolution);
    	return resolution_parameters.referee.columnwinnercalculator.calculate(calculateColumnScores(col));
    }
    
    public boolean nextRound() {
    	int old_round=round;
    	
    	handleGameEvent(new EndPhaseEvent(this));
    	
    	//On décide du gagnant des colonnes et on leur donne leurs gains
    	int c_num=0;
    	for(Column col : field) {
    		Team t=calculateColumnWinner(col);
    		
    		if(t!=null)t.getGains().add(col.getObjectiveCard());
    		
    		handleGameEvent(new GiveGainEvent(this, c_num, col.getObjectiveCard(), t));
    		
    		c_num++;
    	}
    	
    	//On révèle toutes les cartes
    	field.forEachInfluenceCards(InfluenceCard::reveal);
    	
    	//Avance dans la partie
    	round++;
    	boolean is_finished=isFinished();
    	if(!is_finished){
    		//Reinitialise la prochaine manche
    		prepareRound();
    		
    		//Fait piocher les joueurs
    		for(Team t : teams)t.drawUntil(round_parameters.hand_size);
        	
        	handleGameEvent(new NextRoundEvent(old_round,round,this));
        	
        	//Joue le premier tour
   		    if(takeDecision())nextTurn();
    	}
    	return is_finished;
     }
    public void  rebuildField() {
    	if(field!=null)field.discardAll();
    	field=Field.fieldOfPile(objectives, game_parameters.nb_column, game_parameters.max_column_size);
    }
	 public boolean nextTurn() {
		 int oldturn=turn;
		 
		 //Pioche les cartes
		 teams.get(turn).drawUntil(round_parameters.hand_size);
		 
		 //Reactive les effets de carte
		 field.reactiveEffects();
		 
		 //Prochain tour
		 turn++;
		 total_turns++;
		 if(turn>=teams.size()) turn=0;
		 
		 if(isRoundFinished()) return nextRound();
		 else handleGameEvent(new NextTurnEvent(oldturn,turn,this));
		 
		 //Joue automatiquement ce tour.
		 if(getActualHand().size()==0) {
			 boolean one_can_play=false;
			 for(Team t : teams) if(t.getHand().size()>0)one_can_play=true;
			 
			 if(one_can_play)nextTurn();
			 else{
				 nextRound();
			 }
		 }
		 else if(autoplay && takeDecision())nextTurn();
		 
		 return true;
	 }
	 
	 /**
	  * Joue automatiquement le tour
	  * @return true si le tour a été joué automatiquement sinon false
	  */
	 public boolean takeDecision() {
		 GameViewer gv=new GameViewer(this);
		 TeamViewer tv=new TeamViewer(getActualTeam());
		 
		 for(int try_counter=0; try_counter<max_controller_try; try_counter++) {
			 Integer card_num=askCardTo(getActualTeam(), "playCard", try_counter);
			 Integer col_num=askColumnTo(getActualTeam(), "playCard", try_counter);
			 if(card_num!=null && col_num!=null &&
				 0<=card_num && card_num< getActualTeam().getHand().size() &&
				 0<=col_num && col_num< field.size() &&
				playCard(getActualHand().get(card_num), field.get(col_num))) return true;
		 }
		 return false;
	 }
	 
	 /**
	  * Demande à un joueur de choisir une carte
	  * @param team Le joueur à qui demander
	  * @param reason La raison de la demande
	  * @param try_counter Le nombre de fois que la demande a été faites
	  * @return La position de la carte dans la main du joueur ; null si aucune réponse n'a pu être donnée
	  */
	 public Integer askCardTo(Team team, String reason, int try_counter) {
		 GameViewer gv=new GameViewer(this);
		 TeamViewer tv=new TeamViewer(team);
		 
		 //On essaye de trouver un coup à jouer avec le controleur du joueur
		 TeamControler controler=team.getControler();
		 if(controler!=null) {
			 Integer response=controler.askForCard(tv,gv, reason, try_counter);
			 if(response!=null) handleGameEvent(new CardSelectedEvent(this, team, response.intValue(), try_counter, reason));
			 return response;
		 }
		 
		 //Ask card
		 AskCardEvent card_event=new AskCardEvent(this, reason, team, try_counter);
		 handleControlerEvent(card_event);
		 return card_event.getCardToUse();
	 }
	 
	 /**
	  * Demande à un joueur de choisir une carte avec des conditions
	  * @param team Le joueur à qui demander
	  * @param reason La raison de la demande
	  * @param condition La condition
	  * @return La position de la carte dans la main du joueur ; null si aucune réponse n'a pu être donnée
	  */
	 public Integer askCardTo(Team team, String reason, IntPredicate condition) {
		 for(int try_counter=0; try_counter<max_controller_try; try_counter++) {
			 Integer card=askCardTo(team, reason, try_counter);
			 if(card != null && condition.test(card))return card;
		 }
		 return null;
	 }
	 
	 /**
	  * Demande à un joueur de choisir une colonne
	  * @param team Le joueur à qui demander
	  * @param reason La raison de la demande
	  * @param try_counter Le nombre de fois que la demande a été faites
	  * @return La position de la colonne dans la terrain ; null si aucune réponse n'a pu être donnée
	  */
	 public Integer askColumnTo(Team team, String reason, int try_counter) {
		 GameViewer gv=new GameViewer(this);
		 TeamViewer tv=new TeamViewer(team);
		 
		 //On essaye de trouver un coup à jouer avec le controleur du joueur
		 TeamControler controler=team.getControler();
		 if(controler!=null) {
			 Integer response=controler.askForColumn(tv,gv, reason, try_counter);
			 if(response!=null) handleGameEvent(new ColumnSelectedEvent(this, team, response.intValue(), try_counter, reason));
			 return response;
		 }
		 
		 //Ask card
		 AskColumnEvent card_event=new AskColumnEvent(this, reason, team, try_counter);
		 handleControlerEvent(card_event);
		 return card_event.getColumnToUse();
	 }
	 
	 /**
	  * Demande à un joueur de choisir une colonne avec une condition
	  * @param team Le joueur à qui demander
	  * @param reason La raison de la demande
	  * @param condition La condition
	  * @return La position de la colonne dans la terrain ; null si aucune réponse n'a pu être donnée
	  */
	 public Integer askColumnTo(Team team, String reason, IntPredicate condition) {
		 for(int try_counter=0; try_counter<max_controller_try; try_counter++) {
			 Integer column=askCardTo(team, reason, try_counter);
			 if(column != null && condition.test(column))return column;
		 }
		 return null;
	 }
	 
	 public boolean isFinished() {
		 if(finished) return true;
		 if((objectives.size()<field.size() || round>=game_parameters.max_round) && isRoundFinished()) {
			 round--;
			 handleGameEvent(new EndGameEvent(this));
			 finished=true;
		 }
		 return finished;
	 }
	 
	 public boolean isRoundFinished() {
		 return field.isFinished();
	 }
	 
	 public Map<Team,Float> calculateGainScores() {
		 return round_parameters.resolution.referee.gaincalculator.calculate(teams);
 	 }
	 
	 public Team calculateWinner() {
		return round_parameters.resolution.referee.getGameWinner(teams);
	 }
	 
	 public boolean playCard(InfluenceCard card,Column column) {
		 if( canPlayCard(card, column) )
		 {
			 //Joue la carte
			 getActualTeam().getHand().remove(card);
			 column.playCard(card);
			 
			 EffectContext context=new EffectContext(this, field.indexOf(column), column.size()-2);
			 
			 handleGameEvent(new PlayCardEvent(this, context.getColumnNb(), column.size()-1, getActualTeam()));
			 
			 //Active l'effet de la carte du dessus
			 if(column.size()>1)column.get(column.size()-2).tryRevealEffect(context);
			 
			 field.checkAllState();
			 
			 return true;
		 }
		 else return false;
	 }
	 
	 public boolean canPlayCard(InfluenceCard card,Column column) {
		 return field.contains(column) && getActualHand().contains(card) &&  column.canPlayCard(card);
	 }
	 
	 public Team getActualTeam() {
		 return teams.get(turn);
	 }
	 
	 public Hand<InfluenceCard> getActualHand() {
		 return teams.get(turn).getHand();
	 }
	 
	 
	 public GameParameters getParameters() {
			return game_parameters;
	}
	 public RoundParameters getRoundParameters() {
		return round_parameters;
	}
	 public ResolutionParameters getResolutionParameters() {
		return resolution_parameters;
	}

	 public void handleGameEvent(GameEvent event) {
		 if(GAME_EVENTS!=null)GAME_EVENTS.handle(event);
		 KPDPControler.GAME_EVENTS.handle(event);
	 }
	 
	 public void handleIEffectEvent(InfluenceEffectEvent event) {
		 if(INFLUENCE_EVENTS!=null)INFLUENCE_EVENTS.handle(event);
		 KPDPControler.INFLUENCE_EVENTS.handle(event);
	 }
	 
	 public void handleControlerEvent(ControlerEvent event) {
		 if(CONTROLER_EVENTS!=null)CONTROLER_EVENTS.handle(event);
		 KPDPControler.CONTROLER_EVENTS.handle(event);
	 }
}

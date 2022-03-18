package engine.game;

import engine.card.CardViewer;
import engine.field.Column;
import engine.field.ColumnViewer;
import engine.field.Field;
import engine.field.FieldViewer;
import engine.influence.InfluenceCard;
import engine.influence.viewer.InfluenceCardContainerViewer;
import engine.teams.Team;
import engine.teams.TeamViewer;
import engine.util.ListViewerContainer;
import engine.util.Viewer;
import engine.util.ViewerKeyMap;
import engine.util.Viewers;

/**
 * Une classe qui permet de r�cup�rer les informations d'une partie.
 * @author Samuel Demont
 *
 */
public class GameViewer extends Viewer<Game> {
	
	public GameViewer(Game viewed) {
		super(viewed);
	}
	
	
	
	
	/**
	 * @return Un viewer qui permet de r�cup�rer les informations du terrain
	 */
	public FieldViewer getField() {
		return new FieldViewer(viewed.getField());
	}
	
	/**
	 * @return Un viewer qui permet de r�cup�rer les informations du joueur � qui c'est le tour
	 */
	public TeamViewer getActualTeam() {
		return new TeamViewer(viewed.getActualTeam());
	}
	
	/**
	 * @return Le nombre de joueur dans la partie
	 */
	public int countTeam() {
		return viewed.getTeams().size();
	}
	
	/**
	 * @return Le num�ro joueur � qui c'est le tour.
	 */
	public int getTurn() {
		return viewed.getTurn();
	}
	
	/**
	 * @return Le num�ro joueur � qui c'est le tour.
	 */
	public int getTotalTurns() {
		return viewed.getTotalTurns();
	}
	
	/**
	 * @return Le num�ro de la manche
	 */
	public int getRound() {
		return viewed.getRound();
	}
	
	/**
	 * @return true si la partie est termin�e sinon false
	 */
	public boolean isFinished() {
		return viewed.isFinished();
	}
	
	/**
	 * @return Un viewer permettant de r�cup�rer les informations de la main du joueur � qui c'est le tour
	 */
	public InfluenceCardContainerViewer getActualHand() {
		return new InfluenceCardContainerViewer(viewed.getActualHand());
	}
	
	/**
	 * Calcule le score de chaque joueur � partir de ses gains.
	 * @return Une Map associant � chaque joueur son score
	 */
	public ViewerKeyMap<Team,TeamViewer,Float> calculateGainScores() {
		return new ViewerKeyMap<>(viewed.calculateGainScores());
	}
	
	/**
	 * Calcule le gagant de la partie
	 * @return Un viewer permettant de r�cup�rer les informations du gagnant
	 */
	public TeamViewer calculateWinner() {
		return new TeamViewer(viewed.calculateWinner());
	}
	
	/**
	 * @return Une liste des viewers permettant de r�cup�rer les informations des colonnes du terrain
	 */
	public ListViewerContainer<ColumnViewer> getColumns(){
		ListViewerContainer<ColumnViewer> ret=new ListViewerContainer<>();
		for( Column c : viewed.getField())ret.add(new ColumnViewer(c));
		return ret;
	}
	
	/**
	 * @return Une liste de viewers permettant de r�cup�rer les informations des joueurs
	 */
	public ListViewerContainer<TeamViewer> getTeams(){
		ListViewerContainer<TeamViewer> ret=new ListViewerContainer<>();
		for( Team c : viewed.getTeams())ret.add(new TeamViewer(c));
		return ret;
	}
	
	/**
	 * Teste si une carte peut �tre plac�e sur une colonne
	 * @param card La carte � joueur
	 * @param column La colonne ou jouer
	 * @return true si la carte peut �tre jou�e sinon false
	 */
	public boolean canPlayCard(CardViewer<InfluenceCard> card,ColumnViewer column) {
		 return viewed.canPlayCard(Viewers.getOf(card), (Column)Viewers.getOf(column));
	}
	
	/**
	 * R�cup�re le num�ro d'une �quipe
	 */
	public int idOfTeam(TeamViewer vw) {
		return viewed.getTeams().indexOf(Viewers.getOf(vw));
	}
	
	/**
	 * Calcule les scores de chaque joueur sur une colonne.
	 * @param col La colonne
	 * @return Une table associant les joueurs � leur score
	 */
	public ViewerKeyMap<Team, TeamViewer, Float> calculateColumnScoresSilently(ColumnViewer col) {
		Field oldfield=viewed.getField().deepClone();
		ViewerKeyMap<Team, TeamViewer, Float> ret=new ViewerKeyMap<>(viewed.calculateColumnScores(Viewers.getOf(col)));
		viewed.setField(oldfield);
		return ret;
	}
	
	/**
	 * Calcule le joueur qui gagne une colonne
	 * @param col La colonne
	 * @return Un viewer regardant le gagnant
	 */
	public TeamViewer calculateColumnWinnerSilently(ColumnViewer col) {
		Field oldfield=viewed.getField().deepClone();
		TeamViewer ret=new TeamViewer(viewed.calculateColumnWinner(Viewers.getOf(col)));
		viewed.setField(oldfield);
		return ret;
	}
}

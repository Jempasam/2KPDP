package engine.run;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import engine.game.GameControler;
import engine.game.GameViewer;
import engine.game.KPDPControler;
import engine.game.parameters.GameParameters;
import engine.teams.Team;
import engine.teams.TeamViewer;
import engine.teams.controler.BSLAIControler;
import engine.teams.controler.GenBSLAIControler;
import engine.teams.controler.GeneticTeamControler;
import engine.teams.controler.TeamControler;
import engine.util.ViewerKeyMap;
import jempasam.swj.genetic.Genome;

/**
 * Permet de tester les bots
 * @author Samuel DEMONT
 *
 */
public class StatisticFullAIRun {
	public static void main(String[] args) {
		//runTest(ASLAIControler::new, BSLAIControler::new, List.of(RandomAIControler::new), 6, 100000);
		
		GeneticTeamControler genbot=new GenBSLAIControler();
		genbot.setGenome(Genome.valueOf("0.8790482;0.7760563;1.07278;1.8088117;1.0486059;1.1895831;0.8645608;0.68115354;1.4123323;0.5479736;0.7407464;0.87739515;0.9600342;0.6860355;0.5669113;0.6361081;0.9439447;0.61241686;0.9571696;1.6810067;1.0659485;0.69477504;1.0393193;0.9217582;1.1560845;1.0460701;0.5818149;0.8694018;0.9375685;0.9198003"));
		
		
		runGamesThenPrint(List.of(genbot::clonedChild, BSLAIControler::new, BSLAIControler::new, BSLAIControler::new,BSLAIControler::new), new GameParameters(), 10000);
		
		//trainGeneticBot(new GenBSLAIControler(), 3, List.of(BSLAIControler::new,BSLAIControler::new), new GameParameters(), 2000, 100);
		
		/*
		 * Meilleur contre BSLAI: 0.35182115;0.39210904;1.2144725;1.678874;0.5486325;1.4303644;0.4078853;0.40877694;1.2077631;0.536784;0.846525;0.59846246;0.5724667;0.90191275;0.7599462;0.9475706;1.400524;1.1718796;0.5405899;0.4716057;0.9375995;1.3593472;1.506739;1.3898938;2.0309467;4.4811172;1.0123229;1.0187557;1.5422838;0.41198397
		 */
	}
	
	protected static String trainGeneticBot(GeneticTeamControler genetic_bot, int genetic_number, List<Supplier<TeamControler>> other_bots, GameParameters parameters, int run_number_per_gen, int gen_number) {
		List<GeneticTeamControler> individuals=new ArrayList<>();
		GeneticTeamControler best=genetic_bot;
		GeneticTeamControler best_breed=genetic_bot;
		for(int i=0; i<gen_number; i++) {
			
			//New individuals
			individuals.clear();
			individuals.add(best.clonedChild());
			individuals.add(best_breed.clonedChild());
			for(int y=0; y<genetic_number-2; y++) {
				individuals.add(best_breed.mutatedChild());
			}
			
			//Create supplier list
			List<Supplier<TeamControler>> torunlist=new ArrayList<>();
			for(GeneticTeamControler gtc : individuals) {
				GeneticTeamControler a=gtc;
				torunlist.add(a::clonedChild);
			}
			for(Supplier<TeamControler> sup : other_bots)torunlist.add(sup);
			
			//Run test
			int[] results=runGames(torunlist, parameters, run_number_per_gen).getWinningNumbers();
			
			//Select the best
			int higherindex[]= {0,0};
			int higherscore[]= {0,0};
			for(int y=0; y<genetic_number; y++) {
				for(int o=0; o<higherindex.length; o++)
				if(results[y]>higherscore[o]) {
					higherscore[o]=results[y];
					higherindex[o]=y;
					break;
				}
			}
			best_breed=individuals.get(higherindex[0]).breed(individuals.get(higherindex[1]));
			best=individuals.get(higherindex[0]);
			
			//Print
			System.out.println("The best is: "+best.getGenome());
		}
		return best.getGenome().toString();
	}
	protected static void runGamesThenPrint(List<Supplier<TeamControler>> bots, GameParameters parameters, int run_number) {
		System.out.println(runGames(bots, parameters, run_number)+"\n");
	}
	protected static void runTest(Supplier<TeamControler> bot1, Supplier<TeamControler> bot2, List<Supplier<TeamControler>> bots, int player_number, int run_number) {
		//Paramètres des parties
		GameParameters parameters=new GameParameters();
		parameters.nb_team=player_number;
		
		//Initialisation des statistiques
		int[] victory=new int[parameters.nb_team]; Arrays.fill(victory, 0);
		int[] duel={0,0};
		
		
		//Simulations
		for(int i=0; i<run_number; i++) {
			//Création de la partie
			GameControler game=KPDPControler.startGame(parameters);
			
			//Choix des IAs
			for(int y=0; y<game.getTeams().size(); y++)game.getTeams().get(y).setControler(bots.get(y%bots.size()).get());
			int teamid_bot1=(int)(game.countTeam()*Math.random());
			int teamid_bot2=(int)(game.countTeam()*Math.random());
			while(teamid_bot1==teamid_bot2)teamid_bot2=(int)(game.countTeam()*Math.random());
			game.getTeams().get(teamid_bot1).setControler(bot1.get());
			game.getTeams().get(teamid_bot2).setControler(bot2.get());
			
			//Démarrage de la partie
			game.launch();
			while(!game.isFinished()) game.nextTurn();
			
			//Renseignement des statistiques
			int id_of_winning=game.idOfTeam(game.calculateWinner());
			victory[id_of_winning]++;
			if(id_of_winning==teamid_bot1)duel[0]++;
			else if(id_of_winning==teamid_bot2)duel[1]++;
		}
		//Calcul des resultats
		int total_partie=0;
		int total_duel=duel[0]+duel[1];
		for(int a : victory)total_partie+=a;
		int[] victory_pour_cent=new int[victory.length];
		int[] victory_pour_cent_sur_tout=new int[victory.length];
		for(int i=0; i<victory.length; i++)victory_pour_cent[i]=victory[i]*100/total_partie;
		
		
		//Affichage des résultats
		System.out.println("Nombre de parties: "+total_partie);
		System.out.println("Victoires: "+Arrays.toString(victory));
		System.out.println("Victoires(%): "+Arrays.toString(victory_pour_cent));
		System.out.println("Duel: Bot1 "+duel[0]+" vs Bot2 "+duel[1]);
		System.out.println("Duel(%): Bot1 "+(duel[0]*100/total_duel)+"% vs Bot2 "+(duel[1]*100/total_duel)+"%");
		System.out.println("Duel(% sur total): Bot1 "+(duel[0]*100/total_partie)+"% vs Bot2 "+(duel[1]*100/total_partie)+"%");
	}
	
	protected static GameResult runGames(List<Supplier<TeamControler>> bots, GameParameters parameters, int run_number) {
		parameters.nb_team=bots.size();
		
		//Initialisation des statistiques
		GameResult results=new GameResult();
		results.initialize(bots.size());
		
		//Simulations
		for(int i=0; i<run_number; i++) {
			//Création de la partie
			GameControler game=KPDPControler.startGame(parameters);
			
			//Choix des IAs
			int[] playerbotnum=new int[bots.size()];
			
			List<Integer> places=new ArrayList<Integer>();
			for(int y=0; y<game.getTeams().size(); y++)places.add(y);
			int botnum=0;
			for(Supplier<TeamControler> bot : bots) {
				int place=places.remove((int)(places.size()*Math.random()));
				game.getTeams().get(place).setControler(bot.get());
				playerbotnum[place]=botnum;
				botnum++;
			}
			
			//Démarrage de la partie
			game.launch();
			while(!game.isFinished()) game.nextTurn();
			
			//Renseignement des statistiques
			ViewerKeyMap<Team, TeamViewer, Float> scores=game.calculateGainScores();
			for(int pid=0; pid<playerbotnum.length; pid++) {
				int botid=playerbotnum[pid];
				int place=getPlaceOfTeam(game, scores, game.getTeams().get(pid));
				int score=scores.get(game.getTeams().get(pid)).intValue();
				results.addResult(botid, place, score);
				results.addPositionResult(pid, place, score);
			}
		}
		return results;
	}
	
	private static int getPlaceOfTeam(GameViewer game,ViewerKeyMap<Team, TeamViewer, Float> scores, TeamViewer team) {
		int place=0;
		for(TeamViewer t : game.getTeams()) {
			if(!t.viewSame(team) && scores.get(t)>scores.get(team))place++;
		}
		return place;
	}
	
	protected static class ControlerGameResult{
		
		private int[] places;
		private int gameplayed;
		private int controlerid;
		private int totalscore;
		
		public void initialize(int playernumber) {
			places=new int[playernumber];
			for(int i=0; i<playernumber; i++)places[i]=0;
			gameplayed=0;
			totalscore=0;
		}
		
		public void setControler(int controlerid) {
			this.controlerid=controlerid;
		}
		
		public void addResult(int place, int score) {
			gameplayed++;
			totalscore+=score;
			places[place]++;
		}
		
		//PLACE
		
		public int getLastPlace() {
			return places.length-1;
		}
		
		/**
		 * Le nombre de fois ou le controler a fini à cette place
		 */
		public int getPlaceNumber(int place) {
			return places[place];
		}
		
		/**
		 * Le pourcentage de fois ou le controler a fini à cette place
		 */
		public int getPlacePercentage(int place) {
			return getPlaceNumber(place)*100/gameplayed;
		}
		
		//WINNING
		/**
		 * Le nombre de fois ou le controler a gagné
		 */
		public int getWinningNumber() {
			return places[0];
		}
		
		/**
		 * Le pourcentage de fois ou le controler a gagné
		 */
		public int getWinningPercentage() {
			return getWinningNumber()*100/gameplayed;
		}
		
		/**
		 * Le numéro du controler
		 */
		public int getControlerId() {
			return controlerid;
		}
		
		public int getGameNumber() {
			return gameplayed;
		}
		
		//SCORE
		
		/**
		 * Le total de score de toutes les parties
		 */
		public int getTotalScore() {
			return totalscore;
		}
		
		/**
		 * Le score moyen par partie
		 */
		public int getAverageScore() {
			return totalscore/gameplayed;
		}
		
		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder();
			sb.append("(Bot ").append(getControlerId()).append(")(");
			//Winning
			sb.append("Win ").append(getWinningNumber()).append("/").append(getGameNumber()).append(" or ").append(getWinningPercentage()).append("% | ");
			//Places
			for(int i=0; i<=getLastPlace(); i++) {
				sb.append(i+1).append("eme ").append(getPlaceNumber(i)).append("/").append(getGameNumber()).append(" or ").append(getPlacePercentage(i)).append("% | ");
			}
			sb.append("Score ").append(getTotalScore()).append(" Average Score ").append(getAverageScore()).append(" ");
			sb.append(")");
			return sb.toString();
		}
	}
	protected static class GameResult{
		/**
		 * Resultat pour chaques controlers
		 */
		private ControlerGameResult controlerResults[];
		
		/**
		 * Resultat pour chaque position
		 */
		private ControlerGameResult positionResults[];
		
		public void initialize(int playernumber) {
			controlerResults=new ControlerGameResult[playernumber];
			positionResults=new ControlerGameResult[playernumber];
			for(int i=0; i<controlerResults.length; i++) {
				controlerResults[i]=new ControlerGameResult();
				controlerResults[i].setControler(i);
				controlerResults[i].initialize(playernumber);
				
				positionResults[i]=new ControlerGameResult();
				positionResults[i].setControler(i);
				positionResults[i].initialize(playernumber);
			}
		}
		
		public ControlerGameResult getResult(int i) {
			return controlerResults[i];
		}
		
		public ControlerGameResult getPositionResult(int i) {
			return positionResults[i];
		}
		
		private int[] getValueForAll(Function<ControlerGameResult, Integer> value) {
			int ret[]=new int[controlerResults.length];
			for(int i=0; i<controlerResults.length; i++) {
				ret[i]=value.apply(controlerResults[i]);
			}
			return ret;
		}
		
		public int[] getPlaceNumbers(int place) {
			return getValueForAll((cr)->cr.getPlaceNumber(place));
		}
		public int[] getPlacePercentages(int place) {
			return getValueForAll((cr)->cr.getPlacePercentage(place));
		}
		
		public int[] getWinningNumbers() {
			return getValueForAll(ControlerGameResult::getWinningNumber);
		}
		public int[] getWinningPercentages() {
			return getValueForAll(ControlerGameResult::getWinningPercentage);
		}
		
		public void addResult(int botid, int place, int score) {
			controlerResults[botid].addResult(place,score);
		}
		
		public void addPositionResult(int position, int place, int score) {
			positionResults[position].addResult(place,score);
		}
		
		public ControlerGameResult[] getSortedResults() {
			ControlerGameResult[] sorted=Arrays.copyOf(controlerResults,controlerResults.length);
			Arrays.sort(sorted,(a,b)->a.getWinningNumber()>b.getWinningNumber()? 1 : -1);
			return sorted;
		}
		
		public ControlerGameResult[] getReverseSortedResults() {
			ControlerGameResult[] sorted=Arrays.copyOf(controlerResults,controlerResults.length);
			Arrays.sort(sorted,(a,b)->a.getWinningNumber()>b.getWinningNumber()? -1 : 1);
			return sorted;
		}
		
		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder();
			//Position scores
			sb.append("Positions "); for(ControlerGameResult cgr : positionResults)sb.append(cgr.getWinningPercentage()).append("% "); sb.append("\n");
			//Controler states
			for(ControlerGameResult cgr : controlerResults) {
				sb.append(cgr.toString()).append("\n");
			}
			//Winners percentage
			sb.append("WinningNumber ").append(Arrays.toString(getWinningNumbers())).append("\n");
			//Controlers percentage
			sb.append("Win Percentage ").append(Arrays.toString(getWinningPercentages())).append("\n");
			for(int i=0; i<controlerResults.length; i++)
				sb.append(i+1).append("eme Percentage ").append(Arrays.toString(getPlacePercentages(i))).append("\n");
			//Classement
			sb.append("Classement "); for(ControlerGameResult cgr : getReverseSortedResults())sb.append(cgr.getControlerId()).append(" "); sb.append("\n");
			return sb.toString();
		}
	}
}

package engine.teams.controler;

import engine.field.ColumnViewer;
import engine.game.GameViewer;
import engine.influence.type.DomainBoostICT;
import engine.influence.type.InfluenceCardTypes;
import engine.influence.viewer.InfluenceCardViewer;
import engine.objective.Domain;
import engine.teams.TeamViewer;
import jempasam.swj.prototype.loader.tags.ObjectSerializable;

@ObjectSerializable
public class BSLAIControler extends ScoreListAIControler implements PrototypeTeamControler {
	@Override
	protected int calculateScoreOf(InfluenceCardViewer ic, ColumnViewer col, TeamViewer player, GameViewer game) {
		int score=0;
		
		//On ajoute les points de la carte au score
		score+=(int)(ic.getPoints());
		
		//On ajoute les points de la colonne au score
		score+=col.getObjective()*2;
		
		int capture_indice=0;
		int player_by_column=game.getTeams().size();
		capture_indice-=col.sum((card)->card.getTeam().viewSame(ic.getTeam()) ? 0 : (int)(card.getPoints()))/player_by_column;
		capture_indice+=col.sum((card)->!card.getTeam().viewSame(ic.getTeam()) ? 0 : (int)(card.getPoints()));
		if(capture_indice<10)score+=capture_indice;
		else score-=4;
		
		//TEMPETE sur les colonnes ou le joueur domine
		if(ic.getType()==InfluenceCardTypes.TEMPETE) score += capture_indice;
		
		
		//Domain boost
		if(ic.getType() instanceof DomainBoostICT) {
			DomainBoostICT dbic=(DomainBoostICT)ic.getType();
			Domain domain=((DomainBoostICT)ic.getType()).getDomain();
			if(col.getDomain()==dbic.getDomain()) {
				score+=4;
			}
		}
		
		//Assassin boost
		if(ic.getType()==InfluenceCardTypes.ASSASSIN && col.size()<col.getObjective()) score +=8;
		
		//Si il n'y a pas de mousquetaire pour annuler les effets
		if(!col.testIfOne( (icard)->icard.getType()==InfluenceCardTypes.MOUSQUETAIRE ))
		{
			//PETIT GEANT
			// - Posé boost
			if(ic.getType()==InfluenceCardTypes.PETIT_GEANT) score+=Math.max(col.size()*3,col.getObjective()*3);
			// - Ennemi deboost
			if(col.testIfOne((card)->card.getType()==InfluenceCardTypes.PETIT_GEANT&&!card.getTeam().viewSame(player)))score-=3;
			// - Allié boost
			if(col.testIfOne((card)->card.getType()==InfluenceCardTypes.PETIT_GEANT&&card.getTeam().viewSame(player)))score+=3;
			
			//DRAGON
			// - Posé boost
			if(ic.getType()==InfluenceCardTypes.DRAGON) score+=col.sum((card)->!card.getTeam().viewSame(ic.getTeam()) ? 2 : 0);
			// - Ennemi deboost
			if(col.testIfOne((card)->!card.getTeam().viewSame(ic.getTeam())&&card.getType()==InfluenceCardTypes.DRAGON))score-=2;
			
			//SORCIERE
			// - Posé boost
			if(ic.getType()==InfluenceCardTypes.SORCIERE) {
				score+=4*col.findAllFor((card)->!card.getTeam().viewedsEquals(ic.getTeam()) && card.getPoints()<=8).size();
				score-=4*col.sum((card)->card.getTeam().viewedsEquals(ic.getTeam()) && card.getPoints()<=8 ? 1 : 0);
			}
			// - Deboost
			if(col.testIfOne((card)->card.getType()==InfluenceCardTypes.SORCIERE)&&ic.getPoints()<=8)score-=100;
			
			//MAGICIEN
			// - Posé boost
			if(ic.getType()==InfluenceCardTypes.MAGICIEN) {
				score+=4*col.findAllFor((card)->!card.getTeam().viewedsEquals(ic.getTeam()) && card.getPoints()>=10).size();
				score-=4*col.sum((card)->card.getTeam().viewedsEquals(ic.getTeam()) && card.getPoints()>=10 ? 1 : 0);
			}
			// - Deboost
			if(col.testIfOne((card)->card.getType()==InfluenceCardTypes.MAGICIEN)&&ic.getPoints()>=10)score-=100;
			
			//Combo romeo
			if(ic.getType()==InfluenceCardTypes.ROMEO && col.testIfOne((card)->card.getType()==InfluenceCardTypes.JULIETTE&&card.getTeam().viewedsEquals(ic.getTeam())))score+=8;
			if(ic.getType()==InfluenceCardTypes.JULIETTE && col.testIfOne((card)->card.getType()==InfluenceCardTypes.ROMEO&&card.getTeam().viewedsEquals(ic.getTeam())))score+=8;
			
			//Combo prince
			if(ic.getType()==InfluenceCardTypes.PRINCE && col.testIfOne((card)->card.getType()==InfluenceCardTypes.ECUYER&&card.getTeam().viewedsEquals(ic.getTeam())))score+=1000;
			if(ic.getType()==InfluenceCardTypes.PRINCE && col.testIfOne((card)->card.getType()==InfluenceCardTypes.ECUYER&&card.getTeam().viewedsEquals(ic.getTeam())))score+=1000;
			
			//Ermite
			if(ic.getType()==InfluenceCardTypes.ERMITE) score -= Math.max(col.size(), col.getObjective());
			
			//Sosie boost
			if(ic.getType()==InfluenceCardTypes.SOSIE && col.size()<col.getObjective()-1)score+=9;
		}
		
		//Technique de la tempête magique
		if(col.size()==1 && col.get(0).getTeam().viewSame(ic.getTeam()) && ic.getType()==InfluenceCardTypes.TEMPETE)score+=35;
		
		if(col.size()>0 && col.last().getTeam().viewSame(ic.getTeam())) {
			
			//Technique du sosie éclair
			if(col.last().getType()==InfluenceCardTypes.SOSIE)score+=(ic.getPoints()-10)*2;
			
			//Esquive des assassins alliés
			if(col.last().getType()==InfluenceCardTypes.ASSASSIN)score-=100;
		}
		
		return score;
	}
	@Override
	public int askForColumn(TeamViewer team, GameViewer game, String reason, int try_counter) {
		
		//Echange de colonne
			if(reason.equals("exchangeColumn1")) {
				if(try_counter>0)return (int)(Math.random()*game.getField().size());
				
				return game.getField().indexOfViewing(game.getField().getFirstHigher(ColumnViewer::getObjective));
			}
			else if(reason.equals("exchangeColumn2")) {
				if(try_counter>0)return (int)(Math.random()*game.getField().size());
				
				return game.getField().indexOfViewing(game.getField().getFirstHigher((col)->{
					int capture_indice=0;
					int player_by_column=game.getTeams().size();
					capture_indice-=col.sum((card)->card.getTeam().viewSame(team) ? 0 : (int)(card.getPoints()))/player_by_column;
					capture_indice+=col.sum((card)->!card.getTeam().viewSame(team) ? 0 : (int)(card.getPoints()));
					return capture_indice;
				}));
			}
		
		return super.askForColumn(team, game, reason, try_counter);
	}
	
	@Override public PrototypeTeamControler clone() { return new BSLAIControler(); }
}

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
public class ASLAIControler extends ScoreListAIControler implements PrototypeTeamControler {
	@Override
	protected int calculateScoreOf(InfluenceCardViewer ic, ColumnViewer col, TeamViewer player, GameViewer game) {
		int score=0;
		
		//On ajoute les points de la carte au score
		score+=(int)(ic.getPoints());
		
		//On ajoute les points de la colonne au score
		score+=col.getObjective()*2;
		
		score-=col.findAllFor((card)->!card.getTeam().viewedsEquals(ic.getTeam())).size();
		
		//On ajout des points pour les petits géant selon le remplissage de la colonne
		if(ic.getType()==InfluenceCardTypes.PETIT_GEANT||ic.getType()==InfluenceCardTypes.DRAGON) score+=col.size()*3;
		
		//Combo romeo
		if(ic.getType()==InfluenceCardTypes.ROMEO && col.testIfOne((card)->card.getType()==InfluenceCardTypes.JULIETTE&&card.getTeam().viewedsEquals(ic.getTeam())))score+=10;
		if(ic.getType()==InfluenceCardTypes.JULIETTE && col.testIfOne((card)->card.getType()==InfluenceCardTypes.ROMEO&&card.getTeam().viewedsEquals(ic.getTeam())))score+=10;
		
		//Domain boost
		if(ic.getType() instanceof DomainBoostICT) {
			DomainBoostICT dbic=(DomainBoostICT)ic.getType();
			Domain domain=((DomainBoostICT)ic.getType()).getDomain();
			if(col.getDomain()==dbic.getDomain()) {
				score+=4;
			}
		}
		
		//Sorciere boost
		if(ic.getType()==InfluenceCardTypes.SORCIERE)score+=4*col.findAllFor((card)->!card.getTeam().viewedsEquals(ic.getTeam()) && card.getPoints()<=8).size();
		
		return score;
	}
	@Override public PrototypeTeamControler clone() { return new ASLAIControler(); }
}

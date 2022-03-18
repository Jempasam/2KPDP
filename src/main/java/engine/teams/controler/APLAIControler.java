package engine.teams.controler;

import java.util.List;
import java.util.function.BiPredicate;

import engine.field.ColumnViewer;
import engine.influence.type.DomainBoostICT;
import engine.influence.type.InfluenceCardTypes;
import engine.influence.viewer.InfluenceCardViewer;
import engine.objective.Domain;

public class APLAIControler extends PredicateListAIControler{

	@Override
	protected void fillPredicates(List<BiPredicate<InfluenceCardViewer, ColumnViewer>> predicates) {
		//On joue en priorité les cartes hautes sur les colonne hautes
		predicates.add((ic,c)->{
			return (ic.getType()==InfluenceCardTypes.ROI || ic.getType()==InfluenceCardTypes.REINE || ic.getType()==InfluenceCardTypes.JULIETTE) && c.getObjective()>=3;
		});
		
		//Puis on joue les petits géants/dragons sur les colonnes très remplies ou à haut point
		predicates.add((ic,c)->{
			return (ic.getType()==InfluenceCardTypes.PETIT_GEANT||ic.getType()==InfluenceCardTypes.DRAGON) && (c.getObjective()>=4 || c.size()>=5);
		});
		
		//Puis on essaye de faire des combos avec romeo et juliette
		predicates.add((ic,c)->{
			if(ic.getType()==InfluenceCardTypes.ROMEO && c.testIfOne((card)->card.getType()==InfluenceCardTypes.JULIETTE&&card.getTeam().viewedsEquals(ic.getTeam())))return true;
			else if(ic.getType()==InfluenceCardTypes.JULIETTE && c.testIfOne((card)->card.getType()==InfluenceCardTypes.ROMEO&&card.getTeam().viewedsEquals(ic.getTeam())))return true;
			return false;
		});
		
		//Puis on joue les cartes qui combotent avec les domaines sur les bons domaines
		predicates.add((ic,c)->{
			if(ic.getType() instanceof DomainBoostICT) {
				Domain domain=((DomainBoostICT)ic.getType()).getDomain();
				return domain==c.getDomain();
			}
			return false;
		});
		
		//Puis on essaye de jouer une sorciere sur colonne avec pas mal de carte ennemis basses
		predicates.add((ic,c)->
			ic.getType()==InfluenceCardTypes.SORCIERE && c.findAllFor((card)->!card.getTeam().viewedsEquals(ic.getTeam()) && card.getPoints()<=8).size()>3
		);
	}
	
}

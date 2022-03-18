package engine.calculator.fnal;

import java.util.HashMap;
import java.util.Map;

import engine.card.container.CardPile;
import engine.objective.Domain;
import engine.objective.ObjectiveCard;

/**
 * Un calculateur de gain qui:
 * -Si le joueur possède moins de {@value #domain_bonus_number} domaines différents, additionne les points de toutes les cartes objectif.
 * -Si le joueur possède au moins {@value #domain_bonus_number} domaines différents, additionne les points de la carte la plus haute pour chaque domaine, les multiplie par {@value #domain_bonus_multiplier}, puis ajoute la somme du reste des cartes multiplié par {@value #supplement_multiplier}.
 * BaseGainCalculator(6,2,-1) correspond au jeu de base
 * @author Samuel Demont
 *
 */
public class BaseGainCalculator implements IGainCalculator{
	int domain_bonus_number;
	float domain_bonus_multiplier;
	float supplement_multiplier;
	
	public BaseGainCalculator(int domain_bonus_number, float domain_bonus_multiplier, float supplement_multiplier) {
		this.domain_bonus_multiplier=domain_bonus_multiplier;
		this.domain_bonus_number=domain_bonus_number;
		this.supplement_multiplier=supplement_multiplier;
	}
	@Override
	public float calculate(CardPile<ObjectiveCard> gains) {
		Map<Domain,ObjectiveCard> dom=new HashMap<>();
		for(ObjectiveCard objc : gains)
		{
			Domain newdom=objc.getDomain();
			if( !dom.containsKey(newdom) || dom.get(newdom).getObjective()<objc.getObjective() ) {
				dom.put(newdom, objc);
			}
		}
		float score=0;
		if(dom.size()>=domain_bonus_number) {
			for(ObjectiveCard c : dom.values()) {
				score+=c.getObjective();
			}
			score*=domain_bonus_multiplier;
			for(ObjectiveCard objc : gains)
			{
				if(!dom.values().stream().anyMatch( x -> x==objc))
				{
					score+=objc.getObjective()*supplement_multiplier;
				}
			}
		}else {
			for(ObjectiveCard objc : gains) {
				score+=objc.getObjective();
			}
		}
		return score;
	}
	
}

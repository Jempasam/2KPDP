package engine.calculator.fnal;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import engine.card.container.CardPile;
import engine.objective.Domain;
import engine.objective.ObjectiveCard;
/**
 * Un calculateur de gain simple.
 * Seul les {@value #max_domain} domaines qui cumulent le plus de points comptent pour le score du joueur.
 * Les points du domaines qui cumule le plus de points sont multiplié par {@value #first_domain_bonus}
 * @author ds001272
 *
 */
public class DomainGainCalculator implements IGainCalculator{
	private int max_domain,first_domain_bonus;
	
	public DomainGainCalculator(int max_domain, int first_domain_bonus) {
		this.max_domain = max_domain;
		this.first_domain_bonus = first_domain_bonus;
	}
	
	@Override
	public float calculate(CardPile<ObjectiveCard> gains) {
		Map<Domain,Integer> dom=new HashMap<>();
		
		for(ObjectiveCard objc : gains)
		{
			Domain newdom=objc.getDomain();
			int value=0;
			if(dom.containsKey(newdom))value=dom.get(newdom);
			
			dom.put(newdom, value+objc.getObjective());
		}
		float ret=0;
		for(int i=0; i<max_domain && !dom.isEmpty(); i++) {
			int max=0;
			Domain toremove=null;
			for(Entry<Domain,Integer> entry: dom.entrySet()) {
				if(entry.getValue()>max) {
					max=entry.getValue();
					toremove=entry.getKey();
				}
			}
			if(toremove!=null)dom.remove(toremove);
			if(i==0)max=max*first_domain_bonus;
			ret+=max;
		}
		return ret;
	}
	
}

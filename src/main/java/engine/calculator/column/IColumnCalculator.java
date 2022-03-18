package engine.calculator.column;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import engine.field.Column;
import engine.field.Field;
import engine.teams.Team;

/**
 * Permet de calculer le score de chaque joueur sur une colonne ou sur le terrain
 * @author Samuel Demont
 *
 */
public interface IColumnCalculator {
	
	Map<Team,Float> calculate(Column column);
	
	public default Map<Team,Float> calculate(Field field){
		Map<Team,Float> total=new HashMap<>();
		for(Column c : field)
			for(Entry<Team,Float> entry : calculate(c).entrySet()) {
				float value=0; if(total.containsKey(entry.getKey()))value=total.get(entry.getKey());
				total.put(entry.getKey(), entry.getValue()+value);
			}
		return total;
	}
	
}

package engine.calculator.winner;

import java.util.Map;
import java.util.Map.Entry;

import engine.teams.Team;
import engine.util.Comparator;

public class ComparatorWinnerSelector implements IWinnerSelector{
	Comparator<Float> comparator;
	public ComparatorWinnerSelector(Comparator<Float> comparator) {
		this.comparator=comparator;
	}
	@Override
	public Team calculate(Map<Team, Float> scores) {
		Team winner=null;
		float winner_score=0;
		for(Entry<Team, Float> entry : scores.entrySet()) {
			if(winner==null || comparator.compare(entry.getValue(),winner_score)==1) {
				winner=entry.getKey();
				winner_score=entry.getValue();
			}
		}
		return winner;
	}

}

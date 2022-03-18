package engine.influence.filter;

import java.util.function.Consumer;

import engine.influence.InfluenceCard;
import engine.teams.Team;

public class SetTeamFilter implements Consumer<InfluenceCard>{
	private Team team;
	public SetTeamFilter(Team team) {
		this.team=team;
	}
	@Override
	public void accept(InfluenceCard t) {
		t.setTeam(team);
	}
}
package engine.teams.controler;

import jempasam.swj.genetic.Specimen;

public interface GeneticTeamControler extends PrototypeTeamControler, Specimen<GeneticTeamControler>{
	@Override
	default PrototypeTeamControler clone() {
		return clonedChild();
	}
}

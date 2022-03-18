package engine.teams.controler;

import jempasam.swj.genetic.Genome;
import jempasam.swj.prototype.loader.tags.ObjectParameter;

public abstract class GenScoreListAIControler extends ScoreListAIControler implements GeneticTeamControler{
	
	private Genome genome;
	
	public GenScoreListAIControler() {
		super();
		genome=new Genome();
	}
	
	@ObjectParameter
	public void genome(String genome) {
		this.genome=Genome.valueOf(genome);
	}
	
	public GenScoreListAIControler(Genome genome) {
		super();
		this.genome=genome;
	}
	
	@Override
	public Genome getGenome() {
		return genome;
	}

	@Override
	public void setGenome(Genome newgenome) {
		genome=newgenome;
	}
	
	@Override
	public PrototypeTeamControler clone() {
		return clonedChild();
	}
}

package jempasam.swj.genetic;

public interface Specimen<T extends Specimen<T>> {
	Genome getGenome();
	void setGenome(Genome newgenome);
	T clonedChild();
	
	default void mutate() {
		getGenome().mutate();
	}
	
	default T breed(T mate) {
		T ret=clonedChild();
		ret.setGenome(getGenome().breed(mate.getGenome()));
		return ret;
	}
	
	default T mutatedChild() {
		T ret=clonedChild();
		ret.setGenome(getGenome().clone());
		ret.getGenome().mutate();
		return ret;
	}
	
	default float getGen(int position) {
		return getGenome().get(position);
	}
	
	default String saveGenome() {
		return getGenome().toString();
	}
	default void loadGenome(String str) {
		setGenome(Genome.valueOf(str));
	}
}

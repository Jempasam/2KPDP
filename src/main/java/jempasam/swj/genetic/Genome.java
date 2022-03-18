package jempasam.swj.genetic;

import java.util.ArrayList;
import java.util.List;

import jempasam.swj.container.Container;

public class Genome implements Container<Float>{
	private List<Float> genes;
	
	public Genome() {
		genes=new ArrayList<>();
	}
	
	public Genome clone(){
		Genome gen=new Genome();
		for(float f : this) {
			gen.genes.add(f);
		}
		return gen;
	}
	
	public void mutate() {
		for(int i=0; i<genes.size(); i++) {
			float value=genes.get(i);
			value+=value*0.4f*Math.random()-value*0.2f;
			genes.set(i, value);
		}
	}
	 
	public Genome breed(Genome mate) {
		Genome ret=new Genome();
		int size=Math.max(mate.size(), size());
		
		for(int i=0; i<size; i++) {
			float value=(get(i)+mate.get(i))/2;;
			ret.set(i, value);
		}
		return ret;
	}

	@Override public int size() { return genes.size(); }

	@Override
	public Float get(int position) {
		resizeToMinimum(position+1);
		return genes.get(position);
	}
	
	@Override
	public void set(int position, Float obj) {
		resizeToMinimum(position+1);
		genes.set(position,obj);
	}
	
	@Override
	public void insert(int position, Float obj) {
		genes.add(position, obj);
	}
	
	@Override
	public Float remove(int position) {
		return genes.remove(position);
	}
	
	public void resizeToMinimum(int size) {
		while(genes.size()<size)genes.add(1f);
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		if(size()>0) {
			sb.append(get(0).toString());
			for(int i=1; i<size(); i++)sb.append(";").append(get(i).toString());
		}
		return sb.toString();
	}
	
	public static Genome valueOf(String str) {
		Genome genome=new Genome();
		String[] values=str.split(";");
		for(String v : values) {
			genome.add(Float.valueOf(v));
		}
		return genome;
	}
}

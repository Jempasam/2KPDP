package jempasam.swj.data.chunk;

public abstract class DataChunk {
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public DataChunk(String name) {
		this.name=name;
	}
	
	public abstract DataChunk clone();
}

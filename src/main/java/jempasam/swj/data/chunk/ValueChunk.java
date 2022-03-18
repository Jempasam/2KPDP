package jempasam.swj.data.chunk;

public class ValueChunk extends DataChunk{
	
	private String value;
	
	public ValueChunk(String name, String value) {
		super(name);
		this.value = value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	
	public DataChunk clone(){
		return new ValueChunk(getName(), getValue());
	}
	
	@Override
	public String toString() {
		return "\""+getName()+"\":\""+getValue()+"\"";
	}
}

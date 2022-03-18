package jempasam.swj.registry;

public class AlreadyRegistredException extends Exception {
	private String id;
	private Object obj;
	
	public String getId() {
		return id;
	}

	public Object getObject() {
		return obj;
	}

	public AlreadyRegistredException(String s, String id, Object obj) {
		super(s);
		this.id=id;
		this.obj=obj;
	}
}

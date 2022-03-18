package jempasam.swj.registry;

public class IdAlreadyUserException extends Exception {
	private String id;
	private Object obj;
	
	public String getId() {
		return id;
	}

	public Object getObject() {
		return obj;
	}
	
	public IdAlreadyUserException(String t, String id, Object obj) {
		super(t);
		this.id=id;
		this.obj=obj;
	}
}

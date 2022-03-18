package jempasam.swj.deserializer;

import java.lang.reflect.Type;

public interface PrimitiveDeserializer {
	//Integer types
	byte tobyte(String str) throws NumberFormatException;
	short toshort(String str) throws NumberFormatException;
	int toint(String str) throws NumberFormatException;
	long tolong(String str) throws NumberFormatException;
	
	//Real types
	float tofloat(String str) throws NumberFormatException;
	double todouble(String str) throws NumberFormatException;
	
	//Text type
	char tochar(String str) throws NumberFormatException;
	String tostr(String str) throws NumberFormatException;
	
	//Other
	boolean tobool(String str);
	
	default Object asType(Type type, String str) throws NumberFormatException {
		if(type==Short.TYPE)return toshort(str);
		else if(type==Integer.TYPE)return toint(str);
		else if(type==Long.TYPE)return tolong(str);
		
		else if(type==Float.TYPE)return tofloat(str);
		else if(type==Double.TYPE)return todouble(str);
		
		else if(type==Character.TYPE)return tochar(str);
		
		else if(type==Boolean.TYPE)return tobool(str);
		
		else if(type==String.class)return tostr(str);
		
		else return null;
	}
	
}

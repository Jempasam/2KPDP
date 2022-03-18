package jempasam.swj.deserializer;

public class SimplePrimitiveDeserializer implements PrimitiveDeserializer {

	@Override
	public byte tobyte(String str) throws NumberFormatException {return Byte.parseByte(str);}

	@Override
	public short toshort(String str) throws NumberFormatException {return Short.parseShort(str);}

	@Override
	public int toint(String str) throws NumberFormatException {return Integer.parseInt(str);}

	@Override
	public long tolong(String str) throws NumberFormatException {return Long.parseLong(str);}
 
	@Override
	public float tofloat(String str) throws NumberFormatException {return Float.parseFloat(str);}

	@Override
	public double todouble(String str) throws NumberFormatException {return Double.parseDouble(str);}

	@Override
	public char tochar(String str) throws NumberFormatException {
		if(str.length()!=1) {
			return str.charAt(0);
		}else {
			throw new NumberFormatException("\""+str+"\" is not a valid char.");
		}
	}

	@Override
	public String tostr(String str) {
		return str;
	}

	@Override
	public boolean tobool(String str) throws NumberFormatException {return Boolean.parseBoolean(str);}

}

package interfaceCommon.JFXUtils;

/**
 * Permet différentes opérations en rapport avec le texte
 * @author Samuel Demont
 *
 */
public class StringHelper {
	public static String of(Float number) {
		if(number.intValue()==number.floatValue())return Integer.toString(number.intValue());
		else return number.toString();
	}
}

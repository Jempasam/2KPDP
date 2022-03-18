package engine.objective;

import engine.game.KPDPControler;

/**
 * Représente un domaine du jeu KPDP
 * @author Samuel Demont
 *
 */
public class Domain{
	
	public Domain() {
	}
	
	private static Domain register(Domain newdom,String str) {
		try{
			KPDPControler.DOMAIN_REGISTRY.register(str, newdom);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return newdom;
	}
	
	public static Domain ALCHIMIE = register(new Domain(), "Alc");
	public static Domain AGRICULTURE = register(new Domain(), "Agr"); 
	public static Domain COMBAT = register(new Domain(), "Cbt"); 
	public static Domain RELIGION = register(new Domain(), "Rel"); 
	public static Domain MUSIQUE = register(new Domain(), "Mus"); 
	public static Domain COMMERCE = register(new Domain(), "Com");
	
	public static Domain CHASSE = register(new Domain(), "mo.Cha");
	public static Domain DIVERTISSEMENT = register(new Domain(), "mo.Div");
	public static Domain MAL = register(new Domain(), "mo.Mal");
	
	@Override
	public String toString() {
		return KPDPControler.DOMAIN_REGISTRY.getIdOrDefault(this,"noname");
	}
}
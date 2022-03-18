package interfaceCommon.lang;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import interfaceCommon.image.ImageManager;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import jempasam.swj.resources.ResourceManager;

/**
 * Le gestionnaire de langue charge les langue et permet de créer des bindings
 * @author Samuel Demont
 *
 */
public class LanguageManager{
	private static Map<String, Language> languages;
	private static ObjectProperty<Language> selected;
	
	private LanguageManager() {
		
	}
	
	static {
		languages=new HashMap<>();
		selected= new SimpleObjectProperty<>(null); 
		
		for(String langname : ResourceManager.listInDir("lang")) {
				System.out.println(langname);
				InputStream lang=ImageManager.class.getResourceAsStream("/lang/"+langname);
				Language newlanguage=new Language(lang);
				languages.put(langname, newlanguage);
		}
		select("fr.properties");
	}
	
	
	public static Collection<Language> getLanguages() {
		return languages.values();
	}
	
	public static Language getLanguage(String name) {
		return languages.get(name);
	}
	
	/**
	 * Retourne un texte à partir d'une clé de traduction
	 * @param key La clé de traduction
	 * @return le texte
	 */
	public static String get(String key) {
		return selected.get().get(key);
	}
	
	public static Collection<String> getKeys() {
		return selected.get().getKeys();
	}
	
	/**
	 * Selectionne un nouveau language à partir de son nom
	 * @param name Le nom du language
	 */
	public static void select(String name) {
		selected.set(languages.get(name));
	}
	
	public static void select(Language lang) {
		selected.set(lang);
	}
	
	/**
	 * Créer un binding pour une clé de traduction
	 * @param key la clé de traduction
	 * @return le binding
	 */
	public static StringBinding createBinding(String key) {
		return Bindings.createStringBinding(()->get(key), selected);
	}
	
	
}

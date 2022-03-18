package interfaceCommon.addon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import engine.card.CardContainers;
import engine.card.container.CardContainer;
import engine.card.container.CardPile;
import engine.game.KPDPControler;
import engine.influence.InfluenceCard;
import engine.objective.ObjectiveCard;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AddonManager {
	public static Map<String, CardPile<InfluenceCard>> influence_stockpiles = new HashMap<>();
	public static Map<String, CardPile<ObjectiveCard>> objective_stockpiles = new HashMap<>();
	
	static {
		influence_stockpiles.put("Default", CardContainers.DEFAULT_STOCKPILE);
		influence_stockpiles.put("Alpha", CardContainers.ALPHA_STOCKPILE);
		influence_stockpiles.put("Added", CardContainers.ADDED_STOCKPILE);
		
		objective_stockpiles.put("Default", CardContainers.DEFAULT_OBJECTIVES);
		objective_stockpiles.put("High", CardContainers.HIGH_OBJECTIVES);
		objective_stockpiles.put("Triangle", CardContainers.TRIANGLE_OBJECTIVES);
		objective_stockpiles.put("More", CardContainers.MORE_OBJECTIVES);
	}
	
	public static Map<String, CardPile<InfluenceCard>> getInfluenceStockpiles() {
		return influence_stockpiles;
	}
	
	public static Map<String, CardPile<ObjectiveCard>> getObjectiveStockpiles() {
		return objective_stockpiles;
	}
	
	public static void main(String[] args) {
		File file=new File("testsave.txt");
		try {
			loadCC(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(influence_stockpiles);
	}
	
	public static void askToLoadAddon() {
		
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new ExtensionFilter("KPDP Addons", "*.kpdp"));
		File file = fc.showOpenDialog(null);
		if (file!=null) {
		    try {
				loadCC(new FileInputStream(file));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void saveAllTo(File file) {
		try {
			FileOutputStream f=new FileOutputStream(file);
			for( Map.Entry<String, CardPile<InfluenceCard>> e : influence_stockpiles.entrySet()) {
				saveIC(f, e.getKey(), e.getValue());
			}
			
			for( Map.Entry<String, CardPile<ObjectiveCard>> e : objective_stockpiles.entrySet()) {
				saveOC(f, e.getKey(), e.getValue());
			}
			f.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Ecrit dans le fichier le conteneur de carte influence
	 * 
	 * @param o
	 * @param cont
	 * @throws IOException 
	 */
	private static void saveIC(OutputStream o, String name, CardContainer<InfluenceCard> cont) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(o, "utf8"));
		
		StringBuilder sb = new StringBuilder();
		sb.append("i;").append(name);
		for (InfluenceCard ic : cont)
			sb.append(";").append(KPDPControler.CARDTYPE_REGISTRY.getId(ic.getType()));
		sb.append("\n");
		writer.write(sb.toString());
		writer.flush();
	}
	/**
	 * Charge un conteneur de carte depuis le fichier.
	 * 
	 * @param o
	 * @param cont
	 * @throws IOException 
	 */
	private static void loadCC(InputStream i) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(i, "utf8"));
		String line=null;
		while((line=reader.readLine())!=null) {
			String[] words=line.split(";");
			if(words[0].equals("i")) {
				CardPile<InfluenceCard> ret=new CardPile<>();
				for(int y=2; y<words.length; y++) {
					ret.add(new InfluenceCard(KPDPControler.CARDTYPE_REGISTRY.get(words[y]), null));
				}
				influence_stockpiles.put(words[1], ret);
			}
			else if(words[0].equals("o")){
				CardPile<ObjectiveCard> ret=new CardPile<>();
				for(int y=2; y<words.length; y++) {
					String[] parts=words[y].split(",");
					ret.add(new ObjectiveCard(Integer.valueOf(parts[0]), KPDPControler.DOMAIN_REGISTRY.get(parts[1])));
				}
				objective_stockpiles.put(words[1], ret);
			}
		}
		
	}

	/**
	 * Ecrit dans le fichier le conteneur de carte objectif
	 * 
	 * @param o
	 * @param cont
	 */
	private static void saveOC(OutputStream o, String name, CardContainer<ObjectiveCard> cont) {
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(o, "utf8"));

			StringBuilder sb = new StringBuilder();
			sb.append("o;").append(name);
			for (ObjectiveCard oc : cont)
				sb.append(";").append(KPDPControler.DOMAIN_REGISTRY.getId(oc.getDomain())).append(",")
						.append(Integer.toString(oc.getObjective()));
			writer.write(sb.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

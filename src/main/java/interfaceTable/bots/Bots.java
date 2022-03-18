package interfaceTable.bots;

import java.io.InputStream;
import engine.teams.controler.PrototypeTeamControler;
import jempasam.swj.prototype.HashPrototypeManager;
import jempasam.swj.prototype.PrototypeManager;
import jempasam.swj.prototype.Prototypes;
import jempasam.swj.prototype.loader.PrototypeLoader;
import jempasam.swj.resources.ResourceManager;

public class Bots {
	public static final PrototypeManager<PrototypeTeamControler> factory=new HashPrototypeManager<>();
	static {
		System.out.println("LOAD BOTS");
		for(String botfilename : ResourceManager.listInDir("bots")) {
				System.out.println("-Load bots in "+botfilename);
				PrototypeLoader<PrototypeTeamControler> teamcontroler=Prototypes.createLoader(factory, PrototypeTeamControler.class, "engine.teams.controler.", "strobjo");
				InputStream botfile=Bots.class.getResourceAsStream("/bots/"+botfilename);
				teamcontroler.loadFrom(botfile);
		}
		System.out.println("END LOAD BOTS");
	}
}

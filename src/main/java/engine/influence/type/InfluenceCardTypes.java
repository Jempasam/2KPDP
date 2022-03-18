package engine.influence.type;

import engine.game.KPDPControler;
import engine.influence.type.added.AllHidedICT;
import engine.influence.type.added.DiscoverICT;
import engine.influence.type.added.HiddenICT;
import engine.influence.type.added.TimeBoostICT;
import engine.objective.Domain;

/**
 * Contient les instances de tout les types de carte influence du jeu de base.
 * @author Samuel Demont
 *
 */
public class InfluenceCardTypes {
	public static InfluenceCardType register(String id,InfluenceCardType newtype) {
		try{
			KPDPControler.CARDTYPE_REGISTRY.register(id, newtype);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return newtype;
	}
	public static InfluenceCardType ROI=register("Ro", new InfluenceCardType(20f,0,false));
	public static InfluenceCardType REINE=register("Re", new InfluenceCardType(16f,0,false));
	public static InfluenceCardType JULIETTE=register("Ju", new InfluenceCardType(14f,0,false));
	public static InfluenceCardType ECUYER=register("Ec", new CombineWinICT(2f,null));
	
	public static InfluenceCardType ROMEO=register("Rm", new CombineICT(5f, JULIETTE, 10f));
	
	public static InfluenceCardType ASSASSIN=register("As", new AssassinICT(9.5f));
	public static InfluenceCardType TEMPETE=register("Te", new TempeteICT(9f));
	public static InfluenceCardType EXPLORATEUR=register("Ex", new ExplorateurICT(13f));
	public static InfluenceCardType SOSIE=register("So", new SosieICT(0));
	public static InfluenceCardType DRAGON=register("Dr", new DragonICT(11,2));
	public static InfluenceCardType SORCIERE=register("Sr", new SorciereICT(1,9));
	public static InfluenceCardType PRINCE=register("Pr", new CombineWinICT(14,ECUYER));
	
	public static InfluenceCardType MAGICIEN=register("Mg", new MagicienICT(7f,10f));
	public static InfluenceCardType MOUSQUETAIRE=register("Tm", new RemoveEffectICT(11f));
	public static InfluenceCardType CAPE=register("Ci", new CapeICT(0f));
	public static InfluenceCardType TRAITRE=register("Tr", new MoveDomainICT(10f));
	public static InfluenceCardType MENDIANT=register("Me", new MendiantICT(4f));
	
	public static InfluenceCardType PETIT_GEANT=register("Pg", new ColumnSizeICT(2,3));
	public static InfluenceCardType ERMITE=register("Er", new ColumnSizeICT(12,-1));
	
	public static InfluenceCardType CARDINAL=register("Ca", new DomainBoostICT(Domain.RELIGION,8f,4f));
	public static InfluenceCardType MARCHAND=register("Ma", new DomainBoostICT(Domain.COMMERCE,8f,4f));
	public static InfluenceCardType TROUBADOUR=register("Tb", new DomainBoostICT(Domain.MUSIQUE,8f,4f));
	public static InfluenceCardType SEIGNEUR=register("Se", new DomainBoostICT(Domain.AGRICULTURE,8f,4f));
	public static InfluenceCardType MAITRE_DARME=register("Md", new DomainBoostICT(Domain.COMBAT,8f,4f));
	public static InfluenceCardType ALCHIMISTE=register("Al", new DomainBoostICT(Domain.ALCHIMIE,8f,4f));
	
	//ADDED CARD TYPES
	public static InfluenceCardType ECLAIREUR=register("ad.Ec", new DiscoverICT(8, 2));
	public static InfluenceCardType VOLEUR=register("ad.Vo", new HiddenICT(16, 10));
	public static InfluenceCardType SAUVAGE=register("ad.Sa", new ColumnSizeICT(20, -3));
	public static InfluenceCardType AVERSE=register("ad.Av", new AllHidedICT(10));
	public static InfluenceCardType ENFANT_GEANT=register("ad.Eg", new TimeBoostICT(2,0.2f));
	
}

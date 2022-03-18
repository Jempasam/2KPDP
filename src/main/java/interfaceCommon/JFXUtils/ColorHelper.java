package interfaceCommon.JFXUtils;



import engine.field.ColumnStat;
import engine.game.KPDPControler;
import engine.influence.InfluenceStat;
import engine.objective.Domain;
import engine.objective.ObjectiveCard;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;

/**
 * Permet différentes opérations en rapport avec les couleurs.
 * @author Samuel Demont
 *
 */
public class ColorHelper {
	public static Color colorOfDomain(Domain domain) {
		int hue=KPDPControler.DOMAIN_REGISTRY.getId(domain).hashCode()%360;
		return Color.hsb(hue, 0.5, 1);
	}
	public static Color colorOfObjectiveCard(ObjectiveCard oc) {
		return colorOfDomain(oc.getDomain());
	}
	public static Color colorOfTeam(int team_number) {
		return team_number<0 ? Color.WHITE : colorOfInt(team_number);
	}
	
	public static ColorAdjust whiteAdjustToColor(Color color) {
		ColorAdjust adjust=new ColorAdjust();
		adjust.setBrightness(color.getBrightness()-1.0);
		adjust.setSaturation(color.getSaturation());
		
		if(color.getHue()<180)adjust.setHue(color.getHue()/180.0);
		else adjust.setHue((color.getHue()-180)/180.0-1.0);
		
		return adjust;
	}
	
	public static Color colorOfInt(int i) {
		return Color.hsb(i*45%360, 0.5+i%10/25f, 1);
	}
	public static Color colorOfInfluenceState(InfluenceStat stat) {
		switch(stat) {
			case ACTIVE: return Color.TRANSPARENT;
			case ACTIVATED: return Color.LIGHTGREEN;
			case CANCELED: return Color.ORANGE;
			case ELIMINATED: return Color.RED;
			case INACTIVE: return Color.GRAY;
			default: return colorOfInt(stat.ordinal());
		}
	}
	public static Color colorOfColumnState(ColumnStat col) {
		switch(col) {
			case CLOSED: return Color.RED;
			case FINISHED: return Color.AQUA;
			case OPENED: return Color.LIGHTGRAY;
			default: return colorOfInt(col.ordinal());
		}
	}
}

package engine.game.parameters;

import engine.calculator.Calculators;
import engine.calculator.Referee;

public class ResolutionParameters {
	 /**
     * L'arbitre qui décide du contage des points et du gagnant
     * Par défaut=L'arbitre par défaut qui correspond aux règles de base
     */
    public Referee referee;
    
    /**
     * Remplie les valeurs non-remplies avec leur valeur par défaut.
     */
    public void fill() {
    	if(referee==null)referee=Calculators.REFEREE_BASE;
    }
    
    public ResolutionParameters() {
	}
    
    public ResolutionParameters(ResolutionParameters copied) {
    	referee=new Referee(copied.referee);
    }
}

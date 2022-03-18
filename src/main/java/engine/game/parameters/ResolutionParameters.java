package engine.game.parameters;

import engine.calculator.Calculators;
import engine.calculator.Referee;

public class ResolutionParameters {
	 /**
     * L'arbitre qui d�cide du contage des points et du gagnant
     * Par d�faut=L'arbitre par d�faut qui correspond aux r�gles de base
     */
    public Referee referee;
    
    /**
     * Remplie les valeurs non-remplies avec leur valeur par d�faut.
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

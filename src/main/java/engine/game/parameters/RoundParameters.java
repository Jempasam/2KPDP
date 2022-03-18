package engine.game.parameters;

public class RoundParameters {
	
	/**
	 * Les param�tres de r�solution
	 */
	public ResolutionParameters resolution;
	
	 /**
     * La taille de la main des �quipes
     * Par d�faut=3
     */
    public Integer hand_size;
    
    /**
     * Remplie les valeurs non-remplies avec leur valeur par d�faut.
     */
    public void fill() {
    	resolution.fill();
    	if(hand_size==null)hand_size=3;
    }
    
	public RoundParameters() {
		resolution=new ResolutionParameters();
	}
	
	public RoundParameters(RoundParameters copied) {
		resolution=new ResolutionParameters(copied.resolution);
		hand_size=copied.hand_size;
	}
}

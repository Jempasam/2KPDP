package engine.influence;


import engine.field.Column;
import engine.field.Field;
import engine.game.Game;
import engine.teams.Team;

/**
 * Permet d'obtenir le contexte d'activation de l'effet d'une carte.
 * @author Samuel Demont
 *
 */
public class EffectContext {
    private int nb_column;
    private int nb_line;
    private Game game;
    
    public EffectContext(Game game, int nb_column, int nb_line) {
    	this.game=game;
    	this.nb_column=nb_column;
    	this.nb_line=nb_line;
    }
    
    public EffectContext(EffectContext cloned) {
    	this(cloned.game,cloned.nb_column,cloned.nb_line);
    }
    
    public int getColumnNb() { return this.nb_column; }
        
    public int getLineNb() { return this.nb_line; }
    
    public Game getGame() { return this.game; }
    
    public InfluenceCard getCard() { return game.getField().get(nb_column).get(nb_line); }
    
    public Team getTeam() { return getCard().getTeam(); }
    
    public Column getColumn() { return game.getField().get(nb_column); }
    
    public Field getField() { return game.getField(); }
    
    public InfluenceCard getCardBeneath() {
    	Column c=getColumn();
    	if(c.size()>nb_line+1)return c.get(nb_line+1);
    	else return null;
    }
    
    public InfluenceCard getCardAbove() {
    	Column c=getColumn();
    	if(nb_line>0)return c.get(nb_line-1);
    	else return null;
    }
    
}

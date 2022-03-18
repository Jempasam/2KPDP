package engine.game;

import engine.events.EventRegistry;
import engine.game.parameters.GameParameters;
import engine.influence.type.InfluenceCardType;
import engine.kpdpevents.InfluenceEffectEvent;
import engine.kpdpevents.controlerevent.ControlerEvent;
import engine.kpdpevents.gameevent.GameEvent;
import engine.objective.Domain;
import jempasam.swj.registry.Registry;

/**
 * La classe statique principale du jeu 2KPDP.
 * @author Samuel Demont
 *
 */
public class KPDPControler {
    /**
     * Le registre des événments pour les effets de carte influence.
     */
    public static EventRegistry<InfluenceEffectEvent> INFLUENCE_EVENTS=new EventRegistry<>();
    
    /**
     * Le registre des événements de jeu divers
     */
     public static EventRegistry<GameEvent> GAME_EVENTS=new EventRegistry<>();
     
     /**
      * Le registre qui permet de récupérer les cartes et colonnes à jouer
      */
     public static EventRegistry<ControlerEvent> CONTROLER_EVENTS=new EventRegistry<>();
     
    
    /**
     * Le registre des domaines
     */
    public static Registry<Domain> DOMAIN_REGISTRY=new Registry<>();
    
    /**
     * Le registre des types de carte influence
     */
    public static Registry<InfluenceCardType> CARDTYPE_REGISTRY=new Registry<>();
    
    /**
     * Crée une partie
     * @param parmams les paramètres de la partie
     * @return Le controleur de partie qui permet de controler la nouvelle partie
     */
    public static GameControler startGame(GameParameters parmams) {
        return new GameControler(new Game(parmams));
    }
    
}

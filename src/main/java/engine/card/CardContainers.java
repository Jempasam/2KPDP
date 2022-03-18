package engine.card;

import engine.card.container.CardPile;
import engine.influence.InfluenceCard;
import engine.influence.type.InfluenceCardTypes;
import engine.objective.Domain;
import engine.objective.ObjectiveCard;

/**
 * Contient des instances statiques de conteneur de cartes.
 * @author Samuel Demont
 *
 */
public class CardContainers {
	/**
	 * La pioche de carte influence par défaut avec toutes les cartes du jeu de base en 1 exemplaire.
	 */
	public static CardPile<InfluenceCard> DEFAULT_STOCKPILE=generateDefaultStockpile();
	
	/**
	 * La pioche de carte influence avec les premières cartes implémentées.
	 */
	public static CardPile<InfluenceCard> ALPHA_STOCKPILE=generateAlphaStockpile();
	
	/**
	 * La pioche de carte influence avec des cartes en plus de celles du jeu de base
	 */
	public static CardPile<InfluenceCard> ADDED_STOCKPILE=generateAddedStockpile();
	
	/**
	 * La pioche de carte objectif avec pour chaque domaines du jeu de base des cartes objectif avec des objectif de 1 à 5 et sans doublon.
	 */
	public static CardPile<ObjectiveCard> DEFAULT_OBJECTIVES=generateDefaultObjectives(1,5);
	
	/**
	 * La pioche de carte objectif avec pour chaque domaines du jeu de base des cartes objectif avec des objectif de 1 à 10 et sans doublon.
	 */
	public static CardPile<ObjectiveCard> HIGH_OBJECTIVES=generateDefaultObjectives(1,10);
	
	/**
	 * La pioche de carte objectif avec pour 3 domaines des cartes objectif avec des objectif de 1 à 6 et sans doublon.
	 */
	public static CardPile<ObjectiveCard> TRIANGLE_OBJECTIVES=generateSelectedObjectives(1,6, new Domain[] {Domain.AGRICULTURE, Domain.RELIGION, Domain.COMBAT});
	
	/**
	 * La pioche de carte objectif avec pour 9 domaines des cartes objectif avec des objectif de 1 à 6 et sans doublon.
	 */
	public static CardPile<ObjectiveCard> MORE_OBJECTIVES=generateSelectedObjectives(1,6, new Domain[] {Domain.AGRICULTURE, Domain.RELIGION, Domain.COMBAT, Domain.ALCHIMIE, Domain.CHASSE, Domain.COMMERCE, Domain.DIVERTISSEMENT, Domain.MUSIQUE, Domain.MAL});
	
	/**
	 * Crée la pioche de carte objectif avec, pour chaque domaines du jeu de base, des cartes objectif avec des objectif de min_objectif à max_objectif et sans doublon.
	 * @param min_objective L'objectif minimal
	 * @param max_objective L'objectif maximal
	 * @return La pioche créée
	 */
	private static CardPile<ObjectiveCard> generateDefaultObjectives(int min_objective,int max_objective) {
		CardPile<ObjectiveCard> df_obj=new CardPile<>();
		for(int i=min_objective; i<=max_objective; i++) {
			df_obj.add(new ObjectiveCard(i, Domain.AGRICULTURE));
			df_obj.add(new ObjectiveCard(i, Domain.ALCHIMIE));
			df_obj.add(new ObjectiveCard(i, Domain.COMBAT));
			df_obj.add(new ObjectiveCard(i, Domain.COMMERCE));
			df_obj.add(new ObjectiveCard(i, Domain.MUSIQUE));
			df_obj.add(new ObjectiveCard(i, Domain.RELIGION));
		}
		return df_obj;
	}
	
	/**
	 * Crée la pioche de carte objectif avec, pour chaque domaines d'une liste, des cartes objectif avec des objectif de min_objectif à max_objectif et sans doublon.
	 * @param min_objective L'objectif minimal
	 * @param max_objective L'objectif maximal
	 * @return La pioche créée
	 */
	private static CardPile<ObjectiveCard> generateSelectedObjectives(int min_objective,int max_objective, Domain[] domains) {
		CardPile<ObjectiveCard> df_obj=new CardPile<>();
		for(int i=min_objective; i<=max_objective; i++) {
			for(Domain d : domains) df_obj.add(new ObjectiveCard(i, d));
		}
		return df_obj;
	}
	
	
	/**
	 * Crée la pioche de carte influence du jeu de base
	 * @return La pioche créée
	 */
	private static CardPile<InfluenceCard> generateDefaultStockpile() {
		CardPile<InfluenceCard> df=new CardPile<>();
		
		//DOMAIN BOOST CARDS
		// Cartes dont les points dépendent du domaine
		df.add(new InfluenceCard(InfluenceCardTypes.CARDINAL, null));
		df.add(new InfluenceCard(InfluenceCardTypes.ALCHIMISTE, null));
		df.add(new InfluenceCard(InfluenceCardTypes.MARCHAND, null));
		df.add(new InfluenceCard(InfluenceCardTypes.MAITRE_DARME, null));
		df.add(new InfluenceCard(InfluenceCardTypes.TROUBADOUR, null));
		df.add(new InfluenceCard(InfluenceCardTypes.SEIGNEUR, null));
		
		//KILLING CARDS
		// Cartes qui peuvent détruire d'autres cartes
		df.add(new InfluenceCard(InfluenceCardTypes.DRAGON, null));
		df.add(new InfluenceCard(InfluenceCardTypes.SORCIERE, null));
		df.add(new InfluenceCard(InfluenceCardTypes.MAGICIEN, null));
		df.add(new InfluenceCard(InfluenceCardTypes.ASSASSIN, null));
		
		//COMBO CARDS
		// Cartes dont les points dépendent d'autres cartes du plateau
		df.add(new InfluenceCard(InfluenceCardTypes.ECUYER, null));
		df.add(new InfluenceCard(InfluenceCardTypes.PRINCE, null));
		
		df.add(new InfluenceCard(InfluenceCardTypes.ROMEO, null));
		df.add(new InfluenceCard(InfluenceCardTypes.JULIETTE, null));
		
		df.add(new InfluenceCard(InfluenceCardTypes.PETIT_GEANT, null));
		df.add(new InfluenceCard(InfluenceCardTypes.ERMITE, null));
		
		//OTHER CARDS
		df.add(new InfluenceCard(InfluenceCardTypes.EXPLORATEUR, null));
		df.add(new InfluenceCard(InfluenceCardTypes.SOSIE, null));
		df.add(new InfluenceCard(InfluenceCardTypes.TEMPETE, null));
		df.add(new InfluenceCard(InfluenceCardTypes.MOUSQUETAIRE, null));
		df.add(new InfluenceCard(InfluenceCardTypes.CAPE, null));
		df.add(new InfluenceCard(InfluenceCardTypes.TRAITRE, null));
		df.add(new InfluenceCard(InfluenceCardTypes.MENDIANT, null));
		
		//NO EFFECT CARDS
		df.add(new InfluenceCard(InfluenceCardTypes.ROI, null));
		df.add(new InfluenceCard(InfluenceCardTypes.REINE, null));
		
		return df;
	}
	
	/**
	 * Crée une pioche de carte influence basique contenant uniquement les cartes dont les effets ont été implémentés en premier
	 * @return La pioche créée
	 */
	private static CardPile<InfluenceCard> generateAlphaStockpile() {
		CardPile<InfluenceCard> df=new CardPile<>();
		
		//DOMAIN BOOST CARDS
		// Cartes dont les points dépendent du domaine
		df.add(new InfluenceCard(InfluenceCardTypes.CARDINAL, null));
		df.add(new InfluenceCard(InfluenceCardTypes.ALCHIMISTE, null));
		df.add(new InfluenceCard(InfluenceCardTypes.MARCHAND, null));
		df.add(new InfluenceCard(InfluenceCardTypes.MAITRE_DARME, null));
		df.add(new InfluenceCard(InfluenceCardTypes.TROUBADOUR, null));
		df.add(new InfluenceCard(InfluenceCardTypes.SEIGNEUR, null));
		
		//KILLING CARDS
		// Cartes qui peuvent détruire d'autres cartes
		df.add(new InfluenceCard(InfluenceCardTypes.DRAGON, null));
		df.add(new InfluenceCard(InfluenceCardTypes.SORCIERE, null));
		df.add(new InfluenceCard(InfluenceCardTypes.ASSASSIN, null));
		
		//COMBO CARDS
		// Cartes dont les points dépendent d'autres cartes du plateau
		df.add(new InfluenceCard(InfluenceCardTypes.ROMEO, null));
		df.add(new InfluenceCard(InfluenceCardTypes.JULIETTE, null));
		
		df.add(new InfluenceCard(InfluenceCardTypes.PETIT_GEANT, null));
		df.add(new InfluenceCard(InfluenceCardTypes.ERMITE, null));
		
		//OTHER CARDS
		df.add(new InfluenceCard(InfluenceCardTypes.EXPLORATEUR, null));
		df.add(new InfluenceCard(InfluenceCardTypes.SOSIE, null));
		df.add(new InfluenceCard(InfluenceCardTypes.TEMPETE, null));		
		//NO EFFECT CARDS
		df.add(new InfluenceCard(InfluenceCardTypes.ROI, null));
		df.add(new InfluenceCard(InfluenceCardTypes.REINE, null));
		
		return df;
	}
	/**
	 * Crée une pioche de carte influence basique contenant uniquement les cartes dont les effets ont été implémentés en premier
	 * @return La pioche créée
	 */
	private static CardPile<InfluenceCard> generateAddedStockpile() {
		CardPile<InfluenceCard> df=new CardPile<>(DEFAULT_STOCKPILE);
		df.add(new InfluenceCard(InfluenceCardTypes.AVERSE, null));
		df.add(new InfluenceCard(InfluenceCardTypes.ECLAIREUR, null));
		df.add(new InfluenceCard(InfluenceCardTypes.ENFANT_GEANT, null));
		df.add(new InfluenceCard(InfluenceCardTypes.SAUVAGE, null));
		df.add(new InfluenceCard(InfluenceCardTypes.VOLEUR, null));
		return df;
	}
}

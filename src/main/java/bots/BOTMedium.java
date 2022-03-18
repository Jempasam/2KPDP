package bots;


import engine.field.ColumnViewer;
import engine.game.GameViewer;
import engine.influence.type.InfluenceCardType;
import engine.influence.type.InfluenceCardTypes;
import engine.influence.viewer.InfluenceCardViewer;
import engine.objective.Domain;
import engine.teams.TeamViewer;
import engine.teams.controler.PrototypeTeamControler;
import engine.teams.controler.ScoreListAIControler;
import engine.teams.controler.TeamControler;

public class BOTMedium extends ScoreListAIControler implements PrototypeTeamControler {

	public boolean inColone(GameViewer game, InfluenceCardType cardType, ColumnViewer Column) {
		for (int i = 0; i < Column.size(); i++) {
			if (Column.get(i).getType() == cardType) {
				return true;
			}
		}
		return false;
	}

	public boolean inColoneAllied(GameViewer game, InfluenceCardType cardType, ColumnViewer Column, TeamViewer T) {
		for (int i = 0; i < Column.size(); i++) {
			if (Column.get(i).getType() == cardType && T.viewSame(Column.get(i).getTeam())) {
				return true;
			}
		}
		return false;
	}

	public boolean inColoneEnnemy(GameViewer game, InfluenceCardType cardType, ColumnViewer Column, TeamViewer T) {
		for (int i = 0; i < Column.size(); i++) {
			if (Column.get(i).getType() == cardType && !T.viewSame(Column.get(i).getTeam())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int calculateScoreOf(InfluenceCardViewer Card, ColumnViewer Column, TeamViewer team, GameViewer game) {
		int score = 0;
		int BestTemp;
		int Best;
		score += Card.getPoints() * 10;

		int capture_indice = 0;
		int player_by_column = game.getTeams().size();
		capture_indice -= Column.sum((card) -> card.getTeam().viewSame(Card.getTeam()) ? 0 : (int) (card.getPoints()))
				/ player_by_column;
		capture_indice += Column.sum((card) -> !card.getTeam().viewSame(Card.getTeam()) ? 0 : (int) (card.getPoints()));
		Boolean MagicAlready;

		score += Column.getObjectiveCard().getObjective() * 10;
		score += capture_indice;

		// ------------------ Verification qu'il ny a ni tempete, ni mendiant, ni
		// ASSASSIN grace au HACKED
		if (!inColone(game, InfluenceCardTypes.TEMPETE, Column) && !inColone(game, InfluenceCardTypes.MENDIANT, Column)
				&& Column.size()>0 && Column.last().getType() != InfluenceCardTypes.ASSASSIN) {
		} else {
			score -= 9999;
		}

		// Domaine = à la carte
		if (Card.getType() == InfluenceCardTypes.CARDINAL && Column.getDomain() == Domain.RELIGION
				|| Card.getType() == InfluenceCardTypes.MARCHAND && Column.getDomain() == Domain.COMMERCE
				|| Card.getType() == InfluenceCardTypes.TROUBADOUR && Column.getDomain() == Domain.MUSIQUE
				|| Card.getType() == InfluenceCardTypes.SEIGNEUR && Column.getDomain() == Domain.AGRICULTURE
				|| Card.getType() == InfluenceCardTypes.MAITRE_DARME && Column.getDomain() == Domain.COMBAT
				|| Card.getType() == InfluenceCardTypes.ALCHIMISTE && Column.getDomain() == Domain.ALCHIMIE) {
			score += 40;
		}

		// TEMPETE et +10 d'avance
		if (Card.getType() == InfluenceCardTypes.TEMPETE) {
			score += 15 * capture_indice;
		}

		// Traitre dans une colonne controlée >2
		if (Card.getType() == InfluenceCardTypes.TRAITRE && Column.getObjectiveCard().getObjective() <= 2
				&& capture_indice > 10 && Column.getObjectiveCard().getObjective() > Column.size()) {
			score += 120;
		} else {
			score -= 120;
		}

		// ECUYER et PRINCE
		if (Card.getType() == InfluenceCardTypes.ECUYER
				&& inColoneAllied(game, InfluenceCardTypes.PRINCE, Column, team)) {
			score += 12000;
		}
		if (Card.getType() == InfluenceCardTypes.PRINCE
				&& inColoneAllied(game, InfluenceCardTypes.ECUYER, Column, team)) {
			score += 12000;
		}

		// ASSASSIN si 2 places restantes sur la colonne
		if (Card.getType() == InfluenceCardTypes.ASSASSIN) {
			if (Column.getObjectiveCard().getObjective() - Column.size() > 1)
				score += 100;
		}

		// CAPE si 2 places restantes sur la colonne
		if (Card.getType() == InfluenceCardTypes.CAPE) {
			if (Column.getObjectiveCard().getObjective() - Column.size() > 1)
				score += 100;
		}

		// EFFETS annulés par mousquetaire
		if (!inColone(game, InfluenceCardTypes.MOUSQUETAIRE, Column)) {

			// DRAGON dans colonne ou il y a mini 4 cartes
			if (Card.getType() == InfluenceCardTypes.DRAGON) {
				score += Column.sum((card) -> !card.getTeam().viewedsEquals(team) ? 20 : 0);
			}

			// ROMEO + JULIETTE
			if (Card.getType() == InfluenceCardTypes.ROMEO
					&& inColoneAllied(game, InfluenceCardTypes.JULIETTE, Column, team)) {
				score += 100;
			}
			if (Card.getType() == InfluenceCardTypes.JULIETTE
					&& inColoneAllied(game, InfluenceCardTypes.ROMEO, Column, team)) {
				score += 100;
			}

			// Magicien selon le gain
			if (Card.getType() == InfluenceCardTypes.MAGICIEN && !inColone(game, InfluenceCardTypes.MAGICIEN, Column)) {
				score += Column.sum((card) -> !card.getTeam().viewedsEquals(team) && card.getPoints() >= 10
						? (int) (card.getPoints() * 10)
						: 0);
				score -= Column.sum((card) -> card.getTeam().viewedsEquals(team) && card.getPoints() >= 10
						? (int) (card.getPoints() * 10)
						: 0);
			}

			// SORCIERE selon le gain
			if (Card.getType() == InfluenceCardTypes.SORCIERE && !inColone(game, InfluenceCardTypes.SORCIERE, Column)) {
				score += Column.sum((card) -> !card.getTeam().viewedsEquals(team) && card.getPoints() < 10
						? (int) (card.getPoints() * 10)
						: 0);
				score -= Column.sum((card) -> card.getTeam().viewedsEquals(team) && card.getPoints() < 10
						? (int) (card.getPoints() * 10)
						: 0);
			}

			// Gain du petit géant selon le nombre de cartes
			if (Card.getType() == InfluenceCardTypes.PETIT_GEANT) {
				score += Column.size() * 30;
				if (Column.getObjectiveCard().getObjective() - Column.size() > 0)
					score += (Column.getObjectiveCard().getObjective() - Column.size()) * 30;
			}

			// Perte de l'ermite selon le nombre de cartes
			if (Card.getType() == InfluenceCardTypes.ERMITE) {
				score -= Column.size() * 10;
				if (Column.getObjectiveCard().getObjective() - Column.size() > 0)
					score -= (Column.getObjectiveCard().getObjective() - Column.size()) * 10;
			}

			// Perte de score mendiant selon l'avance
			if (Card.getType() == InfluenceCardTypes.MENDIANT) {
				score -= 15 * capture_indice;
			}
		}

		return score;
	}
	

	@Override
	public String askForCommentary(TeamViewer team, GameViewer game, String reason, int try_counter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PrototypeTeamControler clone() {
		return new BOTMedium();
	}

}

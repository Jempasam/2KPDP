package engine.kpdpevents.gameevent;

import engine.game.Game;
import engine.influence.viewer.InfluenceCardViewer;
import engine.teams.TeamViewer;

public class GiveInfluenceCardEvent extends  GameEvent {
	
	
	private TeamViewer givedto;
	private InfluenceCardViewer gived;
	private int newhandsize;
	private int oldhandsize;
	
	
	public GiveInfluenceCardEvent(Game game, TeamViewer givedto, InfluenceCardViewer gived, int newhandsize,
			int oldhandsize) {
		super(game);
		this.givedto = givedto;
		this.gived = gived;
		this.newhandsize = newhandsize;
		this.oldhandsize = oldhandsize;
	}

	public TeamViewer getReciever() {
		return givedto;
	}

	public InfluenceCardViewer getCardGiven() {
		return gived;
	}
	public int getHandSize() {
		return newhandsize;
	}

	public int getOldHandSize() {
		return oldhandsize;
	}
}

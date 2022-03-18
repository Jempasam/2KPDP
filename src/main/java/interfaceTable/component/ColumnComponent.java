package interfaceTable.component;

import engine.field.ColumnStat;
import engine.field.ColumnViewer;
import engine.game.GameViewer;
import interfaceCommon.JFXUtils.ColorHelper;
import interfaceCommon.component.ComponentUtil;
import interfaceCommon.component.OverlapVBox;
import interfaceCommon.component.ResizableImageView;
import interfaceCommon.component.SmallLabel;
import interfaceCommon.image.ImageManager;
import interfaceTable.component.kpdp.TableInfluenceCardComponent;
import interfaceTable.component.kpdp.TableObjectiveCardComponent;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class ColumnComponent extends StackPane{
	
	public TableObjectiveCardComponent objectivecard;
	public TableInfluenceCardComponent[] influencecards;
	
	public ResizableImageView ivOverlay;
	private OverlapVBox cards;
	
	private ColumnViewer column;
	private GameViewer game;
	private ColumnStat state;
	
	public ColumnComponent(ColumnViewer col, GameViewer gam) {
		super();
		
		column=col;
		game=gam;
		influencecards=new TableInfluenceCardComponent[0];
		
		state=null;
		
		ResizableImageView image=new ResizableImageView(ImageManager.getImage("misc", "column"));
		image.setMinSize(0);
		image.setPreserveRatio(false);
		getChildren().add(image);
		
		ivOverlay=new ResizableImageView(ImageManager.getImage("misc", "column2"));
		ivOverlay.setMinSize(0);
		ivOverlay.setPreserveRatio(false);
		getChildren().add(ivOverlay);
		
		cards=new OverlapVBox();
		cards.setAlignment(Pos.TOP_CENTER);
		getChildren().add(cards);
		
		setAlignment(Pos.TOP_CENTER);
		
		update();
	}
	
	public void update() {
		int i;
		
		//Mise à jour des cartes influences
		TableInfluenceCardComponent[] new_influencecard=new TableInfluenceCardComponent[column.size()];
		for(i=0; i<column.size() && i<influencecards.length; i++) {
				if(column.get(i).viewSame(influencecards[i].getInfluenceCard()))
				{
					influencecards[i].update();
					new_influencecard[i]=influencecards[i];
				}
				else {
					new_influencecard[i]=new TableInfluenceCardComponent(column.get(i), game);
					new_influencecard[i].setMaxWidth(ComponentUtil.getBaseUnit()*4);
				}
		}
		
		for(; i<column.size(); i++) {
			new_influencecard[i]=new TableInfluenceCardComponent(column.get(i), game);
			new_influencecard[i].setMaxWidth(ComponentUtil.getBaseUnit()*4);
		}
		influencecards=new_influencecard;
		
		//Si la carte objectif a changé
		if(objectivecard==null || !objectivecard.getObjectiveCard().viewSame(column.getObjectiveCard())) {
			objectivecard=new TableObjectiveCardComponent(column.getObjectiveCard(), game);
			objectivecard.setMaxWidth(ComponentUtil.getBaseUnit()*4);
		}else {
			objectivecard.update();
		}
		
		//Reconstruction de la colonnes
		cards.getChildren().clear();
		cards.getChildren().add(new SmallLabel(Integer.toString(game.getField().indexOfViewing(column))));
		cards.getChildren().add(objectivecard);
		cards.getChildren().addAll(influencecards);
		
		//Si l'état change
		if(state!=column.getState()) {
			//Mise à jour de l'état
			
			ColumnStat new_state=column.getState();
			
			ivOverlay.setEffect(ColorHelper.whiteAdjustToColor(ColorHelper.colorOfColumnState(column.getState())));
			
			state=new_state;
		}
	}
}

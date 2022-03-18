package interfaceCommon.component;


import interfaceCommon.image.ImageManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class ActivableRollComponent extends Region {
	private StackPane contents;
	private BooleanProperty unrolled;
	
	private ImageView roller_top;
	private ImageView roller_bottom;
	private ImageView background;
	
	public ActivableRollComponent() {
		//Les morceau de papier enroulés
		Image roller=ImageManager.getImage("misc", "roller");
		roller_top=new ImageView(roller);
		roller_top.setPreserveRatio(true);
		roller_bottom=new ImageView(roller);
		roller_bottom.setPreserveRatio(true);
		
		//Le fond et la stackpane du contenu
		contents=new StackPane();
		background=new ImageView(ImageManager.getImage("misc", "bg_paper2"));
		
		getChildren().addAll(background, contents, roller_top, roller_bottom);
		unrolled=new SimpleBooleanProperty(true);
		
		roller_top.setOnMouseClicked((e)->{
			swapState();
		});
		roller_bottom.setOnMouseClicked((e)->{
			swapState();
		});
	}
	public void close() {
		if(unrolled.get()) {
			unrolled.set(false);
			contents.setVisible(false);
		}
		layoutChildren();
	}
	public boolean unrolled() {
		return unrolled.get();
	}
	public void open() {
		if(!unrolled.get()) {
			unrolled.set(true);
			contents.setVisible(true);
		}
		layoutChildren();
	}
	public void swapState() {
		if(unrolled.get()) {
			unrolled.set(false);
			contents.setVisible(false);
		}
		else {
			unrolled.set(true);
			contents.setVisible(true);
		}
		layoutChildren();
	}
	
	public BooleanProperty unrolledProperty() {
		return unrolled;
	}
	@Override
	protected double computeMinWidth(double height) {
		return contents.minWidth(height)/38*40;
	}
	@Override
	protected double computePrefWidth(double height) {
		return computeMinWidth(height);
	}
	
	@Override
	protected double computeMinHeight(double width) {
		if(unrolled.get()) {
			double minheight=contents.minHeight(width);
			minheight+=roller_bottom.getImage().getHeight()*width/roller_bottom.getImage().getWidth()*2;
			return minheight;
		}
		else return roller_bottom.getImage().getHeight()*width/roller_bottom.getImage().getWidth()*2;
	}
	
	@Override
	protected double computeMaxHeight(double width) {
		if(unrolled.get()) return super.computeMaxHeight(width);
		else return roller_bottom.getImage().getHeight()*getWidth()/roller_bottom.getImage().getWidth()*2;
	}
	
	@Override
	protected void layoutChildren() {
		super.layoutChildren();
		
		double roller_height=roller_bottom.getImage().getHeight()*getWidth()/roller_bottom.getImage().getWidth();
		
		roller_top.setFitWidth(getWidth());
		roller_top.setLayoutX(0);
		roller_top.setLayoutY(0);
		
		roller_bottom.setFitWidth(getWidth());
		roller_bottom.setLayoutY(getHeight()-roller_height);
		
		background.setFitHeight(getHeight()-roller_height);
		background.setFitWidth(getWidth()/40*38);
		background.setLayoutX(getWidth()/40);
		background.setLayoutY(roller_height/2);
		
		contents.setLayoutY(roller_height);
		contents.setLayoutX(getWidth()/40*2);
		contents.resize(getWidth()/40*36,getHeight()-roller_height*2);
	}
	
	public ObservableList<Node> getContents() {
		return contents.getChildren();
	}
}

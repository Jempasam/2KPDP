package interfaceCommon.transition;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ImageTransition extends Transition{
	
	ImageView image_view;
	Image image;
	
	
	public ImageTransition(ImageView image_view, Image image) {
		super();
		this.image_view = image_view;
		this.image = image;
		setCycleDuration(Duration.millis(1000));
	}


	@Override
	protected void interpolate(double frac) {
		if(frac<0.5){
			image_view.setOpacity(1.0-frac*2);
		}
		else{
			image_view.setImage(image);
			image_view.setOpacity((frac-0.5)*2);
		}
	}

}

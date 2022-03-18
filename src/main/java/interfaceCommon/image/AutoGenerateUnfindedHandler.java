package interfaceCommon.image;

import java.util.function.BiFunction;

import interfaceCommon.JFXUtils.ImageHelper;
import javafx.scene.image.Image;

public class AutoGenerateUnfindedHandler implements BiFunction<ImageBundle,String,Image>{
	
	AutoGenerateUnfindedHandler() {
	}
	@Override
	public Image apply(ImageBundle t, String u) {
		Image generated=ImageHelper.createModified(t.getImageOfInt(u.hashCode()),u.hashCode());
		t.addImage(u, generated);
		return generated;
	}
}

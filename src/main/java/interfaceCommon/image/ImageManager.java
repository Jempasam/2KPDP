package interfaceCommon.image;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import javafx.scene.image.Image;

/**
 * Les gestionnaire des textures charge les textures en en générant lui-même si nécessaire.
 * @author Samuel Demont
 *
 */
public class ImageManager {
	public static AutoGenerateUnfindedHandler AUTOGEN_UNDEFINED_HANDLER=new AutoGenerateUnfindedHandler();
	private static Map<String,ImageBundle> image_bundles;
	
	static {
		image_bundles=new HashMap<>();
		
		Class<?> clazz=ImageManager.class;
		InputStream image=clazz.getResourceAsStream("/imagemanager/index.txt");
		BufferedReader reader=new BufferedReader(new InputStreamReader(image));
		String line;
		try {
			while( (line=reader.readLine()) != null ) {
				image_bundles.put(line, new ImageBundle(line));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ImageBundle getImageBundle(String name) {
		return image_bundles.get(name);
	}
	
	public static void setUnfindedHandler(String bundle_name, BiFunction<ImageBundle,String,Image> unfinded_handler) {
		ImageBundle bundle=getImageBundle(bundle_name);
		if(bundle!=null)bundle.setUnfindedHandler(unfinded_handler);
	}
	
	public static Image getImage(String bundle_name, String image_name) {
		ImageBundle bundle=image_bundles.get(bundle_name);
		if(bundle==null)return null;
		else {
			return bundle.getImage(image_name);
		}
	}
}

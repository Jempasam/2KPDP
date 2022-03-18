package interfaceCommon.image;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;

import javafx.scene.image.Image;

public class ImageBundle {
	private Map<String,Image> images;
	private List<Image> images_as_list;
	private BiFunction<ImageBundle,String,Image> unfinded_handler;
	
	ImageBundle(String name) {
		images=new HashMap<>();
		images_as_list=new ArrayList<>();
		
		InputStream image=ImageManager.class.getResourceAsStream("/imagemanager/"+name+"/index.txt");
		BufferedReader reader=new BufferedReader(new InputStreamReader(image));
		String line;
		try {
			while( (line=reader.readLine()) != null ) {
				loadImage(name, line);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void loadImage(String bundle_name, String image_name) {
		InputStream stream=ImageManager.class.getResourceAsStream("/imagemanager/"+bundle_name+"/"+image_name);
		Image new_image=new Image(stream);
		images.put(image_name.substring(0, image_name.lastIndexOf('.')), new_image);
		images_as_list.add(new_image);
	}
	public void addImage(String id, Image image) {
		images.put(id, image);
	}
	public Image getImageOfInt(int i) {
		return images_as_list.get(Math.abs(i%images_as_list.size()));
	}
	
	public Image getRandomImage() {
		return images_as_list.get(new Random().nextInt(images_as_list.size()));
	}
	
	public void setUnfindedHandler(BiFunction<ImageBundle,String,Image> unfinded_handler) {
		this.unfinded_handler = unfinded_handler;
	}
	
	public Image getImage(String name) {
		if(name.equals("random"))return images_as_list.get((int)(images_as_list.size()*Math.random()));
		
		Image ret=images.get(name);
		if(ret==null) {
			return unfinded_handler==null ? null : unfinded_handler.apply(this,name);
		}else {
			return ret;
		}
	}
	
}

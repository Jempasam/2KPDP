package interfaceCommon.sound;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class SoundBundle {
	private Map<String,Media> sounds;
	private List<Media> sounds_as_list;
	private BiFunction<SoundBundle,String,Media> unfinded_handler;
	
	SoundBundle(String name) {
		sounds=new HashMap<>();
		sounds_as_list=new ArrayList<>();
		
		InputStream sound=SoundManager.class.getResourceAsStream("/sound/"+name+"/index.txt");
		BufferedReader reader=new BufferedReader(new InputStreamReader(sound));
		String line;
		try {
			while( (line=reader.readLine()) != null ) {
				loadSound(name, line);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void loadSound(String bundle_name, String sound_name) {
		Media new_sound=new Media(SoundBundle.class.getResource("/sound/"+bundle_name+"/"+sound_name).toString());;
		sounds.put(sound_name.substring(0, sound_name.lastIndexOf('.')), new_sound);
		sounds_as_list.add(new_sound);
	}
	public void addSound(String id, Media image) {
		sounds.put(id, image);
	}
	
	public Media getSound(String name) {
		if(name.equals("random"))return sounds_as_list.get((int)(sounds_as_list.size()*Math.random()));
		
		Media ret=sounds.get(name);
		return ret;
	}
	
}

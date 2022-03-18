package interfaceCommon.sound;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Les gestionnaire des sons charge les sons.
 * @author Samuel Demont
 *
 */
public class SoundManager {
	private static Map<String,SoundBundle> sound_bundles;
	private static Map<String,MediaPlayer> sound_tracks;
	private static Map<String,FloatProperty> volume;
	
	private SoundManager() {
		
	}
	
	static {
		sound_bundles=new HashMap<>();
		sound_tracks=new HashMap<>();
		volume=new HashMap<>();
		
		Class<?> clazz=SoundManager.class;
		InputStream image=clazz.getResourceAsStream("/sound/index.txt");
		BufferedReader reader=new BufferedReader(new InputStreamReader(image));
		String line;
		try {
			while( (line=reader.readLine()) != null ) {
				sound_bundles.put(line, new SoundBundle(line));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SoundBundle getSoundBundle(String name) {
		return sound_bundles.get(name);
	}
	
	public static void setVolume(String track,float volume) {
		FloatProperty v=SoundManager.volume.get(track);
		if(v==null) {
			v=new SimpleFloatProperty();
			SoundManager.volume.put(track, v);
		}
		v.set(volume);
	}
	
	public static void playSound(String bundle_name, String sound_name, String track_name, int repeat) {
			Media sound = getSound(bundle_name, sound_name);
			if(sound!=null)playOnTrack(sound, track_name, repeat);
	}
	private static void playOnTrack(Media media, String track_name, int repeat) {
		MediaPlayer player=sound_tracks.get(track_name);
		if(player!=null)player.stop();
		
		MediaPlayer bg= new MediaPlayer(media);
		
		FloatProperty v=SoundManager.volume.get(track_name);
		if(v==null) {
			System.out.println("INTIALIZE PATH "+track_name);
			v=new SimpleFloatProperty();
			v.set(1);
			volume.put(track_name, v);
		}
		bg.volumeProperty().bind(v);
		
		
		bg.setCycleCount(repeat);
		bg.play();
		sound_tracks.put(track_name, bg);
		
	}
	public static Media getSound(String bundle_name, String sound_name) {
		SoundBundle bundle=sound_bundles.get(bundle_name);
		if(bundle==null)return null;
		else {
			return bundle.getSound(sound_name);
		}
	}
}

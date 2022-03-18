package jempasam.swj.resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;


public class ResourceManager<T> {
	private Map<String,ResourceBundle<T>> resource_bundles;
	Function<InputStream,T> resource_loader;
	String resourcefolder;
	
	public ResourceManager(Function<InputStream,T> resource_loader, String resourcefolder) {
		resource_bundles=new HashMap<>();
		this.resource_loader=resource_loader;
		this.resourcefolder=resourcefolder;
		
		Set<String> resources_to_load=new HashSet<>();
		
		//Then load
		for(String bundlename : listInDir(resourcefolder)) {
			resource_bundles.put(bundlename, new ResourceBundle<T>(this,bundlename));
		}
	}
	public ResourceBundle<T> getResourceBundle(String name) {
		return resource_bundles.get(name);
	}
	
	public void setUnfinded(String bundle_name, BiFunction<ResourceBundle<T>,String,T> unfinded) {
		ResourceBundle<T> bundle=getResourceBundle(bundle_name);
		if(bundle!=null)bundle.setUnfindedHandler(unfinded);
	}
	
	public T getResource(String bundle_name, String image_name) {
		ResourceBundle<T> bundle=resource_bundles.get(bundle_name);
		if(bundle==null)return null;
		else {
			return bundle.getResource(image_name);
		}
	}
	
	public static Set<String> listInDir(String dir){
		Set<String> ret=new HashSet<>();
		Class<?> clazz=ResourceManager.class;
		
		BufferedReader reader;
		InputStream inputstream;
		String line;
		//Load from inside of the jar
		inputstream=clazz.getResourceAsStream("/"+dir+"/index.txt");
		if(inputstream!=null) {
			reader=new BufferedReader(new InputStreamReader(inputstream));
			try {
				while( (line=reader.readLine()) != null ) {
					System.out.println("pk:"+line);
					ret.add(line);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Load from extern files
		File dirf=new File(dir+"/");
		if(dirf.isDirectory()) {
			for(String file : dirf.list()){
				System.out.println("pd:"+file);
				ret.add(file);
			}
		}
		
		ret.remove("index.txt");
		return ret;
	}
}

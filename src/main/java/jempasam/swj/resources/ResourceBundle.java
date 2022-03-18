package jempasam.swj.resources;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;

public class ResourceBundle<T> {
	private Map<String,T> resources;
	private List<T> initial_resources;
	private BiFunction<ResourceBundle<T>,String,T> unfinded;
	ResourceManager<T> manager;
	
	ResourceBundle(ResourceManager<T> manager,String name) {
		resources=new HashMap<>();
		initial_resources=new ArrayList<>();
		this.manager=manager;
		
		for(String resourcename : ResourceManager.listInDir(manager.resourcefolder+"/"+name)) {
			loadResource(name, resourcename);
		}
	}
	private void loadResource(String bundle_name, String resource_name) {
		InputStream stream=ResourceManager.class.getResourceAsStream("/imagemanager/"+bundle_name+"/"+resource_name);
		T new_resource=manager.resource_loader.apply(stream);
		resources.put(resource_name.substring(0, resource_name.lastIndexOf('.')), new_resource);
		initial_resources.add(new_resource);
	}
	public void addResource(String id, T resource) {
		resources.put(id, resource);
	}
	public T getInitialResourceOfInt(int i) {
		return initial_resources.get(Math.abs(i%initial_resources.size()));
	}
	
	public T getRandomInitialResource() {
		return initial_resources.get(new Random().nextInt(initial_resources.size()));
	}
	
	public void setUnfindedHandler(BiFunction<ResourceBundle<T>,String,T> unfinded) {
		this.unfinded = unfinded;
	}
	
	public T getResource(String name) {
		if(name.equals("random"))return initial_resources.get((int)(initial_resources.size()*Math.random()));
		
		T ret=resources.get(name);
		if(ret==null) {
			return unfinded==null ? null : unfinded.apply(this,name);
		}else {
			return ret;
		}
	}
	
}

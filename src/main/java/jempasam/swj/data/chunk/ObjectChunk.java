package jempasam.swj.data.chunk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jempasam.swj.container.Container;

public class ObjectChunk extends DataChunk implements Container<DataChunk>{
	
	private List<DataChunk> content;
	private Map<String,DataChunk> content_map;
	
	public ObjectChunk(String name) {
		super(name);
		content=new ArrayList<>();
		content_map=new HashMap<>();
	}
	public DataChunk clone(){
		ObjectChunk newchunk=new ObjectChunk(getName());
		for(DataChunk d : this)newchunk.add(d.clone());
		return newchunk;
	}
	
	//--Container--
	//	Get
	@Override public DataChunk get(int position) {return content.get(position);}
	public DataChunk get(String name) {
		int firstpoint;
		if((firstpoint=name.indexOf('.'))!=-1) {
			String parentname=name.substring(0,firstpoint);
			String childname=name.substring(firstpoint+1);
			DataChunk dc=content_map.get(parentname);
			if(dc instanceof ObjectChunk)return ((ObjectChunk) dc).get(childname);
			else return null;
		}
		else return content_map.get(name);
	}
	// Add/Insert
	@Override public void insert(int position, DataChunk obj) {
		int oldpos=content.indexOf(obj);
		if(oldpos!=-1)content.remove(oldpos);
		
		content.add(position, obj);
		content_map.put(obj.getName(), obj);
	}
	// Remove
	@Override public DataChunk remove(int position) {
		DataChunk removed=content.remove(position);
		if(removed!=null)content_map.remove(removed.getName());
		return removed;
	}
	// Set
	@Override public void set(int position, DataChunk obj) {
		DataChunk old=get(position);
		if(old!=null) {
			content_map.remove(old.getName());
			content_map.put(obj.getName(), obj);
			content.set(position, obj);
		}
	}
	// Size
	@Override public int size() {return content.size();}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("\""+getName()+"\":").append("(");
		for(DataChunk d : this)sb.append(d.toString()).append(" ");
		sb.append(")");
		return sb.toString();
	}
	
	private void fillWithValues(String nameprefix, List<ValueChunk> values) {
		for(DataChunk d : this) {
			if(d instanceof ValueChunk)values.add(new ValueChunk(nameprefix+d.getName(), ((ValueChunk) d).getValue()));
			else if(d instanceof ObjectChunk)((ObjectChunk) d).fillWithValues(nameprefix+d.getName()+".", values);
		}
	}
	
	private void fillWithValues(List<ValueChunk> values) { fillWithValues("",values); }
	
	public ValueChunk[] toValueArray() {
		List<ValueChunk> list=new ArrayList<>();
		fillWithValues(list);
		return list.toArray(new ValueChunk[0]);
	}
}

package jempasam.swj.prototype.loader;

import java.io.InputStream;

import jempasam.swj.data.chunk.ObjectChunk;
import jempasam.swj.data.deserializer.DataDeserializer;
import jempasam.swj.logger.SLogger;
import jempasam.swj.prototype.Prototype;
import jempasam.swj.prototype.PrototypeManager;

public abstract class PrototypeLoader<T extends Prototype> {
	protected PrototypeManager<T> manager;
	protected SLogger logger;
	protected DataDeserializer deserializer;
	
	
	protected PrototypeLoader(PrototypeManager<T> manager, SLogger logger, DataDeserializer serializer) {
		super();
		this.manager = manager;
		this.logger = logger;
		this.deserializer = serializer;
	}
	
	
	public final void loadFrom(InputStream input) {
		ObjectChunk data=deserializer.loadFrom(input);
		interpret(data);
	}
	
	protected abstract void interpret(ObjectChunk data);
}

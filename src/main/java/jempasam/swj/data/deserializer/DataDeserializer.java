package jempasam.swj.data.deserializer;

import java.io.InputStream;

import jempasam.swj.data.chunk.ObjectChunk;

public interface DataDeserializer {
	ObjectChunk loadFrom(InputStream i);
}

package jempasam.swj.prototype;

import jempasam.swj.data.deserializer.strobjo.StrobjoDataDeserializer;
import jempasam.swj.deserializer.SimplePrimitiveDeserializer;
import jempasam.swj.logger.SLogger;
import jempasam.swj.logger.SimpleSLogger;
import jempasam.swj.prototype.loader.PrototypeLoader;
import jempasam.swj.prototype.loader.SimplePrototypeLoader;
import jempasam.swj.textanalyzis.tokenizer.impl.InputStreamSimpleTokenizer;

public class Prototypes {
	public static <T extends Prototype> PrototypeLoader<T> createLoader(PrototypeManager<T> manager, Class<T> type, String prefix, String name){
		PrototypeLoader<T> newproto=null;
		SLogger logger=new SimpleSLogger(System.out);
		if(name.equals("strobjo")) {
			newproto=new SimplePrototypeLoader<T>(
					manager,
					logger,
					new StrobjoDataDeserializer((i)->new InputStreamSimpleTokenizer(i, " \t\r\n", ":(),", "\"'"),logger),
					new SimplePrimitiveDeserializer(),
					type,
					prefix);
		}
		return newproto;
	}
}

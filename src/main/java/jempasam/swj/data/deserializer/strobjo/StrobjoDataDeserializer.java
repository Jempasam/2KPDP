package jempasam.swj.data.deserializer.strobjo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import jempasam.swj.data.chunk.DataChunk;
import jempasam.swj.data.chunk.ObjectChunk;
import jempasam.swj.data.chunk.ValueChunk;
import jempasam.swj.data.deserializer.DataDeserializer;
import jempasam.swj.logger.SLogger;
import jempasam.swj.logger.SimpleSLogger;
import jempasam.swj.textanalyzis.reader.IteratorBufferedReader;
import jempasam.swj.textanalyzis.tokenizer.Tokenizer;
import jempasam.swj.textanalyzis.tokenizer.impl.InputStreamSimpleTokenizer;

public class StrobjoDataDeserializer implements DataDeserializer{
	
	Function<InputStream, Tokenizer> tokenizerSupplier;
	SLogger logger;
		
	public StrobjoDataDeserializer(Function<InputStream, Tokenizer> tokenizerSupplier, SLogger logger) {
		super();
		this.tokenizerSupplier=tokenizerSupplier;
		this.logger=logger;
	}
		
	@Override
	public ObjectChunk loadFrom(InputStream input) {
		return loadChunk(new IteratorBufferedReader<String>(tokenizerSupplier.apply(input),new String[5]),"root");
	}
	
	private ObjectChunk loadChunk(IteratorBufferedReader<String> tokenizer,String name) {
		logger.enter("Object "+name);
		ObjectChunk newchunk=new ObjectChunk(name);
		
		String token;
		boolean endofobject=false;
		boolean endofparameter;
		
		List<String> names=new ArrayList<>();
		List<DataChunk> values=new ArrayList<>();
		int i=1;
		//Load names of parameter and their values
		while(!endofobject) {
			logger.enter("Parameter "+i);
			
			endofparameter=false;
			
			//LOAD NAMES
			while(true) {
				token=tokenizer.next();
				//CLOSE OBJECT
				if(token==null || token.equals(")")) {
					endofobject=true;
					break;
				}
				//CLOSE NAME LIST
				else if(token.equals(":")) {
					break;
				}
				//CLOSE PARAMETER
				else if(token.equals(",")) {
					endofparameter=true;
					break;
				}
				//ADD NAME
				else names.add(token);
			}
			
			//LOAD VALUES
			if(!endofobject && !endofparameter)
			while(true) {
				token=tokenizer.next();
				//CLOSE OBJECT
				if(token==null || token.equals(")")) {
					endofobject=true;
					break;
				}
				else if(token.equals(",")) {
					break;
				}
				else{
					tokenizer.backward();
					DataChunk dc=loadDataChunkValue(tokenizer);
					if(dc!=null)values.add(dc);
				}
			}
			
			//CANCELING ERROR
			if(values.size()==0)logger.log("Values are misising. Parameter is ignored.");
			else if(names.size()==0)logger.log("Names are missing. Parameter is ignored.");
			else {
				for(String n : names) {
					int counter=1;
					for(DataChunk v: values) {
						DataChunk d=v.clone();
						d.setName(n+( counter!=1?counter:"" ));
						newchunk.add(d);
						counter++;
					}
				}
			}
			
			values.clear();
			names.clear();
			
			i++;
			
			logger.exit();
		}
		
		logger.exit();
		return newchunk;
	}
	
	private DataChunk loadDataChunkValue(IteratorBufferedReader<String> tokenizer) {
		String token;
		if((token=tokenizer.next())!=null) {
			if(token.equals("("))return loadChunk(tokenizer, "");
			else return new ValueChunk("", token);
		}
		else return null;
	}
	
	public static void main(String[] args) {
		DataDeserializer serializer=new StrobjoDataDeserializer((i)->new InputStreamSimpleTokenizer(i, " \t\r\n", "():,", "\"'"), new SimpleSLogger(System.out));
		try {
			ObjectChunk obj=serializer.loadFrom(new FileInputStream(new File("test.txt")));
			System.out.println(obj.toString());
			System.out.println(Arrays.toString(obj.toValueArray()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

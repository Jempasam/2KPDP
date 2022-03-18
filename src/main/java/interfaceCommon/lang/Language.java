package interfaceCommon.lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Language {
	private String name;
	private Map<String,String> translations;
	
	public Language(InputStream input) {
		translations=new HashMap<>();
		BufferedReader br=new BufferedReader(new InputStreamReader(input));
		try {
			name=br.readLine();
			String lastkey=null;
			int lastkey_counter=0;
			String line;
			String prefix="";
			while((line=br.readLine())!=null) {
				line=line.trim();
				int middle=line.indexOf('=');
				//Commentary
				if(line.length()>0 && line.charAt(0)=='#') {
				}
				//Translation
				else if(middle>0){
					lastkey=prefix+line.substring(0, middle);
					lastkey_counter=2;
					String key=lastkey;
					String text=line.substring(middle+1, line.length());
					translations.put(key, text);
				}
				//Translation counter
				else if(line.length()>0 && middle==0) {
					String key=lastkey+Integer.toString(lastkey_counter);
					String text=line.substring(1, line.length());
					translations.put(key, text);
					lastkey_counter++;
				}
				//Namespace open
				else if(line.length()>0 && line.charAt(line.length()-1)=='{'){
					prefix+=line.substring(0, line.length()-1)+".";
				}
				//Namespace close
				else if(line.equals("}")) {
					int ix=prefix.lastIndexOf('.',prefix.length()-2);
					if(ix==-1)prefix="";
					else prefix=prefix.substring(0, ix+1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	public Collection<String> getKeys() {
		return translations.keySet();
	}
	
	public String get(String key) {
		String text=translations.get(key);
		if(text==null) {
			//Try default
			int lastcomma=key.lastIndexOf('.');
			if(lastcomma!=-1) {
				String dflt=key.substring(0, lastcomma)+".default";
				dflt=translations.get(dflt);
				if(dflt!=null)return dflt;
			}
			System.out.println("Missing translation key \""+key+"\"");
			return key;
		}
		else return text;
	}
}

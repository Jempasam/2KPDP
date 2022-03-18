package jempasam.swj.textanalyzis.tokenizer.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import jempasam.swj.textanalyzis.tokenizer.Tokenizer;



public class InputStreamSimpleTokenizer implements Tokenizer{
	private InputStreamReader input;
	private String separator;
	private String solo;
	private String stringsep;
	
	private int[] buffer;
	private int buffer_last;
	private int buffer_end;
	
	public InputStreamSimpleTokenizer(InputStream input, String separator, String solo_character, String stringsep) {
		this.input=new InputStreamReader(input);
		this.separator=separator;
		this.solo=solo_character;
		this.stringsep=stringsep;
		
		buffer=new int[10];
		buffer_last=0;
		buffer_end=0;
		
	}
	
	private int nextchar() {
		if(buffer_last==buffer_end){
			buffer_last++;
			if(buffer_last>=buffer.length)buffer_last=0;
			buffer_end=buffer_last;
			try {
				buffer[buffer_last]=input.read();
			} catch (IOException e) {
				buffer[buffer_last]=-1;
			}
		}else {
			buffer_last++;
			if(buffer_last>=buffer.length)buffer_last=0;
		}
		return buffer[buffer_last];
	}
	
	private void backward() {
		buffer_last--;
		if(buffer_last<0)buffer_last=buffer.length-1;
	}
	
	@Override
	public String next() {
		StringBuilder sb=new StringBuilder();
		int ch;
		
		//PASS WHITE SPACES
		while(separator.indexOf(ch=nextchar())!=-1 && ch!=-1);
		
		if(ch!=-1) {
			//TEST IF SOLO
			if(solo.indexOf(ch)!=-1) {
				sb.append((char)ch);
			}
			else {
				//READ THE TOKEN
				int instring=-1;
				do {
					if(instring==-1) {
						if(separator.indexOf(ch)!=-1)break;
						else if(solo.indexOf(ch)!=-1) {
							backward();
							break;
						}
						else if(stringsep.indexOf(ch)!=-1)instring=ch;
						else sb.append((char)ch);
					}
					else {
						if(ch==instring)instring=-1;
						else sb.append((char)ch);
					}
				}while((ch=nextchar())!=-1);
			}
		}
		
		if(sb.length()==0)return null;
		else return sb.toString();
	}
	
	@Override
	public boolean hasNext() {
		try {
			return buffer_last!=buffer_end || input.ready();
		} catch (IOException e) {
			return false;
		}
	}
}

package dev;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class IndexGenerator {
	public static void main(String[] args) {
		addIndexToFolder(new File("src/main/resources"));
	}
	private static void addIndexToFolder(File folder) {
		File index=new File(folder.getAbsolutePath()+"/index.txt");
		try {
			BufferedWriter index_write=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(index)));
			
			for(File file : folder.listFiles()) {
				if(!file.getName().equals("index.txt"))index_write.write(file.getName()+"\n");
				if(file.isDirectory())addIndexToFolder(file);
			}
			index_write.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

package network.common;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class KPDPMessageWriter {
	
	OutputStreamWriter stream;
	
	public KPDPMessageWriter(OutputStream stream) {
		this.stream=new OutputStreamWriter(stream);
	}
	
	public void sendMessage(String message) throws IOException {
		stream.write(message+"|");
		stream.flush();
	}
	
	public void sendMessage(String[] message) throws IOException {
		StringBuilder sb=new StringBuilder();
		
		for(String s : message)sb.append(s).append(";");
		sb.deleteCharAt(sb.length()-1);
		
		sendMessage(sb.toString());
	}
	
}

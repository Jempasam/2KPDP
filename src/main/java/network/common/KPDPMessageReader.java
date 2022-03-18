package network.common;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Permet de récupère un message depuis le InputStream d'un socket
 * @author Samuel Demont
 *
 */
public class KPDPMessageReader {
	
	InputStreamReader stream;
	
	public KPDPMessageReader(InputStream stream) throws UnsupportedEncodingException {
		this.stream=new InputStreamReader(stream, "utf8");
	}
	
	public boolean hasMessage() throws IOException{
		return stream.ready();
	}
	
	private String _getMessage() throws IOException {
		StringBuilder sb=new StringBuilder();
		
		int ch;
		while( (ch=stream.read())!=-1 && (char)ch!='|' ) {
			sb.append((char)ch);
		}
		
		return sb.toString();
	}
	
	public String getMessage() throws IOException {
		if(hasMessage())return _getMessage();
		else return null;
	}
	
	public String[] getMessageSplited() throws IOException {
		if(hasMessage())return _getMessage().split(";");
		else return null;
	}
}

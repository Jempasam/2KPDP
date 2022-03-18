package network.common.packet;

public class CommonPacketHelper {
	public static String buildMessage(String ...strings) {
		StringBuilder sb=new StringBuilder();
		sb.append(strings[0]);
		for(int i=1; i<strings.length; i++)sb.append('-').append(strings[1]);
		return sb.toString();
	}
}

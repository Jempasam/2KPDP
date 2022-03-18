package network.pp.packet;

import network.common.packet.CommonPacketHelper;

public class ServerPacketHelper {
	public static String buildAnnounceGame(String idp, String ip, String port, String name) {
		return CommonPacketHelper.buildMessage("ARP", idp, ip, port, name);
	}
}

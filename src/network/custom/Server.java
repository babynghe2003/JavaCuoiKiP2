package network.custom;

import java.io.IOException;
import java.util.ArrayList;

public class Server extends CServer{
	public ArrayList<CSocket> sockets;
	public Server() throws IOException {		
		super(25565);
		sockets = new ArrayList<CSocket>();
	}
	@Override
	public void onClientConnect(CSocket cSocket) {
		System.out.println(cSocket + " Connect");
		sockets.add(cSocket);
	}
	@Override
	public void onClientDisconnect(CSocket cSocket) {
		System.out.println(cSocket + " Disconnect");
		sockets.remove(cSocket);
	}
	@Override
	public void onClientPacket(CSocket cSocket, Packet cPacket) {
		System.out.println(cSocket + " : " + cPacket);
	}
	public static void main(String[] args) {
		try {
			Server server = new Server();
			System.out.println("Server running on " + server.getHost() + ":" + server.getPort());
		} catch (Exception e) {
			System.out.println("Server can't run");
		}
	}

}

package network.custom;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class CServer {
	private ServerSocket server = null;
	private String host;
	private int port;
	public CServer(int port) throws IOException {
		this.port = port;
		this.host = InetAddress.getLocalHost().getHostAddress();
		this.server = new ServerSocket(port);
		this.listen();
	}
	public void onClientConnect(CSocket cSocket) {
		System.out.println("Connect");
	}
	public void onClientDisconnect(CSocket cSocket) {
		System.out.println("disconnect");
	}
	public void onClientPacket(CSocket cSocket, Packet cPacket) {
		System.out.println("packet" + cPacket);
	}
	public void listen() {
		new Thread(()->{
			try {
				while(true) {
					Socket socket = server.accept();
					try {
						new CSocket(socket) {
							public void onConnect() {
								onClientConnect(getInstance());
							};
							public void onDisconnect() {
								onClientDisconnect(getInstance());
							};
							public void onPacket(Packet packet) {
								onClientPacket(getInstance(), packet);
							};
						};
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					this.server.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}).start();
	}
	public ServerSocket getServer() {
		return server;
	}
	public void setServer(ServerSocket server) {
		this.server = server;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}	
}

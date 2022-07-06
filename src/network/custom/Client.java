package network.custom;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends CSocket{

	public Client(String host, int port) throws UnknownHostException, IOException {
		super(host, port);
	}
	@Override
	public void onConnect() {
		System.out.println("Connect success!");
	}
	@Override
	public void onDisconnect() {
		System.out.println("Disconnect!");
	}
	@Override
	public void onPacket(Packet packet) {
		System.out.println(packet);
	}
	public static void main(String[] args) {
		try {
			Client client = new Client("26.131.74.238", 25565);
			System.out.println("Running");
			Scanner sc = new Scanner(System.in);
			String line = "";
			while((line = sc.nextLine()) != null) {
				//client.sendPacket(new CPacket(line, "msg"));
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

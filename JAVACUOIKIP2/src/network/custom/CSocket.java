package network.custom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.google.gson.Gson;

import network.custom.cpacket.CPacketDamage;
import network.custom.cpacket.CPacketJoin;
import network.custom.cpacket.CPacketPlayerPosition;
import network.custom.cpacket.CPacketShoot;
import network.custom.spacket.SPacketDamage;
import network.custom.spacket.SPacketInitWorld;
import network.custom.spacket.SPacketPlayerDisconnect;
import network.custom.spacket.SPacketPlayerPosition;
import network.custom.spacket.SPacketSetID;
import network.custom.spacket.SPacketShoot;
import network.custom.spacket.SPacketSpawnPlayer;

public class CSocket {
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	public CSocket(Socket socket) throws IOException {
		this.socket = socket;
		this.input = new DataInputStream(this.socket.getInputStream());
		this.output = new DataOutputStream(this.socket.getOutputStream());
		this.listen();
		this.onConnect();
	}
	public CSocket getInstance() {
		return this;
	}
	public CSocket(String host, int port) throws UnknownHostException, IOException {
		this.socket = new Socket(host, port);
		this.input = new DataInputStream(this.socket.getInputStream());
		this.output = new DataOutputStream(this.socket.getOutputStream());
		this.listen();
		this.onConnect();
	}
	public void listen() {
		new Thread(()->{
			try {
				Gson gson = new Gson();
				while(true && socket != null && !socket.isClosed()) {
					try {
						String line = input.readUTF();
						CPacket packet = gson.fromJson(line, CPacket.class);
						if(getPacketList().containsKey(packet.getpName())) {
							onPacket(getPacketList().get(packet.getpName()).cast(gson.fromJson(packet.getPacket(), getPacketList().get(packet.getpName()))));
						}
					} catch (Exception e) {
						onDisconnect();						
						e.printStackTrace();
						break;
					}
				}
			} catch (Exception e) {
				onDisconnect();
				e.printStackTrace();
			}
		}).start();
	}
	public void onConnect() {
		System.out.println("Connect");
	}
	public void onDisconnect() {
		System.out.println("Disconnect");
	}
	public void onPacket(Packet packet) {
		System.out.println(packet);
	}
	public synchronized void sendPacket(Packet packet) {
		if(socket.isClosed()) return;
		Gson gson = new Gson();
		CPacket p = new CPacket(packet.getClass().getSimpleName(), packet);
		try {
			output.writeUTF(gson.toJson(p));
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				this.socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public DataInputStream getInput() {
		return input;
	}
	public void setInput(DataInputStream input) {
		this.input = input;
	}
	public DataOutputStream getOutput() {
		return output;
	}
	public void setOutput(DataOutputStream output) {
		this.output = output;
	}
	public void add(HashMap<String, Class<? extends Packet>> a, Class<? extends Packet> b) {
		a.put(b.getSimpleName(), b);
	}
	public HashMap<String, Class<? extends Packet>> getPacketList() {
		HashMap<String, Class<? extends Packet>> packet = new HashMap<String, Class<? extends Packet>>();
		////////////////////////////////
		
		add(packet, SPacketInitWorld.class);
		add(packet, SPacketPlayerDisconnect.class);
		add(packet, SPacketSetID.class);
		add(packet, SPacketSpawnPlayer.class);
		add(packet, SPacketPlayerPosition.class);
		add(packet, SPacketShoot.class);
		add(packet, SPacketDamage.class);
		////////////////////	
		add(packet, CPacketJoin.class);
		add(packet, CPacketPlayerPosition.class);
		add(packet, CPacketShoot.class);
		add(packet, CPacketDamage.class);
		return packet;		
	}	
}

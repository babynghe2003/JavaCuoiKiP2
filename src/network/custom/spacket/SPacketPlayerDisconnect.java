package network.custom.spacket;

import network.custom.Packet;

public class SPacketPlayerDisconnect extends Packet{
	private int id;

	public SPacketPlayerDisconnect(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

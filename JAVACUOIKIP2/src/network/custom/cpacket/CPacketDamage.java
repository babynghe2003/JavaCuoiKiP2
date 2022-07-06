package network.custom.cpacket;

import network.custom.Packet;

public class CPacketDamage extends Packet{
	private int id;

	public CPacketDamage(int id) {
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

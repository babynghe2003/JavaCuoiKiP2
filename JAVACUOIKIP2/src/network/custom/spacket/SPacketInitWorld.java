package network.custom.spacket;

import network.custom.Packet;

public class SPacketInitWorld extends Packet{
	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public SPacketInitWorld(int size) {
		super();
		this.size = size;
	}
	
}

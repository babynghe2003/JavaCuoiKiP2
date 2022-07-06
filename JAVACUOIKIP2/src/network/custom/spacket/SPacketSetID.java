package network.custom.spacket;

import network.custom.Packet;

public class SPacketSetID extends Packet{
	private int id;
	private float posX;
	private float posY;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPosX() {
		return posX;
	}
	public void setPosX(float posX) {
		this.posX = posX;
	}
	public float getPosY() {
		return posY;
	}
	public void setPosY(float posY) {
		this.posY = posY;
	}
	public SPacketSetID(int id, float posX, float posY) {
		super();
		this.id = id;
		this.posX = posX;
		this.posY = posY;
	}
	
}

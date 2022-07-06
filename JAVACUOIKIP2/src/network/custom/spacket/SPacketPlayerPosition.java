package network.custom.spacket;

import network.custom.Packet;

public class SPacketPlayerPosition extends Packet{
	private int id;
	private float posX;
	private float posY;
	private float angle;
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
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
	public SPacketPlayerPosition(int id, float posX, float posY, float angle) {
		super();
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.angle = angle;
	}
	@Override
	public String toString() {
		return "SPacketPlayerPosition [id=" + id + ", posX=" + posX + ", posY=" + posY + ", angle=" + angle + "]";
	}
	
}

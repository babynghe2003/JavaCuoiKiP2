package network.custom.spacket;

import network.custom.Packet;

public class SPacketShoot extends Packet{
	private int id;
	private float posX;
	private float posY;
	private float angle;
	private long spawnTime;
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
	public long getSpawnTime() {
		return spawnTime;
	}
	public void setSpawnTime(long spawnTime) {
		this.spawnTime = spawnTime;
	}
	public SPacketShoot(int id, float posX, float posY, float angle, long spawnTime) {
		super();
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.angle = angle;
		this.spawnTime = spawnTime;
	}
	
}

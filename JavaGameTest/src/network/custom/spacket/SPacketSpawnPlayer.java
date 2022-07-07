package network.custom.spacket;

import network.custom.Packet;

public class SPacketSpawnPlayer extends Packet{
	private int id;
	private float posX;
	private float posY;
	private int health;
	private String name;
	
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
	public SPacketSpawnPlayer(int id, float posX, float posY, int health, String name) {
		super();
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.health = health;
		this.name = name;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

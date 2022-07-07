package game.entity;

import java.awt.Graphics2D;

import game.Game;

public class Player extends Entity{
	private String uuid;
	private String name;
	private float angle;
	private float motion;
	private int health;
	private boolean  isConnect;

	
	public float getMotion() {
		return motion;
	}
	public void setMotion(float motion) {
		this.motion = motion;
	}
	public Player() {
		this.uuid = "";
		this.name = "";
		this.angle = 0;
		this.motion = 0;
		this.health = 100;
		isConnect = true;
	}
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void update(float delta, Game game) {
		
	}

	@Override
	public void render(Graphics2D g2d, Game game) {	
		int w = 100;
		int h = 10;
		int x = (int) (getPosX() - w / 2);
		int y = (int) (getPosY() - 60 - h);
		g2d.drawRect(x,y,w,h);

		g2d.fillRect(x,y,getHealth(),h);
		// g2d.drawString(getHealth() + "", x, y);
		g2d.drawString(getName() + "", x, y-5 );
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public boolean isConnect() {
		return isConnect;
	}
	public void setIsConnect(boolean isConnect) {
		this.isConnect = isConnect;
	}
	

}

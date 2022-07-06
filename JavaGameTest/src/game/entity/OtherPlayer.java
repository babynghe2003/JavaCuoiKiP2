package game.entity;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.Game;

public class OtherPlayer extends Player{
	private float preX = 0;
	private float preY = 0;
	private float newX = 0;
	private float newY = 0;
	private long lastUpdateTime;
	private long updateTime;
	private boolean isConnect;
	Image image;
	Image image2;

	public OtherPlayer() {
		image = new ImageIcon("assets/PNG/playerShip3_orange.png").getImage();
		image2 = new ImageIcon("assets/PNG/ufoRed.png").getImage();

	}
	public float getNewX() {
		return newX;
	}
	public void setNewX(float newX) {
		this.newX = newX;
	}
	public float getNewY() {
		return newY;
	}
	public void setNewY(float newY) {
		this.newY = newY;
	}
	@Override
	public void update(float delta, Game game) {
		/*float del = delta / ((updateTime - lastUpdateTime) / 1000f);
		float x = preX + (newX - preX) * del;
		float y = preY + (newY - preY) * del;
		setPosX(x);setPosY(y);*/
	}
	@Override
	public void render(Graphics2D g2d, Game game) {
		g2d.drawImage(image2, (int) getPosX()-50, (int) getPosY()-50, null);

		g2d.rotate(getAngle(), getPosX(), getPosY());		
		g2d.drawImage(image, (int) getPosX()-50, (int) getPosY()-50, null);
		g2d.rotate(-getAngle(), getPosX(), getPosY());
		super.render(g2d, game);
	}
	public float getPreX() {
		return preX;
	}
	public void setPreX(float preX) {
		this.preX = preX;
	}
	public float getPreY() {
		return preY;
	}
	public void setPreY(float preY) {
		this.preY = preY;
	}
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public boolean isConnect() {
		return isConnect;
	}
	public void setConnect(boolean isConnect) {
		this.isConnect = isConnect;
	}	
}

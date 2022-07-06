package game.entity;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.Game;

public class Particle extends Entity{
	
	private float motionX;
	private float motionY;
	private long lifetime;
	
	Image image;
	
	public Particle(float posX, float posY, float motionX, float motionY, long lifetime) {
		this.motionX = motionX;
		this.motionY = motionY;
		setPosX(posX);setPosY(posY);
		this.lifetime = lifetime;		
		image = new ImageIcon("assets/PNG/Lasers/laserBlue01.png").getImage();
	}
	@Override
	public void update(float delta, Game game) {
		lifetime -= delta*1000f;
		if(!isOverTime()) {
			setPosX(getPosX() + motionX*delta);
			setPosY(getPosY() + motionY*delta);
		}		
	}

	@Override
	public void render(Graphics2D g2d, Game game) {
		float angle = (float) (Math.atan2(motionY, motionX) + Math.PI / 2);
		g2d.rotate(angle, getPosX(), getPosY());
		g2d.drawImage(image, (int) getPosX() - image.getWidth(null) / 2, (int) getPosY(), null);
		g2d.rotate(-angle, getPosX(), getPosY());
		//g2d.drawLine(0, 0, (int) getPosX(), (int) getPosY());
	}
	
	public float getMotionX() {
		return motionX;
	}
	public void setMotionX(float motionX) {
		this.motionX = motionX;
	}
	public float getMotionY() {
		return motionY;
	}
	public void setMotionY(float motionY) {
		this.motionY = motionY;
	}
	public long getLifetime() {
		return lifetime;
	}
	public void setLifetime(long lifetime) {
		this.lifetime = lifetime;
	}
	public boolean isOverTime() {
		return lifetime <= 0;
	}

}

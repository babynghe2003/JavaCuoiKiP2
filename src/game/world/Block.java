package game.world;

import java.awt.Graphics2D;
import java.awt.Image;

import game.Game;
import game.entity.IRender;

public class Block implements IRender{
	private int posX;
	private int posY;
	private Image texture;
	private int size;
	
	@Override
	public void update(float delta, Game game) {		
	}

	@Override
	public void render(Graphics2D g2d, Game game) {
		//graphics2d.drawImage(texture, posX, posY, size, size, null);
		g2d.drawRect(posX, posY, size, size);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Image getTexture() {
		return texture;
	}

	public void setTexture(Image texture) {
		this.texture = texture;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}	
}

package game.entity;

public abstract class Entity implements IRender{
	private float posX;
	private float posY;	
	private int id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Entity [posX=" + posX + ", posY=" + posY + ", id=" + id + "]";
	}	
	
}

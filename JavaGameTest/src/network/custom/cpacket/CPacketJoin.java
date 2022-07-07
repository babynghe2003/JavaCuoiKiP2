package network.custom.cpacket;

import network.custom.Packet;

public class CPacketJoin extends Packet{
    private String name;
    private float posX;
    private float posY;
    private int health;

    public CPacketJoin(String name, float posX, float posY, int health) {
        super();
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
}

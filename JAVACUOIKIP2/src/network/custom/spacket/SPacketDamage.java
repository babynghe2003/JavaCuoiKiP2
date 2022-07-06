package network.custom.spacket;

import network.custom.Packet;

public class SPacketDamage extends Packet{
	private int p_id;
	private int b_id;
	private int p_health;
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public SPacketDamage(int p_id, int b_id, int p_health) {
		super();
		this.p_id = p_id;
		this.b_id = b_id;
		this.p_health = p_health;
	}
	public int getP_health() {
		return p_health;
	}
	public void setP_health(int p_health) {
		this.p_health = p_health;
	}
	
}

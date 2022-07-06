package network.custom;

import com.google.gson.Gson;

public class CPacket {
	private String packet;
	private String pName;
	public CPacket() {
		super();
	}
	public CPacket(String packet, String pName) {
		super();
		this.packet = packet;
		this.pName = pName;
	}
	public CPacket(String packet, Object e) {
		super();
		this.pName = packet;
		this.packet = new Gson().toJson(e);
	}
	public String getPacket() {
		return packet;
	}
	public void setPacket(String packet) {
		this.packet = packet;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	@Override
	public String toString() {
		return "CPacket [packet=" + packet + ", pName=" + pName + "]";
	}
	
}

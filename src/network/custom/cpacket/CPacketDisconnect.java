package network.custom.cpacket;

import network.custom.Packet;

public class CPacketDisconnect extends Packet{
    private int id;

    // constructor
    public CPacketDisconnect(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

}

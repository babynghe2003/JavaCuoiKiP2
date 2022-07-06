package server;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import game.entity.OtherPlayer;
import game.entity.Player;
import game.world.World;
import network.custom.CServer;
import network.custom.CSocket;
import network.custom.Packet;
import network.custom.cpacket.CPacketDamage;
import network.custom.cpacket.CPacketDisconnect;
import network.custom.cpacket.CPacketPlayerPosition;
import network.custom.cpacket.CPacketShoot;
import network.custom.spacket.*;

public class Server extends CServer{
	HashMap<CSocket, OtherPlayer> players;
	World world;
	int index = 0;
	public Server(int port) throws IOException {
		super(port);
		world = new World(1000);
		players = new HashMap<CSocket, OtherPlayer>();
	}
	public static void main(String[] args) throws IOException {
		new Server(25565);
	}
	
	@Override
	public void onClientConnect(CSocket cSocket) {
		cSocket.sendPacket(new SPacketInitWorld(1000));
		OtherPlayer player = new OtherPlayer();
		player.setId(index);
		player.setPosX(0);
		player.setPosY(0);
		cSocket.sendPacket(new SPacketSetID(player.getId(), player.getPosX(), player.getPosY()));
		index++;
		players.forEach((socket, pl)->{
			System.out.println(pl.getHealth());
			cSocket.sendPacket(new SPacketSpawnPlayer(pl.getId(), pl.getPosX(), pl.getPosY(), pl.getHealth()));
			socket.sendPacket(new SPacketSpawnPlayer(player.getId(), player.getPosX(), player.getPosY(), player.getHealth()));
		});
		players.put(cSocket, player);
		world.getEntities().add(player);		
		super.onClientConnect(cSocket);
	}
	@Override
	public void onClientDisconnect(CSocket cSocket) {
		Player player = players.get(cSocket);
		world.getEntities().remove(players.get(cSocket));
		players.remove(cSocket);		
		players.forEach((socket, pl)->{
			socket.sendPacket(new SPacketPlayerDisconnect(player.getId()));
		});
		System.out.println(players.size());
		super.onClientDisconnect(cSocket);
	}
	@Override
	public void onClientPacket(CSocket cSocket, Packet cPacket) {
		if(cPacket instanceof CPacketPlayerPosition) {
			CPacketPlayerPosition packet = (CPacketPlayerPosition) cPacket;
			OtherPlayer player = players.get(cSocket);
			if(player == null) return;
			player.setAngle(packet.getAngle());
			player.setPosX(packet.getPosX());
			player.setPosY(packet.getPosY());
			players.forEach((socket, pl)->{
				if(socket != cSocket) {
					socket.sendPacket(new SPacketPlayerPosition(player.getId(), player.getPosX(), player.getPosY(), player.getAngle()));
				}				
			});
		}
		if(cPacket instanceof CPacketShoot) {
			long spawnTime = new Date().getTime();
			int id = index++;
			CPacketShoot pac = (CPacketShoot) cPacket;
			players.forEach((socket, pl)->{
				socket.sendPacket(new SPacketShoot(id, pac.getPosX(), pac.getPosY(), pac.getAngle(), spawnTime));
			});
		}
		if(cPacket instanceof CPacketDamage) {
			CPacketDamage pac = (CPacketDamage) cPacket;
			OtherPlayer player = players.get(cSocket);
			if(player == null) return;

			players.get(cSocket).setHealth(player.getHealth()-1);
			players.forEach((socket, pl)->{
				socket.sendPacket(new SPacketDamage(player.getId(), pac.getId(), player.getHealth()));
			});
		}
		if(cPacket instanceof CPacketDisconnect) {
			CPacketDisconnect pac = (CPacketDisconnect) cPacket;
			OtherPlayer player = players.get(cSocket);
			if(player == null) return;
			players.remove(cSocket);
			onClientDisconnect(cSocket);
		}
	}
}

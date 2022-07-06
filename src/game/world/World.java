package game.world;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import game.Game;
import game.entity.Entity;
import game.entity.IRender;
import game.entity.OtherPlayer;
import game.entity.Particle;
import game.entity.Player;

public class World implements IRender{
	private List<Entity> entities;
	private List<Entity> queue;
	private int size;
	public World(int size) {
		this.size = size;
		entities = new ArrayList<Entity>();
		queue = new ArrayList<Entity>();
	}
	public synchronized OtherPlayer getPlayer(int id) {
		for(Entity e : entities) {
			if(e instanceof OtherPlayer && e.getId() == id) {
				return (OtherPlayer) e;
			}
		}
		return null;
	}
	public synchronized Particle getBullet(int id) {
		for(Entity e : entities) {
			if(e instanceof Particle && e.getId() == id) {
				return (Particle) e;
			}
		}
		return null;
	}
	public synchronized List<Entity> getEntities() {
		return entities;
	}
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public void update(float delta, Game game) {		
		List<Entity> removes = new ArrayList<Entity>();

		for(Particle p : game.getPlayer().bullets) {
			entities.add(p);
		}
		for(Entity p : queue) {
			entities.add(p);
		}
		queue.clear();
		game.getPlayer().bullets.clear();
		for(Entity e : entities) {
			e.update(delta, game);
		}
		for(Entity e : entities) {
			if(e instanceof Player && !((Player)e).isConnect()) {
				removes.add(e);
			}
		}
		for(Entity e : entities) {
			if(e instanceof Particle && ((Particle) e).isOverTime()) {
				removes.add(e);
			}	
			if(e instanceof OtherPlayer && (!((OtherPlayer) e).isConnect() || ((OtherPlayer) e).getHealth() <= 0)) {
				removes.add(e);
			}
		}
		for(Entity e : removes) {
			entities.remove(e);
		}
	}

	@Override
	public void render(Graphics2D g2d, Game game) {
		g2d.drawOval(0-size/2, 0-size/2, size, size);
		for(Entity e : entities) {
			if (!(e instanceof Player && !((Player) e).isConnect()))
			e.render(g2d, game);
		}
	}
	public List<Entity> getQueue() {
		return queue;
	}
	public void setQueue(List<Entity> queue) {
		this.queue = queue;
	}	
}

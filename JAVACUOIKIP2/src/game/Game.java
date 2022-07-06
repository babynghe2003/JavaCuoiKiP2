package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import game.entity.IRender;
import game.entity.MyPlayer;
import game.entity.OtherPlayer;
import game.entity.Particle;
import game.entity.Player;
import game.utils.Helper;
import game.world.World;
import network.custom.Packet;
import network.custom.spacket.SPacketDamage;
import network.custom.spacket.SPacketPlayerDisconnect;
import network.custom.spacket.SPacketPlayerPosition;
import network.custom.spacket.SPacketSetID;
import network.custom.spacket.SPacketShoot;
import network.custom.spacket.SPacketSpawnPlayer;
import network.custom.CSocket;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game extends JPanel implements IRender, MouseListener, MouseMotionListener, KeyListener{
	public static final int UNIT = 100;
	private static final long serialVersionUID = 1L;
	private boolean stop = false;
	private World world;
	private int fps;
	private int limitFps;
	private GameState gameState;
	private MyPlayer player;

	public boolean warning = false;
	public long lastWarning = new Date().getTime();

	Image imageWarning;


	public Game(int port, String host) throws UnknownHostException, IOException {
		setFocusable(true);
		addMouseMotionListener(this);
		addKeyListener(this);
		addMouseListener(this);
		Client client = new Client(host, port, getGame());
		player = new MyPlayer(client);
		world = new World(1000);
		world.getQueue().add(player);
		gameState = new GameState();
		imageWarning = new ImageIcon("assets/PNG/warning.png").getImage();
		setBackground(Theme.getBackgroundColor());
		setLimitFps(100);
		start();
	}
	public void start() {		
		new Thread(()->{
			int delta = 0;
			long lastUpdate = new Date().getTime();
			while(!stop) {				
				long time = new Date().getTime();
				delta += time - lastUpdate;
				lastUpdate = time;
				if(delta >= (1000 / getLimitFps())) {
					update(delta / 1000f, getGame());
					lastUpdate = time;
					repaint();
					delta = 0;
				}				
			}
		}).start();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Helper.smoothPaint(g2d);
		g2d.setColor(Color.white);
		render(g2d, getGame());
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		JFrame frame = new JFrame();
		Game game = new Game(25565,"localhost");
		frame.setLayout(new BorderLayout());
		frame.setSize(500, 500);
		frame.add(game, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	
	@Override
	public void update(float delta, Game game) {
		long time = new Date().getTime();
		if(time - lastWarning > 1000) {
			warning = false;
		}
		world.update(delta, game);
	}
	@Override
	public void render(Graphics2D g2d, Game game) {
		g2d.translate(getGame().getWidth() / 2 - getPlayer().getPosX(),getGame().getHeight() / 2 - getPlayer().getPosY());
		
		world.render(g2d, game);
		long time = new Date().getTime();
		
		if (warning){
			float opacity = 0.5f-(time - lastWarning)/2000f;
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
			g2d.drawImage(imageWarning,Math.round(getPlayer().getPosX()-getGame().getWidth()/2),Math.round(getPlayer().getPosY()-getGame().getHeight() / 2), getGame().getWidth(), getGame().getHeight(), null);
		}		
	}

	public static synchronized void playSound(final String url) {
		new Thread(new Runnable() { // the wrapper thread is unnecessary, unless it blocks on the Clip finishing, see comments
		  public void run() {
			try {
			  Clip clip = AudioSystem.getClip();
			  AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream("/assets/Bonus/" + url));
			  clip.open(inputStream);
			  clip.start(); 
			} catch (Exception e) {
			  System.err.println(e.getMessage());
			}
		  }
		}).start();
	  }

	public Game getGame() {
		return this;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public int getFps() {
		return fps;
	}
	public void setFps(int fps) {
		this.fps = fps;
	}
	public int getLimitFps() {
		return limitFps;
	}
	public void setLimitFps(int limitFps) {
		this.limitFps = limitFps;
	}
	public GameState getGameState() {
		return gameState;
	}
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	boolean test[] = new boolean[4];
	@Override
	public void keyPressed(KeyEvent e) {
		getGameState().getKeyboard()[e.getKeyCode()] = true;

	}
	@Override
	public void keyReleased(KeyEvent e) {
		getGameState().getKeyboard()[e.getKeyCode()] = false;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		getGameState().setMouseX(e.getX());
		getGameState().setMouseY(e.getY());
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		getGameState().setMouseX(e.getX());
		getGameState().setMouseY(e.getY());
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		getGameState().getMouse()[e.getButton()] = true;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		getGameState().getMouse()[e.getButton()] = false;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		getGameState().setMouseX(-1);
		getGameState().setMouseY(-1);
		getGameState().getMouse()[0] = false;
		getGameState().getMouse()[1] = false;
		getGameState().getMouse()[2] = false;
	}
	public MyPlayer getPlayer() {
		return player;
	}
	public void setPlayer(MyPlayer player) {
		this.player = player;
	}
}
class Client extends CSocket{
	Game game;
	public Client(String host, int port, Game game) throws UnknownHostException, IOException {
		super(host, port);	
		this.game = game;
	}
	@Override
	public void onConnect() {
		super.onConnect();
	}
	@Override
	public void onDisconnect() {
		super.onDisconnect();
	}
	@Override
	public void onPacket(Packet packet) {
		if(packet instanceof SPacketSetID) {
			SPacketSetID pac = ((SPacketSetID) packet);
			game.getPlayer().setId(pac.getId());
			game.getPlayer().setPosX(pac.getPosX());
			game.getPlayer().setPosY(pac.getPosY());
		}
		if(packet instanceof SPacketSpawnPlayer) {
			OtherPlayer player = new OtherPlayer();
			SPacketSpawnPlayer pac = ((SPacketSpawnPlayer) packet);
			player.setId(pac.getId());
			player.setPosX(pac.getPosX());
			player.setPosY(pac.getPosY());
			player.setHealth(pac.getHealth());
			player.setConnect(true);
			game.getWorld().getQueue().add(player);
		}
		if(packet instanceof SPacketPlayerPosition) {
			SPacketPlayerPosition pac = (SPacketPlayerPosition) packet;
			OtherPlayer player = game.getWorld().getPlayer(pac.getId());
			if(player == null) return;
			player.setPosX(pac.getPosX());
			player.setPosY(pac.getPosY());
			player.setAngle(pac.getAngle());
		}
		if(packet instanceof SPacketPlayerDisconnect) {
			SPacketPlayerDisconnect pac = (SPacketPlayerDisconnect) packet;
			game.getWorld().getPlayer(pac.getId()).setConnect(false);
		}
		if(packet instanceof SPacketShoot) {
			game.playSound("sfx_laser1.ogg");
			SPacketShoot pac = (SPacketShoot) packet;
			float speed = 1000f;
			float moX = (float) (Math.cos(pac.getAngle()) * speed);
			float moY = (float) (Math.sin(pac.getAngle()) * speed);
			Particle par = new Particle(pac.getPosX(), pac.getPosY(), moX, moY, 10000 - (new Date().getTime() - pac.getSpawnTime()));
			par.setId(pac.getId());
			game.getWorld().getQueue().add(par);
		}
		if(packet instanceof SPacketDamage) {
			SPacketDamage pac = (SPacketDamage) packet;
			Player player = pac.getP_id() == game.getPlayer().getId() ? game.getPlayer() : game.getWorld().getPlayer(pac.getP_id());
			Particle par = game.getWorld().getBullet(pac.getB_id());
			if(player == null || par == null) return;
			player.setHealth(pac.getP_health());
			par.setLifetime(0);
			if (player == game.getPlayer()) {
				game.warning = true;
				game.lastWarning = new Date().getTime();
				System.out.println(game.warning);

			}
		}
	}
}

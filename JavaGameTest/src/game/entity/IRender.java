package game.entity;

import java.awt.Graphics2D;

import game.Game;

public interface IRender {
	public void update(float delta, Game game);
	public void render(Graphics2D g2d, Game game);
}

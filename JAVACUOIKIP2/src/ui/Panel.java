package ui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Panel {
	private int x;
	private int y;
	private List<Panel> childs;
	private int width;
	private int height;
	private Point mouse;
	public Panel() {
		super();
		this.childs = new ArrayList<Panel>();
	}
	public Panel(int x, int y, List<Panel> childs, int width, int height, Point mouse) {
		super();
		this.x = x;
		this.y = y;
		this.childs = childs;
		this.width = width;
		this.height = height;
		this.mouse = mouse;
	}
	public void updateMouse(Point mouse) {
		if(mouse != null) {
			setMouse(mouse);
			for(Panel p : childs) {
				if(isHoverChild(p)) {
					p.updateMouse(new Point(mouse.x - p.getX(), mouse.y - p.y));
				}
			}
		}
	}
	public boolean isHoverChild(Panel child) {
		return getMouse().getX() >= child.getX() && getMouse().getY() >= child.getY()
				&& getMouse().getX() <= child.getX()+child.getWidth() && getMouse().getY() <= child.getY() + child.getHeight();
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public List<Panel> getChilds() {
		return childs;
	}
	public void setChilds(List<Panel> childs) {
		this.childs = childs;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Point getMouse() {
		return mouse;
	}
	public void setMouse(Point mouse) {
		this.mouse = mouse;
	}	
}

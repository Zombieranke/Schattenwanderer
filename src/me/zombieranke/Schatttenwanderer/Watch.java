package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;

public class Watch extends GameObject{
	
	protected int direction;

	public Watch(int x, int y, Image img, int direction) {
		super(x, y, img);
		this.direction = direction;
	}

	@Override
	public void render(Graphics g) {
		Shape seek = new Circle(x,y,20);
		g.fill(seek);
		img.drawCentered(x, y);
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	

}

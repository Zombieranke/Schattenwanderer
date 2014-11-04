package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Watch extends GameObject{
	
	protected int direction;
	protected int radius = 100;

	public Watch(int x, int y, Image img, int direction) {
		super(x, y, img);
		this.direction = direction;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillArc(x-radius, y-radius, radius*2, radius*2, direction, direction+40);
		img.drawCentered(x, y);
	}
	
	@Override
	public void update(int delta){
		
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	

}

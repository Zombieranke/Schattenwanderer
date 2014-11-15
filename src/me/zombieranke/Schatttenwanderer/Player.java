package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player extends GameObject {
	/*Basic Implementation. Needs a lot of work. */
	
	private Shape collisionArea;
	
	public Player(int x, int y, Image img){
		super(x, y, img);
		collisionArea = new Rectangle(x,y,10,10);
	}
	
	public Player(int x, int y){
		super(x, y);
		collisionArea = new Rectangle(x,y,10,10);
	}
	
	public boolean checkCollision(GameObject other)
	{
		return collisionArea.contains(other.getX(),other.getY());
	}
	
	public void render(Graphics g){
		//img.drawCentered(x,y);
		g.setColor(Color.red);
		g.fillRect(x,y,10,10);
	}
	
	public void update(int delta)
	{
		collisionArea = new Rectangle(x,y,10,10);
	}
	
}

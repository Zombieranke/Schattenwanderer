package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Player extends GameObject
{
	/*Basic Implementation. Needs a lot of work. */
	
	public Player(int x, int y, Image img)
	{
		super(x, y, img);
		collisionArea = new Rectangle(x,y,10,10);
	}
	
	public Player(int x, int y)
	{
		super(x, y);
		collisionArea = new Rectangle(x,y,10,10);
	}
	
	public void render(Graphics g)
	{
		//img.drawCentered(x,y);
		g.setColor(Color.red);
		g.fillRect(x,y,10,10);
		/*Color boxCol = new Color(Color.black);
		boxCol.a = 0.5f;
		g.setColor(boxCol);
		g.fill(collisionArea);*/
	}
	
	public void update(int delta)
	{
		collisionArea.setX(x);
		collisionArea.setY(y);
	}
	
}

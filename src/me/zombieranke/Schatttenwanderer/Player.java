package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player extends MovableObject
{
	/*Basic Implementation. Needs a lot of work. */
	
	private static final int DEFAULT_COL_X = 10;
	private static final int DEFAULT_COL_Y = 10;
	
	public Player(int x, int y, Image img)
	{
		super(x, y, img,DEFAULT_COL_X,DEFAULT_COL_Y);
	}
	
	public Player(int x, int y)
	{
		super(x, y,DEFAULT_COL_X,DEFAULT_COL_Y);
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
	
}

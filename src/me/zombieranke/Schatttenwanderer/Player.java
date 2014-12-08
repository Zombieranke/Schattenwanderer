package me.zombieranke.Schatttenwanderer;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Player extends MovableObject
{
	/*Basic Implementation. Needs a lot of work. */
	
	private static final int DEFAULT_COL_X = 20;
	private static final int DEFAULT_COL_Y = 20;
	
	public Player(int x, int y, Image img)
	{
		super(x, y, img,DEFAULT_COL_X,DEFAULT_COL_Y);
	}
	
	public Player(int x, int y)
	{
		super(x, y,DEFAULT_COL_X,DEFAULT_COL_Y);
	}
	
	public Player(int x, int y, Image img,int colX,int colY)
	{
		super(x, y, img,colX,colY);
	}
	public Player(int x, int y, int colX, int colY)
	{
		super(x, y,colX,colY);
	}
	
	public Player(int x, int y, Animation animation, int colX, int colY)
	{
		super(x, y, animation, colX, colY);
	}
	
	public void render(Graphics g)
	{
		//img.drawCentered(x,y);
		animation.draw(x, y);
		/*Color boxCol = new Color(Color.black);
		boxCol.a = 0.5f;
		g.setColor(boxCol);
		g.fill(collisionArea);*/
	}
	
}

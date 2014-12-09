package me.zombieranke.Schatttenwanderer;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Target extends MovableObject
{
	/*Basic Implementation. Needs a lot of work. */
	
	private static final int DEFAULT_COL_X = 10;
	private static final int DEFAULT_COL_Y = 10;
	private boolean mission = false;
	
	public Target(int x, int y, Image img)
	{
		super(x, y, img,DEFAULT_COL_X,DEFAULT_COL_Y);
	}
	
	public Target(int x, int y)
	{
		super(x, y,DEFAULT_COL_X,DEFAULT_COL_Y);
	}
	
	public Target(int x, int y, Image img,int colX,int colY)
	{
		super(x, y, img,colX,colY);
	}
	public Target(int x, int y, int colX, int colY)
	{
		super(x, y,colX,colY);
	}
	
	public Target(int x, int y, Animation animation, int colX, int colY)
	{
		super(x, y, animation, colX, colY);
	}
	
	public Target(int x, int y, Animation animation, Image image, int colX, int colY)
	{
		super(x, y, animation, image, colX, colY);
	}
	
	public void missionAccomplished(boolean mission)
	{
		this.mission = mission;
	}
	
	public void render(Graphics g)
	{
		if (mission==true)
		{
			img.draw(x, y);
		}

		else
		{
			animation.draw(x, y);
		}
	}
	
}
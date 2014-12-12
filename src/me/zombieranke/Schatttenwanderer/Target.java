package me.zombieranke.Schatttenwanderer;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Target extends MovableObject
{
	/*Basic Implementation. Needs a lot of work. */
	
	private static final float DEFAULT_COL_X = 10;
	private static final float DEFAULT_COL_Y = 10;
	private boolean dead = false;
	
	public Target(float x, float y, Image img)
	{
		super(x, y, img,DEFAULT_COL_X,DEFAULT_COL_Y);
	}
	
	public Target(float x, float y)
	{
		super(x, y,DEFAULT_COL_X,DEFAULT_COL_Y);
	}
	
	public Target(float x, float y, Image img,float colX,float colY)
	{
		super(x, y, img,colX,colY);
	}
	public Target(float x, float y, float colX, float colY)
	{
		super(x, y,colX,colY);
	}
	
	public Target(float x, float y, Animation animation, float colX, float colY)
	{
		super(x, y, animation, colX, colY);
	}
	
	public Target(float x, float y, Animation animation, Animation animation2, float colX, float colY)
	{
		super(x, y, animation, animation2, colX, colY);
	}
	
	public void setDead(boolean dead)
	{
		this.dead = dead;
	}
	
	public void render(Graphics g)
	{
		if (dead)
		{
			animation2.draw(x, y);
		}
		else
		{
			animation.draw(x, y);
		}
	}
	
}
package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

public class Exit extends SolidObject
{
	boolean horizontal;
	boolean open;
	
	
	public Exit(int x,int y)
	{
		super(x,y);
		horizontal = true;
		open = false;
	}
	
	public Exit(int x,int y,Animation animation,int colX, int colY)
	{
		super(x,y,animation,colX,colY);
		horizontal = true;
		open = false;
	}
	
	public Exit(int x, int y, Animation animation,int colX, int colY, boolean horizontal)
	{
		super(x,y,animation,colX,colY);
		this.horizontal = horizontal;
		open = false;
	}
	
	public void render(Graphics g)
	{
		animation.draw(x, y);
	}
	
	public boolean getOpen()
	{
		return open;
	}
	
	public void setOpen(boolean open)
	{
		this.open = open;
	}
	
}

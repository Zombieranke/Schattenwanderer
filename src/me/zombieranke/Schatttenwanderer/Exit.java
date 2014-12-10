package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

public class Exit extends SolidObject
{
	boolean open;
	
	
	public Exit(float x,float y)
	{
		super(x,y);
		open = false;
	}
	
	public Exit(float x, float y, Animation animation,float colX, float colY)
	{
		super(x,y,animation,colX,colY);
		open = false;
	}
	
	public void render(Graphics g)
	{
		animation.draw(x, y);
	}
	
	public boolean isOpen()
	{
		return open;
	}
	
	public void setOpen(boolean open)
	{
		this.open = open;
	}
	
}

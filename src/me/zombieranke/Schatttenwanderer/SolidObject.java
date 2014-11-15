package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.*;

public abstract class SolidObject extends GameObject
{
	
	
	public SolidObject(int x,int y)
	{
		super(x,y);
		solid = true;
	}
	
	public SolidObject(int x,int y,Image img)
	{
		super(x,y,img);
		solid = true;
	}
	
	public abstract void render(Graphics g);
	
	
}

package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;

public abstract class SolidObject extends GameObject
{
	public SolidObject(int x, int y, Image img, Shape collisionArea)
	{
		super(x,y,img,collisionArea);
		solid = true;
	}
	
	public SolidObject(int x, int y, Shape collisionArea)
	{
		super(x,y,collisionArea);
		solid = true;
	}
	
	public SolidObject(int x, int y, Image img, int colX, int colY)
	{
		super(x,y,img,colX,colY);
		solid = true;
	}
	
	public SolidObject(int x, int y, int colX, int colY)
	{
		super(x,y,colX,colY);
		solid = true;
	}
	
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
	
	public SolidObject(int x, int y, Animation animation, int colX, int colY)
	{
		super(x,y,animation,colX,colY);
	}
	
	public abstract void render(Graphics g);
	
	
}

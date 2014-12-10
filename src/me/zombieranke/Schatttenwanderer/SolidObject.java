package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;

public abstract class SolidObject extends GameObject
{
	public SolidObject(float x, float y, Image img, Shape collisionArea)
	{
		super(x,y,img,collisionArea);
		solid = true;
	}
	
	public SolidObject(float x, float y, Shape collisionArea)
	{
		super(x,y,collisionArea);
		solid = true;
	}
	
	public SolidObject(float x, float y, Image img, float colX, float colY)
	{
		super(x,y,img,colX,colY);
		solid = true;
	}
	
	public SolidObject(float x, float y, float colX, float colY)
	{
		super(x,y,colX,colY);
		solid = true;
	}
	
	public SolidObject(float x,float y)
	{
		super(x,y);
		solid = true;
	}
	
	public SolidObject(float x,float y,Image img)
	{
		super(x,y,img);
		solid = true;
	}
	
	public SolidObject(float x, float y, Animation animation, float colX, float colY)
	{
		super(x,y,animation,colX,colY);
	}
	
	public abstract void render(Graphics g);
	
	
}

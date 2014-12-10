package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class Watch extends MovableObject
{
	
	protected float direction = 0;
	protected float radius = 100;
	private float angle = 60;
	private Shape sight;

	public Watch(float x, float y, Image img, Shape collisionArea)
	{
		super(x,y,img,collisionArea);;
	}
	
	public Watch(float x, float y, Shape collisionArea)
	{
		super(x,y,collisionArea);
	}
	
	public Watch(float x, float y, Image img, float colX, float colY)
	{
		super(x,y,img,colX,colY);;
	}
	
	public Watch(float x, float y, float colX, float colY)
	{
		super(x,y,colX,colY);
	}
	
	public Watch(float x,float y)
	{
		super(x,y);
	}
	
	public Watch(float x,float y,Image img)
	{
		super(x,y,img);
	}
	
	public Watch(float x, float y, Animation animation, float colX, float colY)
	{
		super(x, y, animation, colX, colY);
	}

	@Override
	public void render(Graphics g)
	{
		Color boxCol = new Color(Color.red);
		boxCol.a = 0.5f;
		g.setColor(boxCol);
		g.fill(sight);	
		animation.draw(x, y);
	}
	
	public Shape getSight()
	{
		return sight;
	}
	
	
	public void updateSight(ArrayList<SolidObject> wall)
	{
		sight = new WatchSightArea(x+colX/2,y+colY/2,radius, direction, angle, wall);
	}

	public float getDirection()
	{
		return direction;
	}

	public void setDirection(float direction)
	{
		this.direction = direction;
		super.setRotation(direction+180);
	}

	public float getRadius()
	{
		return radius;
	}

	public void setRadius(float radius)
	{
		this.radius = radius;
	}
	
	

}

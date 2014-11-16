package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class Watch extends GameObject
{
	
	protected float direction = 0;
	protected float radius = 100;
	private float angle = 60;
	private Wall wall;
	private Shape sight;

	public Watch(int x, int y, Image img, Shape collisionArea, Wall wall)
	{
		super(x,y,img,collisionArea);
		this.wall = wall;
	}
	
	public Watch(int x, int y, Shape collisionArea, Wall wall)
	{
		super(x,y,collisionArea);
		this.wall = wall;
	}
	
	public Watch(int x, int y, Image img, int colX, int colY, Wall wall)
	{
		super(x,y,img,colX,colY);
		this.wall = wall;
	}
	
	public Watch(int x, int y, int colX, int colY, Wall wall)
	{
		super(x,y,colX,colY);
		this.wall = wall;
	}
	
	public Watch(int x,int y, Wall wall)
	{
		super(x,y);
		this.wall = wall;
	}
	
	public Watch(int x,int y,Image img, Wall wall)
	{
		super(x,y,img);
		this.wall = wall;
	}

	@Override
	public void render(Graphics g)
	{
		Color boxCol = new Color(Color.red);
		boxCol.a = 0.5f;
		g.setColor(boxCol);
		g.fill(sight);
		img.drawCentered(x, y);
	}
	
	
	@Override
	public void update(int delta)
	{	
		collisionArea.setX(x);
		collisionArea.setY(y);
	}
	
	public void updateSight(int delta)
	{
		sight = new WatchSightArea(x,y,radius, direction, angle, wall);
	}

	public float getDirection()
	{
		return direction;
	}

	public void setDirection(float direction)
	{
		this.direction = direction;
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

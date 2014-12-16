package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Watch extends MovableObject
{
	
	protected float direction = 0;
	protected float sightRadius = 100;
	private float angle = 60;
	private float playerNoise = 10;
	private WatchSightArea sightCone;
	private Circle hearCircle;

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

	public void init(ArrayList<SolidObject> wall)
	{
		hearCircle = new Circle(x+colX/2,y+colY/2, playerNoise);
		updateSight(wall);
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(new Color(1.0f,0f,0f,0.5f));
		g.fill(sightCone);
		g.setColor(new Color(1.0f,1.0f,0f,0.5f));
		g.fill(hearCircle);
		animation.draw(x, y);
	}
	
	public void addNoise(float deltaNoise)
	{
		playerNoise += deltaNoise;
	}
	
	public void setNoise(float noise)
	{
		playerNoise = noise;
	}
	
	public float getNoise()
	{
		return playerNoise;
	}
	
	public Shape getSightCone()
	{
		return sightCone;
	}
	
	public Shape getHearCircle()
	{
		return hearCircle;
	}
	
	
	public void updateSight(ArrayList<SolidObject> wall)
	{
		sightCone = new WatchSightArea(x+colX/2,y+colY/2,sightRadius, direction, angle, wall);
		hearCircle.setRadius(playerNoise);
		hearCircle.setCenterX(x+colX/2);
		hearCircle.setCenterY(y+colY/2);
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

	public float getSightRadius()
	{
		return sightRadius;
	}

	public void setSightRadius(float radius)
	{
		this.sightRadius = radius;
	}
	
	

}

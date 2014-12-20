package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;

public class Watch extends MovableObject implements Mover
{
	
	protected float direction = 0;
	protected float sightRadius = 100;
	private float angle = 60;
	
	private static int speedAlarm = 2;
	private static int speedWalk = 1;
	
	private boolean alarmed;
	
	/**Permanent radius of the hearing circle*/
	private static final float playerNoiseDefault = 20;
	
	/**Current aberration of the default radius of the hearing circle*/
	private float playerNoise;
	
	private WatchSightArea sightCone;
	private Circle hearCircle;
	private AStarPathFinder aPath;
	private float[] points;
	private int currentStep = 0;
	private Path p;
	private int pCur = 0;

	public Watch(float x, float y, Image img, Shape collisionArea)
	{
		super(x,y,img,collisionArea);
	}
	
	public Watch(float x, float y, Shape collisionArea)
	{
		super(x,y,collisionArea);
	}
	
	public Watch(float x, float y, Image img, float colX, float colY)
	{
		super(x,y,img,colX,colY);
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
	
	public Watch(float x, float y, Animation animation, float colX, float colY, float[] points, AStarPathFinder aPath)
	{
		super(x, y, animation, colX, colY);
		this.points = points;
		this.aPath = aPath;
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
	
	public int getSpeed()
	{
		if(alarmed)
		{
			return speedAlarm;
		}
		return speedWalk;
	}
	
	public void setAlarmed(boolean alarm)
	{
		alarmed = alarm;
	}
	
	public boolean getAlarmed()
	{
		return alarmed;
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
	
	public float getNoiseDefault()
	{
		return playerNoiseDefault;
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
	
	public void setPath(Path  p)
	{
		this.p=p;
	}
	
	public void move(float speed)
	{
		if(p == null)
		{
			calculatePath();
		}
		System.out.println(getX()+","+getY()+","+points[currentStep]+","+points[currentStep+1]);
		if(getX()==points[currentStep]*8 && getY()==points[currentStep+1]*8)
		{
			currentStep +=2;
			System.out.println("inc");
			if(currentStep > points.length-1)
			{
				System.out.println("Wrap around");
				currentStep = 0;
			}
			calculatePath();
		}
		
		if(getX()==p.getX(pCur)*8 && getY()==p.getY(pCur)*8)
		{
			pCur++;
		}
		
		Vector2f goal = new Vector2f(p.getX(pCur)*8 - getX(), p.getY(pCur)*8 - getY());
		setDirection((float) goal.getTheta());
		//System.out.println(getX()+","+getY()+","+p.getX(pCur)*8f+","+p.getY(pCur)*8f);
		
		if(goal.length()<speed)
		{
			x += goal.x;
			y += goal.y;
			speed -= goal.length();
			move(speed);
		}
		else
		{
			goal.normalise().scale(speed);
			x += goal.x;
			y += goal.y;
		}
		
	}
	
	public void calculatePath()
	{
		System.out.println(currentStep);
		p = aPath.findPath(this, Math.round(getX()/8), Math.round(getY()/8), Math.round(points[currentStep]), Math.round(points[currentStep+1]));
		pCur = 0;
	}
	
	public Path getPath()
	{
		return p;
	}

}

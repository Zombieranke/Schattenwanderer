package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;
import java.util.Random;

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
	
	private static float speedAlarm = 1.5f;
	private static float speedWalk = 1;
	
	private boolean alarmed;
	
	/**Permanent radius of the hearing circle*/
	private static final float playerNoiseDefault = 20;
	
	/**Current aberration of the default radius of the hearing circle*/
	private float playerNoise;
	
	private boolean stopped;
	private WatchSightArea sightCone;
	private Circle hearCircle;
	private AStarPathFinder aPath;
	private Vector2f[] points;
	private int currentStep = 0;
	private Path p;
	private int pCur = 0;
	private Vector2f playerLastKnown;

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
	
	public Watch(float x, float y, Animation animation, float colX, float colY, Vector2f[] points, AStarPathFinder aPath)
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
	
	public float getSpeed()
	{
		if(alarmed)
		{
			return speedAlarm;
		}
		return speedWalk;
	}
	
	public void setPLayerLastKnown(Vector2f plk)
	{
		this.playerLastKnown = plk;
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
	
	
	public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	public void updateSight(ArrayList<SolidObject> wall)
	{
		sightCone = new WatchSightArea(x+colX/2,y+colY/2,sightRadius, direction, angle, wall, 20);
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
	
	public void move(ArrayList<SolidObject> solids, Exit exit)
	{
		if(!stopped)
		{
			if(alarmed)
			{
				if(p != null)
				{
					followPath(speedAlarm, solids, exit);
				}
				else if(playerLastKnown == null)
				{
					
					Random rnd = new Random();
					Vector2f move = new Vector2f(speedAlarm,0);
					move.setTheta(direction);
					float rndMove = rnd.nextFloat();
					if(rndMove<0.98){
						if(canMove(move.x,move.y,solids,exit))
						{
							x += move.x;
							y += move.y;
						}
						else
						{
							if(rndMove<0.5)
							{
								while(!canMove(move.x,move.y,solids,exit))
								{
									setDirection(direction + 45);
									move.setTheta(direction);
								}
							}
							else
							{
								while(!canMove(move.x,move.y,solids,exit))
								{
									setDirection(direction - 45);
									move.setTheta(direction);
								}
							}
						}
						super.setRotation(direction+180);
					}
					else if(rndMove<0.99)
					{
						do
						{
							setDirection(direction + 45);
							move.setTheta(direction);
						}
						while(!canMove(move.x,move.y,solids,exit));
					}
					else
					{
						do
						{
							setDirection(direction - 45);
							move.setTheta(direction);
						}
						while(!canMove(move.x,move.y,solids,exit));
					}
						
				}
				else
				{
					p = new Path();
					p.appendStep(Math.round(playerLastKnown.x/8), Math.round(playerLastKnown.y/8));
					pCur = 0;
					if(Math.round(playerLastKnown.x/8) == Math.round(x/8) && Math.round(playerLastKnown.y/8) == Math.round(y/8))
					{
						playerLastKnown = null;
					}
					followPath(speedAlarm, solids, exit);
					p = null;
				}			
			}
			else
			{
				while(p == null)
				{
					calculatePath(points[currentStep],solids, exit);
					if(p == null)
					{
						currentStep++;
						if(currentStep > points.length-1)
						{
							//System.out.println("Wrap around");
							currentStep = 0;
						}
					}
				}
				//System.out.println(getX()+","+getY()+","+points[currentStep]+","+points[currentStep+1]);
				if(getX()==points[currentStep].x*8 && getY()==points[currentStep].y*8)
				{
					currentStep +=1;
					//System.out.println("inc");
					if(currentStep > points.length-1)
					{
						//System.out.println("Wrap around");
						currentStep = 0;
					}
					calculatePath(points[currentStep],solids,exit);
				}
				followPath(speedWalk, solids, exit);
			}
		}
	}
	
	public void followPath(float speed, ArrayList<SolidObject> solids, Exit exit)
	{
		if(getX()==p.getX(pCur)*8 && getY()==p.getY(pCur)*8 && pCur != p.getLength()-1)
		{
			pCur++;
		}
		
		Vector2f goal = new Vector2f(p.getX(pCur)*8 - getX(), p.getY(pCur)*8 - getY());
		setDirection((float) goal.getTheta());
		//System.out.println(getX()+","+getY()+","+p.getX(pCur)*8f+","+p.getY(pCur)*8f);
		
		
		
		if(goal.length()<=speed)
		{
			if(canMove(goal.x,goal.y,solids, exit))
			{
				move(goal.x,goal.y);
			}
			else
			{
				double angle = goal.getTheta();
				angle = Math.floor(angle/90) * 90;
				goal.setTheta(angle);
				
				if(canMove(goal.x,goal.y,solids, exit))
				{
					if(goal.length()<=speed)
					{
						x += goal.x;
						y += goal.y;
						
						if(pCur == p.getLength()-1)
						{
							p = null;
							//System.out.println("Set to null");
						}
					}
					else
					{
						goal.normalise().scale(speed);
						x += goal.x;
						y += goal.y;
					}
				}
				else
				{
					goal.setTheta(angle + 90);
					
					if(canMove(goal.x,goal.y,solids, exit))
					{
						if(goal.length()<=speed)
						{
							x += goal.x;
							y += goal.y;
							
							if(pCur == p.getLength()-1)
							{
								p = null;
								//System.out.println("Set to null");
							}
						}
						else
						{
							goal.normalise().scale(speed);
							x += goal.x;
							y += goal.y;
						}
					}
				}
			}
			
			if(pCur == p.getLength()-1)
			{
				p = null;
				//System.out.println("Set to null");
			}
		}
		else
		{
			goal.normalise().scale(speed);
			if(canMove(goal.x,goal.y,solids, exit))
			{
				move(goal.x,goal.y);
			}
			else
			{
				double angle = goal.getTheta();
				angle = Math.floor(angle/90) * 90;
				goal.setTheta(angle);
				
				if(canMove(goal.x,goal.y,solids, exit))
				{
					if(goal.length()<=speed)
					{
						x += goal.x;
						y += goal.y;
						
						if(pCur == p.getLength()-1)
						{
							p = null;
							//System.out.println("Set to null");
						}
					}
					else
					{
						goal.normalise().scale(speed);
						x += goal.x;
						y += goal.y;
					}
				}
				else
				{
					goal.setTheta(angle + 90);
					
					if(canMove(goal.x,goal.y,solids, exit))
					{
						if(goal.length()<=speed)
						{
							x += goal.x;
							y += goal.y;
							
							if(pCur == p.getLength()-1)
							{
								p = null;
								//System.out.println("Set to null");
							}
						}
						else
						{
							goal.normalise().scale(speed);
							x += goal.x;
							y += goal.y;
						}
					}
				}
			}
		}
		
	}
	

	public void calculatePath(Vector2f v, ArrayList<SolidObject> solids, Exit exit)
	{
		//System.out.println(currentStep);
		Vector2f curPos = new Vector2f(Math.round(getX()/8),Math.round(getY()/8)); 
		if(!canMoveAbsolute(curPos.x*8,curPos.y*8, solids, exit))
		{
			curPos = findValidPos(curPos, solids, exit);
			if(curPos ==null)
			{
				System.out.println("CurPos is null");
			}
		}
		
		if(!canMoveAbsolute(v.x*8,v.y*8, solids, exit))
		{	
			v = findValidPos(v, solids, exit);
			if(v ==null)
			{
				System.out.println("V is null");
			}
		}
		
		p=aPath.findPath(this, Math.round(curPos.x), Math.round(curPos.y), Math.round(v.x), Math.round(v.y));
		pCur = 0;
	}
	
	public Vector2f findValidPos(Vector2f v, ArrayList<SolidObject> solids, Exit exit)
	{
		Vector2f toAdd = new Vector2f(1f,0f);
		for(int i = 0; i<4;i++)
		{
			toAdd.setTheta(i*90);
			if(canMoveAbsolute((v.x+ toAdd.x)*8 ,(v.y + toAdd.y)*8, solids, exit))
			{
				return v.add(toAdd);
			}
		}
		return null;
	}
	
	public Path getPath()
	{
		return p;
	}

}

package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class MovableObject extends GameObject
{
	
	/**Indicates if the object is moving*/
	protected boolean moving = false;
	
	public MovableObject(float x, float y, Image img, Shape collisionArea)
	{
		super(x,y,img,collisionArea);
		solid = true;
	}
	
	public MovableObject(float x, float y, Shape collisionArea)
	{
		super(x,y,collisionArea);
	}
	
	public MovableObject(float x, float y, Image img, float colX, float colY)
	{
		super(x,y,img,colX,colY);
	}
	
	public MovableObject(float x, float y, float colX, float colY)
	{
		super(x,y,colX,colY);
	}
	
	public MovableObject(float x,float y)
	{
		super(x,y);
	}
	
	public MovableObject(float x,float y,Image img)
	{
		super(x,y,img);
	}
	
	public MovableObject(float x, float y, Animation animation, float colX, float colY)
	{
		super(x, y, animation, colX, colY);
	}
	
	public MovableObject(float x, float y, Animation animation, Animation animation2, float colX, float colY)
	{
		super(x, y, animation, animation2, colX, colY);
	}

	public abstract void render(Graphics g);
	
	public void setRotation(float degrees)
	{
		animation.getCurrentFrame().setRotation(degrees);
	}
	
	public boolean canMove(float deltaX,float deltaY, ArrayList<SolidObject> walls,Exit exit)
	{
		collisionArea.setX(x+deltaX);
		collisionArea.setY(y+deltaY);
		for(SolidObject w : walls){
			if(w.checkCollision(collisionArea)){
				update();
				return false;
			}
		}
		if(exit.checkCollision(collisionArea)&&exit.isOpen()==false)
		{
			return false;
		}
		return true;
	}
	
	public boolean canMoveAbsolute(float absX,float absY, ArrayList<SolidObject> walls,Exit exit)
	{
		Rectangle colArea = new Rectangle(absX,absY,colX,colY); 
		for(SolidObject w : walls){
			if(w.checkCollision(colArea)){
				update();
				return false;
			}
		}
		
		if(exit.checkCollision(colArea)&&exit.isOpen()==false)
		{
			return false;
		}
		return true;
	}
	
	public void move(float x, float y){
		this.x+=x;
		this.y+=y;
	}
	public void update()
	{
		collisionArea.setX(x);
		collisionArea.setY(y);
	}
	
	public void setMoving(boolean moving)
	{
		this.moving = moving;
	}
	
	public boolean isMoving()
	{
		return moving;
	}
	
	
}

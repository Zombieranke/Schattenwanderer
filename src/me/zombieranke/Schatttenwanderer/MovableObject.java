package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public abstract class MovableObject extends GameObject {

	
	public MovableObject(int x, int y, Image img, Shape collisionArea)
	{
		super(x,y,img,collisionArea);
		solid = true;
	}
	
	public MovableObject(int x, int y, Shape collisionArea)
	{
		super(x,y,collisionArea);
	}
	
	public MovableObject(int x, int y, Image img, int colX, int colY)
	{
		super(x,y,img,colX,colY);
	}
	
	public MovableObject(int x, int y, int colX, int colY)
	{
		super(x,y,colX,colY);
	}
	
	public MovableObject(int x,int y)
	{
		super(x,y);
	}
	
	public MovableObject(int x,int y,Image img)
	{
		super(x,y,img);
	}
	
	public abstract void render(Graphics g);
	
	public boolean canMove(int deltaX,int deltaY, ArrayList<SolidObject> walls)
	{
		collisionArea.setX(x+deltaX);
		collisionArea.setY(y+deltaY);
		for(SolidObject w : walls){
			if(w.checkCollision(collisionArea)){
				update();
				return false;
			}
		}
		return true;
	}
	
	public void move(int x, int y){
		this.x+=x;
		this.y+=y;
	}
	public void update()
	{
		collisionArea.setX(x);
		collisionArea.setY(y);
	}
	
	
}

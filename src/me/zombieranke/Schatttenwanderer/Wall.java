package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;

public class Wall extends SolidObject
{	
		
	public Wall(int x, int y, Image img, Shape collisionArea){
		super(x,y,img,collisionArea);
	}
	
	public Wall(int x, int y, Shape collisionArea){
		super(x,y,collisionArea);
	}
	
	public Wall(int x, int y, Image img, int colX, int colY){
		super(x,y,img,colX,colY);
	}
	
	public Wall(int x, int y, int colX, int colY){
		super(x,y,colX,colY);
	}
	
	public Wall(int x,int y)
	{
		super(x,y);
	}
	
	public Wall(int x,int y,Image img)
	{
		super(x,y,img);
	}
	
	public void render(Graphics g)
	{
		img.draw(x,y);
		/*Color boxCol = new Color(Color.black);
		boxCol.a = 0.5f;
		g.setColor(boxCol);
		g.fill(collisionArea);*/
		//g.setColor(Color.green);
		//g.fillRect(x,y,16,16);
	}
	
}

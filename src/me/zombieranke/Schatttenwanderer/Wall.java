package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import me.zombieranke.utils.Direction;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;

public class Wall extends SolidObject
{
	

	public Wall(int x, int y, Image img, Shape collisionArea)
	{
		super(x,y,img,collisionArea);
	}
	
	public Wall(int x, int y, Shape collisionArea)
	{
		super(x,y,collisionArea);
	}
	
	public Wall(int x, int y, Image img, int colX, int colY)
	{
		super(x,y,img,colX,colY);
	}
	
	public Wall(int x, int y, int colX, int colY)
	{
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
	
	public static ArrayList<Wall> createWall(int startX,int startY,int segmentCount,Direction dir) throws SlickException{
		ArrayList<Wall> wall = new ArrayList<Wall>();
		
		for(int i = 0; i<segmentCount;i++){
			Wall w = new Wall(startX,startY,new Image("res/Wall_Type_1.jpg"),32,32);
			wall.add(w);
			switch(dir){
			
			case EAST:
				startX+=32;
				break;
			
			case NORTH:
				startY-=32;
				break;
			
			case WEST:
				startX-=32;
				break;
				
			case SOUTH:
				startY+=32;
			
			default:
				break;
			}
		}
		
		
		return wall;
		
	}
	
}

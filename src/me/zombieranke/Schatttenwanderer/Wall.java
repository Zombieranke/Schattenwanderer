package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Wall extends SolidObject
{	
	
	public Wall(int x, int y) throws SlickException
	{
		super(x,y);
		//img = new Image("res/Wall_Type_1.jpg"); 
	}
	
	
	public Wall(int x, int y, Image img) throws SlickException
	{
		super(x,y,img);
	}
	
	public void render(Graphics g)
	{
		//img.draw(x,y);
		g.setColor(Color.green);
		g.fillRect(x,y,16,16);
	}
	
}

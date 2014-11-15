package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Wall extends SolidObject
{	
	
	public Wall(int x, int y) throws SlickException
	{
		super(x,y);
		img = new Image("res/colour-chrome.jpg");
	}
	
	public void render(Graphics g)
	{
		img.draw(x,y);
	}
	
}

package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;


public class Schattenwanderer extends BasicGame {

	public Schattenwanderer(){
		super("Schattenwanderer");
	}

	public static void main(String[] args) throws SlickException{
		AppGameContainer container = new AppGameContainer(new Schattenwanderer());
		container.setDisplayMode(1024, 768, false);
		container.start();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.fill(new Circle(100,100,10));
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}

package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;


public class Schattenwanderer extends BasicGame {
	
	private Watch w;
	

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
		w = new Watch(100,100, new Image("res/watch_placeholder.png"),0);
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.setColor(Color.pink);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		w.render(g);
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		Input input = container.getInput();
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			container.exit();
		}
		
	}

}

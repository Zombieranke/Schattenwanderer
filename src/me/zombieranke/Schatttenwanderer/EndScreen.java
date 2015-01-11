package me.zombieranke.Schatttenwanderer;

import me.zombieranke.utils.Ressources;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class EndScreen extends BasicGameState {

	private int leaveTime = 0; 
	private final static int ID = 0;
	private Image background;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		background = Ressources.BACKGROUND_MENU;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		background.draw();
		g.setColor(Color.white);
		g.drawString("Thanks for playing the game", 500, 512);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		leaveTime+=delta;
		if(leaveTime>1000)
		{
			container.exit();
		}

	}

	@Override
	public int getID() {
		return ID;
	}

}

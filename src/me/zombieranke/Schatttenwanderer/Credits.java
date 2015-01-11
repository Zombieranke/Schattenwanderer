/*package me.zombieranke.Schatttenwanderer;

import me.zombieranke.utils.Ressources;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Credits extends BasicGameState
{
	private Image background;
	private static final int ID = 7;
	private int shiftUpwards = 1300;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = Ressources.BACKGROUND_CREDITS;
	}
	
	@Override
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException
	{
		background.draw();
		g.setColor(Color.white);
		g.drawString("Game Director - Arthur Bouz\n\nLead Programmer - Christoph Majcen\n\nVisual Artist - Moritz Fink\n\nAudio Artist - Patrick Kerschbaumer", container.getWidth()/2-200, shiftUpwards);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		shiftUpwards -= 1;
		
		if(shiftUpwards<-50)
		{
			game.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
}*/
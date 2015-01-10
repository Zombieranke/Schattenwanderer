package me.zombieranke.Schatttenwanderer;

import me.zombieranke.utils.Ressources;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Victory extends BasicGameState
{
	private Image background;
	private Animation blood;
	private static final int ID = 6;

	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = Ressources.BACKGROUND_SUCCESS;
		blood = new Animation(Ressources.GAME_WON_SPRITESHEET, 100);
	}
	
	@Override
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException
	{
		background.draw();
		blood.draw(478, 780);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		blood.update(delta);
		blood.start();
		if(blood.getFrame() == 48)
		{
			blood.setCurrentFrame(25);
		}
		Input input = container.getInput();
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || input.isKeyPressed(Input.KEY_ESCAPE))
		{
			blood.setCurrentFrame(0);
			blood.stop();
			game.enterState(7);
		}
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
}
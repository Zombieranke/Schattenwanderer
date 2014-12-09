package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Fail extends BasicGameState
{
	private Image background;
	private int ID = 3;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = new Image("/res/Background_Fail.jpg");
	}
	
	@Override
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException
	{
		background.draw(); //Hier wäre es cool, wenn das Bild reinfadet statt sofort ganz da zu sein
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		Input input = container.getInput();
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || input.isKeyPressed(Input.KEY_ESCAPE))
		{
			game.enterState(1);
		}
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
}
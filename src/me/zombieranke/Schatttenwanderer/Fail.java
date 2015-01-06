package me.zombieranke.Schatttenwanderer;

import me.zombieranke.utils.Ressources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Fail extends BasicGameState
{
	/**The background image for the fail screen*/
	private Image background;
	
	/**The ID of this state*/
	private int ID = 5;
	
	/**The ID this state was entered from*/
	private int lastID = 1;
	
	/**Set from which state this state was entered
	 * 
	 * @param last The ID of the state which entered this state
	 */
	public void setLast(int last){
		lastID = last;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = Ressources.BACKGROUND_FAIL;
	}
	
	@Override
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException
	{
		background.draw();
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		Input input = container.getInput();
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) || input.isKeyPressed(Input.KEY_ESCAPE))
		{
			game.enterState(lastID);
		}
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
}

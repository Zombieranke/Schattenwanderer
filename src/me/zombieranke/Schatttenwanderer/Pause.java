package me.zombieranke.Schatttenwanderer;



import me.zombieranke.utils.Ressources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Pause extends BasicGameState
{
	private Image background;
	private Image pause_Image;
	
	private static final int ID = 8;

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
		background = Ressources.UI_BACKGROUND;
		pause_Image = Ressources.PAUSE_IMAGE;
	}
	
	@Override
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException
	{
		background.draw();
		pause_Image.draw(container.getWidth()/2-250, container.getHeight()/2-250);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		Input input = container.getInput();
		if(input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_P))
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
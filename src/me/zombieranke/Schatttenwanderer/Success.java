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

public class Success extends BasicGameState
{
	private Image background;
	private Animation blood;
	private static final int ID = 4;
	
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
			//System.out.println("Entering " + lastID + 1);
			if(lastID - LevelHandler.levelOffset < LevelHandler.levelCount)
			{
				game.enterState(lastID + 1);
			}
			else
			{
				game.enterState(1);
			}
		}
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
}
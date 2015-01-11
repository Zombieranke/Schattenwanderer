package me.zombieranke.Schatttenwanderer;



import me.zombieranke.utils.Ressources;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Pause extends BasicGameState
{
	private Image background;
	
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
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		background = Ressources.UI_BACKGROUND;
	}
	
	@Override
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException
	{
		background.draw();
		g.setColor(Color.green);
		TrueTypeFont usedFont = new TrueTypeFont(new Font("Times New Roman", 0, 30), true);
		g.setFont(usedFont);
		g.drawString("Arrow keys to move", container.getWidth()/2-usedFont.getWidth("Arrow keys to move")/2, 230);
		g.drawString("C to stealth", container.getWidth()/2-usedFont.getWidth("C to stealth")/2, 260);
		g.drawString("V to sprint", container.getWidth()/2-usedFont.getWidth("V to sprint")/2, 290);
		g.drawString("B to toggle levers", container.getWidth()/2-usedFont.getWidth("B to toggle levers")/2, 320);
		g.drawString("Shift to sneak", container.getWidth()/2-usedFont.getWidth("Shift to sneak")/2, 350);
		usedFont = new TrueTypeFont(new Font("Times New Roman", 0, 60), true);
		g.setFont(usedFont);
		g.drawString("PAUSE", container.getWidth()/2-usedFont.getWidth("PAUSE")/2, 900);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		Input input = container.getInput();
		if(input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_P) || input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
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
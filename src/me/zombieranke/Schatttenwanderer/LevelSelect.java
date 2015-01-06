package me.zombieranke.Schatttenwanderer;

import java.io.IOException;
import java.util.ArrayList;

import me.zombieranke.utils.Ressources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SavedState;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelSelect extends BasicGameState {
	
	private static final int ID = 2;
	

	/**The background image for the menu*/
	private Image background;
	
	private ArrayList<MenuFunc> gui = new ArrayList<MenuFunc>();
	
	private SavedState st;
	
	

	@Override
	public void enter(GameContainer container, StateBasedGame game)
	{
		try
		{
			st.load();
		}
		catch(IOException io)
		{
			io.printStackTrace();
		}
		
		double levelMax = st.getNumber("lastSuccessful");
		
		for(MenuFunc m : gui){
			if(m.getState() - LevelHandler.levelOffset <= levelMax + 1)
			{
				m.setLock(false);
			}
			else
			{
				m.setLock(true);
			}
		}
		
	}
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		background = Ressources.BACKGROUND_MENU;
		
		st = new SavedState("/Schattenwanderer_State");
		
		gui.add(new MenuFunc(new MouseOverArea(container, Ressources.LEVEL1_UNLIGHTED, 112, 112),Ressources.LEVEL1_LIGHTED,LevelHandler.levelOffset+1));
		gui.add(new MenuFunc(new MouseOverArea(container, Ressources.LEVEL2_UNLIGHTED, 112, 112),Ressources.LEVEL2_LIGHTED,LevelHandler.levelOffset+2));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw();

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			game.enterState(1);
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}

package me.zombieranke.Schatttenwanderer;

import java.io.IOException;
import java.util.ArrayList;

import me.zombieranke.utils.Ressources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SavedState;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelSelect extends BasicGameState {
	
	private static final int ID = 2;
	

	/**The background image for the menu*/
	private Image background;
	
	private ArrayList<MenuFunc> gui;
	
	private SavedState st;
	
	private MenuFunc backButton;
	

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
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
		
		gui = new ArrayList<MenuFunc>();
		
		int row = 0;
		
		backButton = new MenuFunc(new MouseOverArea(container, Ressources.BACK_UNLIGHTED, 50, 500), Ressources.BACK_LIGHTED, 1);
		
		for(int i = 1; i<=LevelHandler.levelCount;i++)
		{
			gui.add(new MenuFunc(new MouseOverArea(container, new Image("res/Level" + i + "_Unlighted.png"), 240 + ((i-1)%3)*300, 112 + row *227),new Image("res/Level" + i + "_Lighted.png"),LevelHandler.levelOffset+i));
			if(i%3==0)
			{
				row++;
			}
		}
		
		for(MenuFunc m : gui){
			if(m.getState() - LevelHandler.levelOffset <= levelMax + 1)
			{
				m.setLock(false);
				int levelID = m.getState() - LevelHandler.levelOffset;
				m.setNormalImage(new Image("res/Level" + levelID + "_Unlighted.png"));
				
				m.setMouseDownImage(new Image("res/Level" + levelID + "_Unlighted.png"));
			}
			else
			{
				m.setLock(true);
				m.setNormalImage(Ressources.LEVEL_GREYED);
				m.setMouseOverImage(Ressources.LEVEL_GREYED);
				m.setMouseDownImage(Ressources.LEVEL_GREYED);
			}
		}
		
	}
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		background = Ressources.BACKGROUND_MENU;
		
		st = new SavedState("/Schattenwanderer_State");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw();
		backButton.draw(g, container);
		
		for(MenuFunc m : gui)
		{
			m.draw(g, container);
		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) // If Mouse is pressed and mouse is over the mouseoverarea
		{
			if (backButton.isMouseOver())
			{
				game.enterState(backButton.getState());
			}
			for(MenuFunc m : gui)
			{
				if (m.isMouseOver() && !m.isLocked())
				{
					game.enterState(m.getState());
				}
			}
		}
		
		
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			game.enterState(1);
		}
		

	}

	@Override
	public int getID() {
		return ID;
	}

}

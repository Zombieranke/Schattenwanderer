package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import me.zombieranke.utils.Ressources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameMenu extends BasicGameState
{
	
	/**The background image for the menu*/
	private Image background;
	
	private ArrayList<MenuFunc> gui = new ArrayList<MenuFunc>();
	
	private Music menuMusic;
	
	/**The ID of this state*/
	private int ID = 1;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = Ressources.BACKGROUND_MENU;
		
		// Initialisiere die Menüpunkte als MouseOverArea und gib ihnen ein Standard und ein mouseover image
		gui.add(new MenuFunc(new MouseOverArea(container, Ressources.NEW_GAME_UNLIGTHED, 
															130, 110), 
															Ressources.NEW_GAME_LIGHTED,LevelHandler.levelOffset + 1));
		gui.add(new MenuFunc(new MouseOverArea(container, Ressources.LEVELS_UNLIGHTED, 
														   300, 300), 
														   Ressources.LEVELS_LIGHTED, 2));
		gui.add(new MenuFunc(new MouseOverArea(container, Ressources.OPTIONS_UNLIGHTED, 
															230, 480), 
															Ressources.OPTIONS_LIGHTED, 3));
		gui.add(new MenuFunc(new MouseOverArea(container, Ressources.EXIT_UNLIGHTED,
														 450, 660),
														 Ressources.EXIT_LIGHTED, 0));	
		
		menuMusic = Ressources.MENU_MUSIC;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		background.draw();
		
		// Draw Menu points
		for(MenuFunc m :gui)
		{
			m.draw(g, container);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		// Update option points to react to mouseovers
		
		Input input = container.getInput();
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) // If Mouse is pressed and mouse is over the mouseoverarea
		{
			for(MenuFunc m : gui)
			{
				if (m.isMouseOver())
				{
					game.enterState(m.getState());
				}
			}
		}
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			game.enterState(0);
		}
		
	}
	
	 @Override
	 public void enter(GameContainer container, StateBasedGame game)
	 {
		 menuMusic.loop(1f,Ressources.Volume);
	 }
	 
	 @Override
	 public void leave(GameContainer container, StateBasedGame game)
	 {
		 menuMusic.stop();
	 }

	@Override
	public int getID()
	{
		return ID;
	}

}

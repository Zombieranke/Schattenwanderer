package me.zombieranke.Schatttenwanderer;

import me.zombieranke.utils.Ressources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameMenu extends BasicGameState
{
	
	/**The background image for the menu*/
	private Image background;
	
	/**The button for New Game*/
	private MenuFunc NewGame;
	
	/**The button for Levels*/
	private MenuFunc Levels;
	
	/**The button for Options*/
	private MenuFunc Options;
	
	/**The button for Exit*/
	private MenuFunc Exit;
	
	/**The ID of this state*/
	private int ID = 1;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = Ressources.BACKGROUND_MENU;
		
		// Initialisiere die Menüpunkte als MouseOverArea und gib ihnen ein Standard und ein mouseover image
		NewGame = new MenuFunc(new MouseOverArea(container, Ressources.NEW_GAME_UNLIGTHED, 
															130, 110), 
															Ressources.NEW_GAME_LIGHTED);
		Levels = new MenuFunc(new MouseOverArea(container, Ressources.LEVELS_UNLIGHTED, 
														   300, 300), 
														   Ressources.LEVELS_LIGHTED);
		Options = new MenuFunc(new MouseOverArea(container, Ressources.OPTIONS_UNLIGHTED, 
															230, 480), 
															Ressources.OPTIONS_LIGHTED);
		Exit = new MenuFunc(new MouseOverArea(container, Ressources.EXIT_UNLIGHTED,
														 450, 660),
														 Ressources.EXIT_LIGHTED);													
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		background.draw();
		
		// Draw Menu points
		NewGame.draw(g, container);
		Levels.draw(g, container);
		Options.draw(g, container);
		Exit.draw(g, container);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		// Update option points to react to mouseovers
		NewGame.update(delta);
		Levels.update(delta);
		Options.update(delta);
		Exit.update(delta);
		
		Input input = container.getInput();
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && NewGame.isMouseOver()) // If Mouse is pressed and mouse is over the mouseoverarea
		{
			game.enterState(6);
		}
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && Exit.isMouseOver())
		{
			container.exit();
		}
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			container.exit();
		}
		
	}

	@Override
	public int getID()
	{
		return ID;
	}

}

package me.zombieranke.Schatttenwanderer;

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
	
	//Image Declarations
	private Image background;
	
	// Class Objects
	private MenuFunc NewGame;
	private MenuFunc Levels;
	private MenuFunc Options;
	private MenuFunc Exit;
	
	private int ID = 1;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = new Image("res/Background_Menu.jpg");
		
		// Initialisiere die Menüpunkte als MouseOverArea und gib ihnen ein Standard und ein mouseover image
		NewGame = new MenuFunc(new MouseOverArea(container, new Image("res/New_Game_Unlighted.png"), 
															130, 110), 
															new Image("res/New_Game_Lighted.png"));
		Levels = new MenuFunc(new MouseOverArea(container, new Image("res/Levels_Unlighted.png"), 
														   300, 300), 
														   new Image("res/Levels_Lighted.png"));
		Options = new MenuFunc(new MouseOverArea(container, new Image("res/Options_Unlighted.png"), 
															230, 480), 
															new Image("res/Options_Lighted.png"));
		Exit = new MenuFunc(new MouseOverArea(container, new Image("res/Exit_Unlighted.png"),
														 450, 660),
														 new Image("res/Exit_Lighted.png"));													
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

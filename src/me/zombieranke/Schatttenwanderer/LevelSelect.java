package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import me.zombieranke.utils.Ressources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class LevelSelect extends BasicGameState {
	
	private static final int ID = 2;
	

	/**The background image for the menu*/
	private Image background;
	
	private ArrayList<MenuFunc> gui = new ArrayList<MenuFunc>();
	
	private Music menuMusic;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		
		
		menuMusic = Ressources.MENU_MUSIC;

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub

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
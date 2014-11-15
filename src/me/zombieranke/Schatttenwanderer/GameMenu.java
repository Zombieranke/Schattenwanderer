package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameMenu extends BasicGameState {
	
	//Image Deklarations
	private Image background;
	
	// Class Objects
	private MenuFunc NewGame;
	private MenuFunc Levels;
	private MenuFunc Options;
	private MenuFunc Exit;
	
	private int ID = 1;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		background = new Image("res/Background.jpg");
		NewGame = new MenuFunc(new MouseOverArea(container, new Image("res/New_Game_Unlighted.png"), 130, 150), new Image("res/New_Game_Lighted.png"));
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		background.draw();
		NewGame.draw(g, container);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		NewGame.update(delta);
		Input input = container.getInput();
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON) && NewGame.IsMouseOver()){
			game.enterState(2);
		}
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			container.exit();
		}
		
	}

	@Override
	public int getID() {
		return ID;
	}

}

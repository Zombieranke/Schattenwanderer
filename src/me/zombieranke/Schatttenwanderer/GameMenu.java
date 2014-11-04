package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameMenu extends BasicGameState {
	
	private int ID = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString("Hier koennte ihr Menu entstehen", 100, 100);
		g.drawString("Press 2 to enter game", 100, 115);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		if(input.isKeyPressed(Input.KEY_2)){
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

package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Level extends BasicGameState {
	
	private int ID = 2;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString("Hier wird ein Spiel entstehen", 100, 100);
		g.drawString("Press 1 to enter Menu", 100, 115);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		if(input.isKeyPressed(Input.KEY_1)){
			game.enterState(1);
		}
		if(input.isKeyPressed(Input.KEY_ESCAPE)){
			container.exit();
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}

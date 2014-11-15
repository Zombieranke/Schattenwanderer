package me.zombieranke.Schatttenwanderer;



import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Level extends BasicGameState {
	
	private int ID = 2;
	private Wall wall;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		wall = new Wall(500,500);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.setColor(Color.black);
		g.drawString("Hier wird ein Spiel entstehen", 100, 100);
		g.drawString("Press 1 to enter Menu", 100, 115);
		wall.render(g);
		
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
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

package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class Schattenwanderer extends StateBasedGame {
		

	public Schattenwanderer(){
		super("Schattenwanderer");
	}

	public static void main(String[] args){
		try{
		AppGameContainer container = new AppGameContainer(new Schattenwanderer());
		container.setDisplayMode(1024, 768, false);
		container.start();
		}
		catch(SlickException e){
			e.printStackTrace();
		}
	}


	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new GameMenu());
		addState(new Level());
		
	}

}

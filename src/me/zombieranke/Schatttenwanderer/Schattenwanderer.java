package me.zombieranke.Schatttenwanderer;

import java.io.File;

import me.zombieranke.levels.*;
import me.zombieranke.utils.Ressources;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

 
public class Schattenwanderer extends StateBasedGame
{
		

	public Schattenwanderer()
	{
		super("Schattenwanderer");
	}

	public static void main(String[] args)
	{
		System.setProperty("org.lwjgl.librarypath", new File("natives").getAbsolutePath());
		try
		{
		AppGameContainer container = new AppGameContainer(new Schattenwanderer());
		container.setDisplayMode(1280, 1024, false);
		container.setVSync(true);
		container.setShowFPS(false);
		container.start();
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
	}


	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{
		Ressources.init();
		addState(new GameMenu());
		addState(new EndScreen());
		addState(new Success());
		addState(new Fail());
		addState(new OptionsScreen());
		addState(new Level1());
		addState(new Level2());
		addState(new Level3());
		addState(new Tutorial());
		addState(new Tutorial2());
		addState(new Tutorial3());
		addState(new LevelSelect());
		addState(new Victory());
		addState(new Credits());
		addState(new Pause());
	}

}

package me.zombieranke.levels;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import me.zombieranke.Schatttenwanderer.Level;
import me.zombieranke.Schatttenwanderer.Player;
import me.zombieranke.Schatttenwanderer.Wall;
import me.zombieranke.Schatttenwanderer.Watch;

public class Level1 extends Level 
{
	private int levelID = 1;
	
	
	public Level1(){
		levelCount = levelID;
	}
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		levelCount = 1;
		Wall wall = new Wall(120,200, new Image("res/Wall_Type_1.jpg"),32,32);
		Walls.add(wall);
		Wall wall2 = new Wall(152,200, new Image("res/Wall_Type_1.jpg"),32,32);
		Walls.add(wall2);
		player = new Player (100,200);
		watch = new Watch(300,300,new Image("res/Watch_Placeholder.png"),5,5, wall);
	}
}

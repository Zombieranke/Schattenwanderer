package me.zombieranke.levels;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import me.zombieranke.Schatttenwanderer.LevelHandler;
import me.zombieranke.Schatttenwanderer.Player;
import me.zombieranke.Schatttenwanderer.Wall;
import me.zombieranke.Schatttenwanderer.Watch;
import me.zombieranke.utils.Direction;

public class Level1 extends LevelHandler 
{
	private int levelID = 1;
	
	
	public Level1(){
		levelCount = levelID;
	}
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		levelCount = 1;
		ArrayList<Wall> w1 = Wall.createWall(100, 100, 32, Direction.EAST);
		ArrayList<Wall> w2 = Wall.createWall(100, 132, 20, Direction.SOUTH);
		ArrayList<Wall> w3 = Wall.createWall(1092, 132, 19, Direction.SOUTH);
		ArrayList<Wall> w4 = Wall.createWall(132, 740, 31, Direction.EAST);
		walls.addAll(w1);
		walls.addAll(w2);
		walls.addAll(w3);
		walls.addAll(w4);
		player = new Player (200,200);
		watch = new Watch(300,300,new Image("res/Watch_Placeholder.png"),5,5);
		watch.updateSight(walls);
	}
}

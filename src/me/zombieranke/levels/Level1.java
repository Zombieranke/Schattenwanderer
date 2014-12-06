package me.zombieranke.levels;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import me.zombieranke.Schatttenwanderer.Laser;
import me.zombieranke.Schatttenwanderer.LevelHandler;
import me.zombieranke.Schatttenwanderer.Lever;
import me.zombieranke.Schatttenwanderer.Player;
import me.zombieranke.Schatttenwanderer.Wall;
import me.zombieranke.Schatttenwanderer.Watch;
import me.zombieranke.utils.Direction;

public class Level1 extends LevelHandler 
{
	private int levelID = 1;
	private SpriteSheet playerSheet;
	private SpriteSheet enemySheet;
	private Animation playerAnimation;
	private Animation enemyAnimation;
	private final int ORIGIN_X = 100;
	private final int ORIGIN_Y = 100;
	private final int DEFAULT_TILE_SIZE = 32;

	
	public Level1(){
		levelCount = levelID;
	}
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		levelCount = 1;
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y, 32, Direction.EAST));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE, 20, Direction.SOUTH));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 31, ORIGIN_Y + DEFAULT_TILE_SIZE, 19, Direction.SOUTH));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE, ORIGIN_Y + DEFAULT_TILE_SIZE * 20, 31, Direction.EAST));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 15, ORIGIN_Y + DEFAULT_TILE_SIZE, 5, Direction.SOUTH));
		Laser laser1 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE,ORIGIN_Y + DEFAULT_TILE_SIZE * 5,new Image("res/Laser.png"),16,16,Direction.EAST);
		Laser laser2 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 5, ORIGIN_Y + DEFAULT_TILE_SIZE,new Image("res/Laser.png"),16,16,Direction.SOUTH);
		laser.add(laser1);
		laser.add(laser2);
		Lever lever1 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE,ORIGIN_Y+ DEFAULT_TILE_SIZE, new Image("res/lever_up.png"), new Image("res/lever_down.png"),16,16, Direction.SOUTH,laser1);
		lever.add(lever1);
		Lever lever2 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE,ORIGIN_Y+ DEFAULT_TILE_SIZE * 15, new Image("res/lever_up.png"), new Image("res/lever_down.png"),16,16, Direction.EAST,laser2);
		lever.add(lever2);
		playerSheet = new SpriteSheet("res/Player_Spritesheet.png", 20, 20);
		enemySheet = new SpriteSheet("res/Enemy_Spritesheet.png", 20, 20);
		playerAnimation = new Animation(playerSheet, 200);
		enemyAnimation = new Animation(enemySheet, 200);
		playerAnimation.setPingPong(true);
		enemyAnimation.setPingPong(true);
		playerAnimation.setAutoUpdate(false);
		enemyAnimation.setAutoUpdate(false);
		
		player = new Player (200,200, playerAnimation, 20, 20);
		watch = new Watch(300,300,enemyAnimation,20,20);
		super.initializeObjects(container);
	}
}

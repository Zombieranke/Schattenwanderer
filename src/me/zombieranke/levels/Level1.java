package me.zombieranke.levels;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import me.zombieranke.Schatttenwanderer.Exit;
import me.zombieranke.Schatttenwanderer.Laser;
import me.zombieranke.Schatttenwanderer.LevelHandler;
import me.zombieranke.Schatttenwanderer.Lever;
import me.zombieranke.Schatttenwanderer.Player;
import me.zombieranke.Schatttenwanderer.Target;
import me.zombieranke.Schatttenwanderer.Wall;
import me.zombieranke.Schatttenwanderer.Watch;
import me.zombieranke.utils.Direction;

public class Level1 extends LevelHandler 
{
	private int levelID = 1;
	private SpriteSheet playerSheet;
	private SpriteSheet enemySheet;
	private SpriteSheet targetSheet;
	private SpriteSheet exitSheet;
	private Animation playerAnimation;
	private Animation enemyAnimation;
	private Animation targetAnimation;
	private Animation exitAnimation;
	private Image targetDeathImage;
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
		
		exitSheet = new SpriteSheet("res/Exit_Spritesheet.png",96,32);
		exitAnimation = new Animation(exitSheet, 50);
		exitAnimation.setPingPong(true);
		exitAnimation.setAutoUpdate(false);
		
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y, 20, Direction.EAST));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 23, ORIGIN_Y, 10, Direction.EAST));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE, 20, Direction.SOUTH));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 32, ORIGIN_Y + DEFAULT_TILE_SIZE, 19, Direction.SOUTH));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE, ORIGIN_Y + DEFAULT_TILE_SIZE * 20, 32, Direction.EAST));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 15, ORIGIN_Y + DEFAULT_TILE_SIZE, 5, Direction.SOUTH));
		
		playerSheet = new SpriteSheet("res/Player_Spritesheet.png", 20, 20);
		playerAnimation = new Animation(playerSheet, 100);
		playerAnimation.setPingPong(true);
		playerAnimation.setAutoUpdate(false);
		
		enemySheet = new SpriteSheet("res/Enemy_Spritesheet.png", 20, 20);
		enemyAnimation = new Animation(enemySheet, 100);
		enemyAnimation.setPingPong(true);
		enemyAnimation.setAutoUpdate(false);
		
		targetSheet = new SpriteSheet("res/Target_Spritesheet.png", 20, 20);
		targetDeathImage = new Image("res/Death1.png");
		targetAnimation = new Animation(targetSheet, 100);
		targetAnimation.setPingPong(true);
		targetAnimation.setAutoUpdate(false);
		
	}
	
	@Override
	public void onLoad(GameContainer container) throws SlickException 
	{
		Laser laser1 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE,ORIGIN_Y + DEFAULT_TILE_SIZE * 5,new Image("res/Laser.png"),16,16,Direction.EAST);
		Laser laser2 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 5, ORIGIN_Y + DEFAULT_TILE_SIZE,new Image("res/Laser.png"),16,16,Direction.SOUTH);
		laser.add(laser1);
		laser.add(laser2);
		Lever lever1 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE,ORIGIN_Y+ DEFAULT_TILE_SIZE, new Image("res/lever_up.png"), new Image("res/lever_down.png"),16,16, Direction.SOUTH,laser1);
		lever.add(lever1);
		Lever lever2 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE,ORIGIN_Y+ DEFAULT_TILE_SIZE * 15, new Image("res/lever_up.png"), new Image("res/lever_down.png"),16,16, Direction.EAST,laser2);
		lever.add(lever2);
		player = new Player (200,200, playerAnimation, 20, 20);
		watch = new Watch(300,300,enemyAnimation,20,20);
		target = new Target(600,600,targetAnimation, targetDeathImage, 20, 20);
		exit = new Exit(ORIGIN_X + DEFAULT_TILE_SIZE*20, ORIGIN_Y, exitAnimation, 96, 32);
		
		super.initializeObjects(container);
	}
}

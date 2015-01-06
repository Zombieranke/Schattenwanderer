package me.zombieranke.levels;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.heuristics.ManhattanHeuristic;

import me.zombieranke.Schatttenwanderer.Exit;
import me.zombieranke.Schatttenwanderer.Laser;
import me.zombieranke.Schatttenwanderer.LevelHandler;
import me.zombieranke.Schatttenwanderer.LevelMap;
import me.zombieranke.Schatttenwanderer.Lever;
import me.zombieranke.Schatttenwanderer.Player;
import me.zombieranke.Schatttenwanderer.Target;
import me.zombieranke.Schatttenwanderer.Wall;
import me.zombieranke.Schatttenwanderer.Watch;
import me.zombieranke.utils.Direction;
import me.zombieranke.utils.Ressources;

/**The first Level */
public class Level2 extends LevelHandler 
{	
	/** The ID of the level*/
	private int levelID = 2;
	
	/**The player animation*/
	private Animation playerAnimation;
	
	/**The enemy animation*/
	private Animation enemyAnimation;
	
	/**The target animation*/
	private Animation targetAnimation;
	
	/**The death animation of the target*/
	private Animation deathAnimation;
	
	/**The animation of the exit*/
	private Animation exitAnimation;
	
	/**Animation of the second Watch*/
	//private Animation enemyAnimation2;
	
	/**X-Coordinate of the level start*/
	private final float ORIGIN_X = 100;
	
	/**Y-Coordinate of the level start*/
	private final float ORIGIN_Y = 100;
	
	/**The default Wall Size*/
	private final float DEFAULT_TILE_SIZE = 32;

	/**Default Constructor*/
	public Level2(){
		levelNumber = levelID;
	}
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y, 20, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 23, ORIGIN_Y, 10, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE, 20, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 32, ORIGIN_Y + DEFAULT_TILE_SIZE, 19, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE, ORIGIN_Y + DEFAULT_TILE_SIZE * 20, 32, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 15, ORIGIN_Y + DEFAULT_TILE_SIZE, 5, Direction.SOUTH,Ressources.WALL_TYPE_1));
		
		exitAnimation = new Animation(Ressources.EXIT_SPRITESHEET, 50);
		exitAnimation.setPingPong(true);
		exitAnimation.setAutoUpdate(false);
		
		exit = new Exit(ORIGIN_X + DEFAULT_TILE_SIZE*20, ORIGIN_Y, exitAnimation, 96, 32);
		
		playerAnimation = new Animation(Ressources.PLAYER_SPRITESHEET, 100);
		playerAnimation.setPingPong(true);
		playerAnimation.setAutoUpdate(false);
		
		enemyAnimation = new Animation(Ressources.ENEMY_SPRITESHEET, 100);
		enemyAnimation.setPingPong(true);
		enemyAnimation.setAutoUpdate(false);
		
		//enemyAnimation2 = new Animation(Ressources.ENEMY_SPRITESHEET, 100);
		//enemyAnimation2.setPingPong(true);
		//enemyAnimation2.setAutoUpdate(false);
		
		targetAnimation = new Animation(Ressources.TARGET_SPRITESHEET, 100);
		targetAnimation.setPingPong(true);
		targetAnimation.setAutoUpdate(false);
		
		deathAnimation = new Animation(Ressources.TARGET_DEATH_SPRITESHEET, 80);
		deathAnimation.setAutoUpdate(false);
		
		alarmMusic = Ressources.ALARM_MUSIC;
		gameMusic = Ressources.GAME_MUSIC;
		endMusic = Ressources.END_MUSIC;
		exitSound = Ressources.EXIT_SOUND;
		leverSound = Ressources.LEVER_SOUND;
		game_background = Ressources.GAME_BACKGROUND;
		UI_Background = Ressources.UI_BACKGROUND;
		
		levelMap = new LevelMap(solids,exit);
		aPath = new AStarPathFinder(levelMap, 1000, true, new ManhattanHeuristic(1));
		
		super.resetOnLeave(container, game);
		
	}
	
	@Override
	public void onLeave(GameContainer container) throws SlickException 
	{
		Laser laser1 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE,ORIGIN_Y + DEFAULT_TILE_SIZE * 5.25f, Ressources.LASER.copy(),16,16,Direction.EAST);
		Laser laser2 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 5.25f, ORIGIN_Y + DEFAULT_TILE_SIZE,Ressources.LASER.copy(),16,16,Direction.SOUTH);
		Laser laser3 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 31.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 10.25f,Ressources.LASER.copy(),16,16,Direction.WEST);
		laser.add(laser1);
		laser.add(laser2);
		laser.add(laser3);
		
		Lever lever1 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE *1.25f,ORIGIN_Y+ DEFAULT_TILE_SIZE, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.SOUTH,laser1);
		Lever lever2 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE,ORIGIN_Y+ DEFAULT_TILE_SIZE * 8.25f, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.EAST,laser2);
		Lever lever3 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 14.5f,ORIGIN_Y + DEFAULT_TILE_SIZE * 4.25f,Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16,Direction.WEST,laser3);
		lever.add(lever1);
		lever.add(lever2);
		lever.add(lever3);
		
		
		Vector2f[] watchpoints = {new Vector2f(25,25),new Vector2f(88,25),new Vector2f(88,88)};
		//Vector2f[] watchpoints2 = {new Vector2f(80,80),new Vector2f(80,40),new Vector2f(40,40),new Vector2f(40,80)};
		
		player = new Player (200,200, playerAnimation, 20, 20);
		watches.add(new Watch(504,304,enemyAnimation,20,20, watchpoints,aPath));
		//watches.add(new Watch(604,604,enemyAnimation2,20,20, watchpoints2,aPath));
		target = new Target(600,600,targetAnimation, deathAnimation, 20, 20);
		
		super.initObjects(container);
	}
}

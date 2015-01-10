package me.zombieranke.levels;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
public class Tutorial3 extends LevelHandler 
{	
	/** The ID of the level*/
	private int levelID = 3;
	
	/** Whether the Watch has already spawned or not.*/
	private boolean hasMoved = false;
	
	/** Whether the Watch has already been removed or not.*/
	private boolean hasRemoved = false;
	
	/**The player animation*/
	private Animation playerAnimation;
	
	/**The enemy animation*/
	private Animation enemyAnimation;
	/**Animation of the second Watch*/
	
	/**The target animation*/
	private Animation targetAnimation;
	
	/**The death animation of the target*/
	private Animation deathAnimation;
	
	/**The animation of the exit*/
	private Animation exitAnimation;
	
	/**X-Coordinate of the level start*/
	private final float ORIGIN_X = 100;
	
	/**Y-Coordinate of the level start*/
	private final float ORIGIN_Y = 100;
	
	/**The default Wall Size*/
	private final float DEFAULT_TILE_SIZE = 32;

	/**Default Constructor*/
	public Tutorial3(){
		levelNumber = levelID;
	}
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE * 8, 33, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, 4, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 32, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, 1, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 32, ORIGIN_Y + DEFAULT_TILE_SIZE * 12, 1, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE * 13, 33, Direction.EAST,Ressources.WALL_TYPE_1));
		
		exitAnimation = new Animation(Ressources.EXIT_SPRITESHEET, 50);
		exitAnimation.setPingPong(true);
		exitAnimation.setAutoUpdate(false);
		
		exit = new Exit(ORIGIN_X + DEFAULT_TILE_SIZE * 31, ORIGIN_Y + DEFAULT_TILE_SIZE * 11, exitAnimation, 96, 32, Direction.EAST);
		
		playerAnimation = new Animation(Ressources.PLAYER_SPRITESHEET, 100);
		playerAnimation.setPingPong(true);
		playerAnimation.setAutoUpdate(false);
		
		enemyAnimation = new Animation(Ressources.ENEMY_SPRITESHEET, 100);
		enemyAnimation.setPingPong(true);
		enemyAnimation.setAutoUpdate(false);
		
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
	public void renderSpecific(GameContainer container,StateBasedGame game,Graphics g)
	{
		
	}
	
	@Override
	public void updateSpecific(GameContainer container, StateBasedGame game, int delta)
	{
			watches.get(0).setDirection(270);
			watches.get(0).setStopped(true);
			watches.get(1).setDirection(90);
			watches.get(1).setStopped(true);
	}
	
	@Override
	public void onLeave(GameContainer container) throws SlickException 
	{
		/*Laser laser1 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 8,ORIGIN_Y + DEFAULT_TILE_SIZE * 9, Ressources.LASER.copy(),16,16,Direction.SOUTH);

		laser.add(laser1);

		
		
		Lever lever1 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 8.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 17, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.SOUTH,new Laser[]{laser1});
		
		lever.add(lever1);*/

		
		Vector2f[] watchpoints = {new Vector2f(21,55)};
		
		// ORIGIN_X + DEFAULT_TILE_SIZE * 2, ORIGIN_Y + DEFAULT_TILE_SIZE * 10.5f = 21,55
		
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 22, ORIGIN_Y + DEFAULT_TILE_SIZE * 9.25f,enemyAnimation,20,20, watchpoints,aPath));
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 22, ORIGIN_Y + DEFAULT_TILE_SIZE * 11.75f,enemyAnimation,20,20, watchpoints,aPath));
		
		player = new Player (ORIGIN_X + DEFAULT_TILE_SIZE * 2, ORIGIN_Y + DEFAULT_TILE_SIZE * 10.5f, playerAnimation, 20, 20);
		
		target = new Target(ORIGIN_X + DEFAULT_TILE_SIZE * 27, ORIGIN_Y + DEFAULT_TILE_SIZE * 10.5f,targetAnimation, deathAnimation, 20, 20);
		
		super.initObjects(container);
	}
}

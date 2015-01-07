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
	private Animation enemyAnimation2;
	private Animation enemyAnimation3;
	private Animation enemyAnimation4;
	
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
		
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y, 30, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE, 19, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 32, ORIGIN_Y, 20, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE * 20, 33, Direction.EAST,Ressources.WALL_TYPE_1));
		
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 7, ORIGIN_Y + DEFAULT_TILE_SIZE * 2, 2, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE, ORIGIN_Y + DEFAULT_TILE_SIZE * 3, 8, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE, ORIGIN_Y + DEFAULT_TILE_SIZE * 4, 8, Direction.EAST,Ressources.WALL_TYPE_1));
		
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 7, ORIGIN_Y + DEFAULT_TILE_SIZE * 6, 2, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE, ORIGIN_Y + DEFAULT_TILE_SIZE * 7, 8, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE, ORIGIN_Y + DEFAULT_TILE_SIZE * 8, 8, Direction.EAST,Ressources.WALL_TYPE_1));
		
		
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 4, ORIGIN_Y + DEFAULT_TILE_SIZE * 11, 5, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 4, ORIGIN_Y + DEFAULT_TILE_SIZE * 12, 5, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 4, ORIGIN_Y + DEFAULT_TILE_SIZE * 13, 5, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 4, ORIGIN_Y + DEFAULT_TILE_SIZE * 14, 5, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 4, ORIGIN_Y + DEFAULT_TILE_SIZE * 15, 5, Direction.EAST,Ressources.WALL_TYPE_1));
		
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 5, ORIGIN_Y + DEFAULT_TILE_SIZE * 18, 4, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 5, ORIGIN_Y + DEFAULT_TILE_SIZE * 19, 4, Direction.EAST,Ressources.WALL_TYPE_1));
		
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 12, ORIGIN_Y + DEFAULT_TILE_SIZE * 4, 13, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 13, ORIGIN_Y + DEFAULT_TILE_SIZE * 4, 5, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 21, ORIGIN_Y + DEFAULT_TILE_SIZE * 4, 6, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 27, ORIGIN_Y + DEFAULT_TILE_SIZE * 4, 13, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 13, ORIGIN_Y + DEFAULT_TILE_SIZE * 16, 5, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 21, ORIGIN_Y + DEFAULT_TILE_SIZE * 16, 6, Direction.EAST,Ressources.WALL_TYPE_1));
		
		
		
		
		exitAnimation = new Animation(Ressources.EXIT_SPRITESHEET, 50);
		exitAnimation.setPingPong(true);
		exitAnimation.setAutoUpdate(false);
		
		exit = new Exit(ORIGIN_X + DEFAULT_TILE_SIZE*30, ORIGIN_Y, exitAnimation, 96, 32, Direction.NORTH);
		
		playerAnimation = new Animation(Ressources.PLAYER_SPRITESHEET, 100);
		playerAnimation.setPingPong(true);
		playerAnimation.setAutoUpdate(false);
		
		enemyAnimation = new Animation(Ressources.ENEMY_SPRITESHEET, 100);
		enemyAnimation.setPingPong(true);
		enemyAnimation.setAutoUpdate(false);
		
		enemyAnimation2 = new Animation(Ressources.ENEMY_SPRITESHEET, 100);
		enemyAnimation2.setPingPong(true);
		enemyAnimation2.setAutoUpdate(false);
		
		enemyAnimation3 = new Animation(Ressources.ENEMY_SPRITESHEET, 100);
		enemyAnimation3.setPingPong(true);
		enemyAnimation3.setAutoUpdate(false);
		
		enemyAnimation4 = new Animation(Ressources.ENEMY_SPRITESHEET, 100);
		enemyAnimation4.setPingPong(true);
		enemyAnimation4.setAutoUpdate(false);
		
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
		Laser laser1 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 11.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 11.25f, Ressources.LASER.copy(),16,16,Direction.WEST);
		Laser laser2 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 20.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 4.25f, Ressources.LASER.copy(),16,16,Direction.WEST);
		Laser laser3 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 21.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 17, Ressources.LASER.copy(),16,16,Direction.SOUTH);
		laser.add(laser1);
		laser.add(laser2);
		laser.add(laser3);

		
		Lever lever1 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 13, ORIGIN_Y + DEFAULT_TILE_SIZE *11.25f, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.EAST,new Laser[]{laser1});
		Lever lever2 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 26.25f, ORIGIN_Y + DEFAULT_TILE_SIZE *3.5f, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.NORTH,new Laser[]{laser2});
		Lever lever3 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 26.25f, ORIGIN_Y + DEFAULT_TILE_SIZE *5, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.SOUTH,new Laser[]{laser2});
		Lever lever4 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 23.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 15.5f, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.NORTH,new Laser[]{laser3});
		lever.add(lever1);
		lever.add(lever2);
		lever.add(lever3);
		lever.add(lever4);

		
		Vector2f[] watchpoints = {new Vector2f(21, 73), new Vector2f(21, 51), new Vector2f(41, 51), new Vector2f(21, 51)};
		Vector2f[] watchpoints2 = {new Vector2f(53,21),new Vector2f(53,85),new Vector2f(131,85),new Vector2f(131,21)};
		Vector2f[] watchpoints3 = {new Vector2f(131,85),new Vector2f(131,21), new Vector2f(53,21),new Vector2f(53,85)};
		Vector2f[] watchpoints4 = {new Vector2f(77,41),new Vector2f(77,67), new Vector2f(113,67),new Vector2f(113,41)};
		
		//ORIGIN_X + DEFAULT_TILE_SIZE * 16, ORIGIN_Y + DEFAULT_TILE_SIZE * 7 = 77, 41
		//ORIGIN_X + DEFAULT_TILE_SIZE * 25, ORIGIN_Y + DEFAULT_TILE_SIZE * 12 = 113, 61
		
		
		//ORIGIN_X + DEFAULT_TILE_SIZE * 10, ORIGIN_Y + DEFAULT_TILE_SIZE * 2 = 53, 21
		//ORIGIN_X + DEFAULT_TILE_SIZE * 10, ORIGIN_Y + DEFAULT_TILE_SIZE * 18 = 53, 85
		//ORIGIN_X + DEFAULT_TILE_SIZE * 30, ORIGIN_Y + DEFAULT_TILE_SIZE * 18 = 133, 85
		//ORIGIN_X + DEFAULT_TILE_SIZE * 30, ORIGIN_Y + DEFAULT_TILE_SIZE * 2 = 133, 21
		
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 2,ORIGIN_Y + DEFAULT_TILE_SIZE * 14,enemyAnimation,20,20, watchpoints,aPath));
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 18, ORIGIN_Y + DEFAULT_TILE_SIZE * 2,enemyAnimation2,20,20, watchpoints2,aPath));
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 18, ORIGIN_Y + DEFAULT_TILE_SIZE * 18,enemyAnimation3,20,20, watchpoints3,aPath));
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 20, ORIGIN_Y + DEFAULT_TILE_SIZE * 7,enemyAnimation4,20,20, watchpoints4,aPath));
		
		player = new Player (ORIGIN_X + DEFAULT_TILE_SIZE + 10, ORIGIN_Y + DEFAULT_TILE_SIZE + 10, playerAnimation, 20, 20);
		
		target = new Target(ORIGIN_X + DEFAULT_TILE_SIZE * 3,ORIGIN_Y + DEFAULT_TILE_SIZE * 18, targetAnimation, deathAnimation, 20, 20);
		
		super.initObjects(container);
	}
}

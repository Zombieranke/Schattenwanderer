package me.zombieranke.levels;

import org.newdawn.slick.Animation;
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
public class Level1 extends LevelHandler 
{	
	/** The ID of the level*/
	private int levelID = 4;
	
	/**The player animation*/
	private Animation playerAnimation;
	
	/**The enemy animation*/
	private Animation enemyAnimation;
	/**Animation of the second Watch*/
	private Animation enemyAnimation2;
	private Animation enemyAnimation3;
	
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
	public Level1(){
		levelNumber = levelID;
	}
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y, 13, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 15, ORIGIN_Y, 18, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE, 19, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 32, ORIGIN_Y + DEFAULT_TILE_SIZE, 19, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE * 20, 33, Direction.EAST,Ressources.WALL_TYPE_1));
		
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 29, ORIGIN_Y + DEFAULT_TILE_SIZE * 3, 15, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 28, ORIGIN_Y + DEFAULT_TILE_SIZE * 3, 10, Direction.WEST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 28, ORIGIN_Y + DEFAULT_TILE_SIZE * 17, 10, Direction.WEST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 18, ORIGIN_Y + DEFAULT_TILE_SIZE, 8, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 18, ORIGIN_Y + DEFAULT_TILE_SIZE * 17, 6, Direction.NORTH,Ressources.WALL_TYPE_1));
		
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 8, ORIGIN_Y + DEFAULT_TILE_SIZE, 8, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 8, ORIGIN_Y + DEFAULT_TILE_SIZE * 12, 5, Direction.SOUTH,Ressources.WALL_TYPE_1));
		
		exitAnimation = new Animation(Ressources.EXIT_SPRITESHEET, 50);
		exitAnimation.setPingPong(true);
		exitAnimation.setAutoUpdate(false);
		
		exit = new Exit(ORIGIN_X + DEFAULT_TILE_SIZE*13, ORIGIN_Y, exitAnimation, 96, 32, Direction.NORTH);
		
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
		game_Background = Ressources.GAME_BACKGROUND;
		UI_Background = Ressources.UI_BACKGROUND;
		deathMusic = Ressources.DEATH_MUSIC;
		
		levelMap = new LevelMap(solids,exit);
		aPath = new AStarPathFinder(levelMap, 1000, true, new ManhattanHeuristic(1));
		
		super.resetOnLeave(container, game);	
	}
	
	@Override
	protected void renderSpecificBefore(GameContainer container, StateBasedGame game, Graphics g)
	{
		UI_Background.draw(0,0);
		game_Background.draw(100, 100, 1055, 671);
	}
	
	@Override
	public void onLeave(GameContainer container) throws SlickException 
	{
		Laser laser1 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 8,ORIGIN_Y + DEFAULT_TILE_SIZE * 9, Ressources.LASER.copy(),16,16,Direction.SOUTH);
		Laser laser2 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 8.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 11.5f,Ressources.LASER.copy(),16,16,Direction.NORTH);
		Laser laser3 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 9, ORIGIN_Y + DEFAULT_TILE_SIZE * 15.25f,Ressources.LASER.copy(),16,16,Direction.EAST);
		Laser laser4 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 28.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 8.25f,Ressources.LASER.copy(),16,16,Direction.WEST);
		Laser laser5 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 28.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 12.25f,Ressources.LASER.copy(),16,16,Direction.WEST);
		Laser laser6 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 25.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 4,Ressources.LASER.copy(),16,16,Direction.SOUTH);
		Laser laser7 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 17.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 4.25f,Ressources.LASER.copy(),16,16,Direction.WEST);
		laser.add(laser1);
		laser.add(laser2);
		laser.add(laser3);
		laser.add(laser4);
		laser.add(laser5);
		laser.add(laser6);
		laser.add(laser7);
		
		
		Lever lever1 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 8.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 17, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.SOUTH,new Laser[]{laser3});
		Lever lever2 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 30, ORIGIN_Y + DEFAULT_TILE_SIZE * 8.25f, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.EAST,new Laser[]{laser4});
		Lever lever3 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 30, ORIGIN_Y + DEFAULT_TILE_SIZE * 12.25f,Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16,Direction.EAST,new Laser[]{laser5});
		Lever lever4 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 25.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 2.5f,Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16,Direction.NORTH,new Laser[]{laser6});
		Lever lever5 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 19, ORIGIN_Y + DEFAULT_TILE_SIZE * 4.25f,Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16,Direction.EAST,new Laser[]{laser7});
		lever.add(lever1);
		lever.add(lever2);
		lever.add(lever3);
		lever.add(lever4);
		lever.add(lever5);
		
		
		Vector2f[] watchpoints = {new Vector2f(57,85),new Vector2f(33,85), new Vector2f(57,85), new Vector2f(57,25)};
		//ORIGIN_X + DEFAULT_TILE_SIZE * 11, ORIGIN_Y + DEFAULT_TILE_SIZE * 3 = 57, 25
		//ORIGIN_X + DEFAULT_TILE_SIZE * 11, ORIGIN_Y + DEFAULT_TILE_SIZE * 18 = 57, 85
		//ORIGIN_X + DEFAULT_TILE_SIZE * 5, ORIGIN_Y + DEFAULT_TILE_SIZE * 18 = 33, 85
		
		Vector2f[] watchpoints2 = {new Vector2f(33,53),new Vector2f(111,53)};
		//ORIGIN_X + DEFAULT_TILE_SIZE * 5, ORIGIN_Y + DEFAULT_TILE_SIZE * 10 = 33, 53
		//ORIGIN_X + DEFAULT_TILE_SIZE * 24.5, ORIGIN_Y + DEFAULT_TILE_SIZE * 10 = 111, 53
		
		Vector2f[] watchpoints3 = {new Vector2f(73,53), new Vector2f(111,53), new Vector2f(73,53), new Vector2f(73,87), new Vector2f(129,87), new Vector2f(73,87), new Vector2f(73,25)};
		//ORIGIN_X + DEFAULT_TILE_SIZE * 15, ORIGIN_Y + DEFAULT_TILE_SIZE * 3 = 73, 25
		//ORIGIN_X + DEFAULT_TILE_SIZE * 15, ORIGIN_Y + DEFAULT_TILE_SIZE * 10 = 73, 53
		//ORIGIN_X + DEFAULT_TILE_SIZE * 24.5, ORIGIN_Y + DEFAULT_TILE_SIZE * 10 = 111, 53
		//ORIGIN_X + DEFAULT_TILE_SIZE * 15, ORIGIN_Y + DEFAULT_TILE_SIZE * 10 = 73, 53
		//ORIGIN_X + DEFAULT_TILE_SIZE * 15, ORIGIN_Y + DEFAULT_TILE_SIZE * 18.5 = 73, 87
		//ORIGIN_X + DEFAULT_TILE_SIZE * 29, ORIGIN_Y + DEFAULT_TILE_SIZE * 18.5 = 129, 87
		//ORIGIN_X + DEFAULT_TILE_SIZE * 15, ORIGIN_Y + DEFAULT_TILE_SIZE * 18.5 = 73, 87
		
		player = new Player (200,200, playerAnimation, 20, 20);
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 11, ORIGIN_Y + DEFAULT_TILE_SIZE * 16,enemyAnimation,20,20, watchpoints,aPath));
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 10, ORIGIN_Y + DEFAULT_TILE_SIZE * 10,enemyAnimation2,20,20, watchpoints2,aPath));
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 15, ORIGIN_Y + DEFAULT_TILE_SIZE * 9,enemyAnimation3,20,20, watchpoints3,aPath));
		target = new Target(ORIGIN_X + DEFAULT_TILE_SIZE * 27, ORIGIN_Y + DEFAULT_TILE_SIZE * 10,targetAnimation, deathAnimation, 20, 20);
		
		super.initObjects(container);
	}
}

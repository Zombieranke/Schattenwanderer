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
public class Level3 extends LevelHandler 
{	
	/** The ID of the level*/
	private int levelID = 6;
	
	/**The player animation*/
	private Animation playerAnimation;
	
	/**The enemy animation*/
	private Animation enemyAnimation;
	private Animation enemyAnimation2;
	private Animation enemyAnimation3;
	private Animation enemyAnimation4;
	private Animation enemyAnimation5;
	
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
	
	private int laserTimer = 0;

	/**Default Constructor*/
	public Level3(){
		levelNumber = levelID;
	}
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y, 33, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE, 19, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 32, ORIGIN_Y + DEFAULT_TILE_SIZE, 19, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE * 20, 13, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 15, ORIGIN_Y + DEFAULT_TILE_SIZE * 20, 18, Direction.EAST,Ressources.WALL_TYPE_1));
		
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 3, ORIGIN_Y + DEFAULT_TILE_SIZE * 3, 7, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 4, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, 2, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 5, ORIGIN_Y + DEFAULT_TILE_SIZE * 10, 2, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 4, ORIGIN_Y + DEFAULT_TILE_SIZE * 12, 2, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 3, ORIGIN_Y + DEFAULT_TILE_SIZE * 12, 6, Direction.SOUTH,Ressources.WALL_TYPE_1));
		
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 12, ORIGIN_Y + DEFAULT_TILE_SIZE * 4, 6, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 12, ORIGIN_Y + DEFAULT_TILE_SIZE * 12, 5, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 13, ORIGIN_Y + DEFAULT_TILE_SIZE * 16, 15, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 13, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, 10, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 13, ORIGIN_Y + DEFAULT_TILE_SIZE * 12, 10, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 27, ORIGIN_Y + DEFAULT_TILE_SIZE * 15, 12, Direction.NORTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 26, ORIGIN_Y + DEFAULT_TILE_SIZE * 4, 10, Direction.WEST,Ressources.WALL_TYPE_1));
		
		exitAnimation = new Animation(Ressources.EXIT_SPRITESHEET, 50);
		exitAnimation.setPingPong(true);
		exitAnimation.setAutoUpdate(false);
		
		exit = new Exit(ORIGIN_X + DEFAULT_TILE_SIZE * 12, ORIGIN_Y + DEFAULT_TILE_SIZE * 20, exitAnimation, 96, 32, Direction.SOUTH);
		
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
		
		enemyAnimation5 = new Animation(Ressources.ENEMY_SPRITESHEET, 100);
		enemyAnimation5.setPingPong(true);
		enemyAnimation5.setAutoUpdate(false);
		
		
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
	public void updateSpecific(GameContainer container, StateBasedGame game, int delta)
	{
		laserTimer++;
		
		if(laserTimer>200)
		{
			for(Laser l : laser)
			{
				if(l.getClusterNumber() == 1)
				{
					l.toggle();
				}
			}
			
			laserTimer = 0;
		}
	}
	
	
	@Override
	public void onLeave(GameContainer container) throws SlickException 
	{
		Laser laser1 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 4, ORIGIN_Y + DEFAULT_TILE_SIZE * 4.25f, Ressources.LASER.copy(),16,16,Direction.EAST,1);
		Laser laser2 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 6, ORIGIN_Y + DEFAULT_TILE_SIZE * 9.25f, Ressources.LASER.copy(),16,16,Direction.EAST, false,1);
		Laser laser3 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 6, ORIGIN_Y + DEFAULT_TILE_SIZE * 12.25f, Ressources.LASER.copy(),16,16,Direction.EAST,1);
		Laser laser4 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 4, ORIGIN_Y + DEFAULT_TILE_SIZE * 16.25f, Ressources.LASER.copy(),16,16,Direction.EAST, false,1);
		Laser laser5 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 12.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 17, Ressources.LASER.copy(),16,16,Direction.SOUTH,2);
		Laser laser6 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 13.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 10, Ressources.LASER.copy(),16,16,Direction.SOUTH,1);
		Laser laser7 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 19.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 10, Ressources.LASER.copy(),16,16,Direction.SOUTH,1);
		Laser laser8 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 16.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 11.5f, Ressources.LASER.copy(),16,16,Direction.NORTH,false,1);
		Laser laser9 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 22.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 11.5f, Ressources.LASER.copy(),16,16,Direction.NORTH,false,1);
		Laser laser10 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 15.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 17, Ressources.LASER.copy(),16,16,Direction.SOUTH,2);
		Laser laser11 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 16.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 13, Ressources.LASER.copy(),16,16,Direction.SOUTH,false,1);
		Laser laser12 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 22.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 13, Ressources.LASER.copy(),16,16,Direction.SOUTH,false,1);
		Laser laser13 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 19.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 15.5f, Ressources.LASER.copy(),16,16,Direction.NORTH,1);
		laser.add(laser1);
		laser.add(laser2);
		laser.add(laser3);
		laser.add(laser4);
		laser.add(laser5);
		laser.add(laser6);
		laser.add(laser7);
		laser.add(laser8);
		laser.add(laser9);
		laser.add(laser10);
		laser.add(laser11);
		laser.add(laser12);
		laser.add(laser13);
		

		Lever lever1 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 13f, ORIGIN_Y + DEFAULT_TILE_SIZE * 14.25f, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.EAST,new Laser[]{laser5,laser10});
		//Lever lever2 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 26.25f, ORIGIN_Y + DEFAULT_TILE_SIZE *3.5f, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.NORTH,laser2);
		//Lever lever3 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 26.25f, ORIGIN_Y + DEFAULT_TILE_SIZE *5, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.SOUTH,laser2);
		//Lever lever4 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 23.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 15.5f, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.NORTH,laser3);
		lever.add(lever1);
		//lever.add(lever3);
		//lever.add(lever4);

		
		Vector2f[] watchpoints = {new Vector2f(41, 19), new Vector2f(19, 19), new Vector2f(19, 87), new Vector2f(41, 87)};
		Vector2f[] watchpoints2 = {new Vector2f(131,21),new Vector2f(131,85),new Vector2f(53,85),new Vector2f(53,21)};
		Vector2f[] watchpoints3 = {new Vector2f(110,42), new Vector2f(110,64),new Vector2f(110,36), new Vector2f(71,36),new Vector2f(71,29),new Vector2f(71,29),new Vector2f(71,42)};
		Vector2f[] watchpoints4 = {new Vector2f(71,29),new Vector2f(71,42), new Vector2f(110,42), new Vector2f(110,70),new Vector2f(71,70), new Vector2f(110,70),new Vector2f(110,36), new Vector2f(71,36),new Vector2f(71,29)};
		Vector2f[] watchpoints5 = {new Vector2f(53,21), new Vector2f(53,55), new Vector2f(110,55), new Vector2f(53,55), new Vector2f(53,85),new Vector2f(131,85), new Vector2f(131,21)};
		
		//ORIGIN_X + DEFAULT_TILE_SIZE * 7, ORIGIN_Y + DEFAULT_TILE_SIZE * 1.5f = 41, 19
		//ORIGIN_X + DEFAULT_TILE_SIZE * 1.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 18.5f = 19, 87
		
		//ORIGIN_X + DEFAULT_TILE_SIZE * 29.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 2.5f = 131, 23
		
		//ORIGIN_X + DEFAULT_TILE_SIZE * 14.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 4 = 71, 29
		//ORIGIN_X + DEFAULT_TILE_SIZE * 14.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 7.5f = 71, 43
		//ORIGIN_X + DEFAULT_TILE_SIZE * 25, ORIGIN_Y + DEFAULT_TILE_SIZE * 7.5f = 113, 43
		
		//ORIGIN_X + DEFAULT_TILE_SIZE * 10, ORIGIN_Y + DEFAULT_TILE_SIZE * 11 = 53, 57
		
		//ORIGIN_X + DEFAULT_TILE_SIZE * 10, ORIGIN_Y + DEFAULT_TILE_SIZE * 2 = 53, 21
		//ORIGIN_X + DEFAULT_TILE_SIZE * 10, ORIGIN_Y + DEFAULT_TILE_SIZE * 18 = 53, 85
		//ORIGIN_X + DEFAULT_TILE_SIZE * 30, ORIGIN_Y + DEFAULT_TILE_SIZE * 18 = 133, 85
		//ORIGIN_X + DEFAULT_TILE_SIZE * 30, ORIGIN_Y + DEFAULT_TILE_SIZE * 2 = 133, 21
		
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 7, ORIGIN_Y + DEFAULT_TILE_SIZE * 5,enemyAnimation,20,20, watchpoints,aPath));
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 2 ,enemyAnimation2,20,20, watchpoints2,aPath));
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 14.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 7,enemyAnimation3,20,20, watchpoints3,aPath));
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 14.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 7,enemyAnimation4,20,20, watchpoints4,aPath));
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 15f, ORIGIN_Y + DEFAULT_TILE_SIZE * 2 ,enemyAnimation5,20,20, watchpoints5,aPath));
		
		player = new Player (ORIGIN_X + DEFAULT_TILE_SIZE * 3.5f, ORIGIN_Y + DEFAULT_TILE_SIZE * 10.5f, playerAnimation, 20, 20);
		
		target = new Target(ORIGIN_X + DEFAULT_TILE_SIZE * 25, ORIGIN_Y + DEFAULT_TILE_SIZE * 11, targetAnimation, deathAnimation, 20, 20);
		
		super.initObjects(container);
	}
}

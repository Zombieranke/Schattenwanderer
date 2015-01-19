package me.zombieranke.levels;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
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
import me.zombieranke.utils.Direction;
import me.zombieranke.utils.Ressources;


/**The first Level */
public class Tutorial extends LevelHandler 
{	
	/** The ID of the level*/
	private int levelID = 1;
	
	/**The player animation*/
	private Animation playerAnimation;
	
	/**The target animation*/
	private Animation targetAnimation;
	
	/**The death animation of the target*/
	private Animation deathAnimation;
	
	/**The animation of the exit*/
	private Animation exitAnimation;
	
	private Image goalText;
	
	private Image arrowKeysText;
	
	private Image leverText;
	
	private Image exitText;
	
	private Image alarmText;
	
	
	/**X-Coordinate of the level start*/
	private final float ORIGIN_X = 100;
	
	/**Y-Coordinate of the level start*/
	private final float ORIGIN_Y = 100;
	
	/**The default Wall Size*/
	private final float DEFAULT_TILE_SIZE = 32;

	/**Default Constructor*/
	public Tutorial(){
		levelNumber = levelID;
	}
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE *8 , 33, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE *13 , 33, Direction.EAST,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X, ORIGIN_Y + DEFAULT_TILE_SIZE *9 , 4, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 32, ORIGIN_Y + DEFAULT_TILE_SIZE *9 , 1, Direction.SOUTH,Ressources.WALL_TYPE_1));
		solids.addAll(Wall.createWall(ORIGIN_X + DEFAULT_TILE_SIZE * 32, ORIGIN_Y + DEFAULT_TILE_SIZE *12, 1, Direction.SOUTH,Ressources.WALL_TYPE_1));
		
		exitAnimation = new Animation(Ressources.EXIT_SPRITESHEET, 50);
		exitAnimation.setPingPong(true);
		exitAnimation.setAutoUpdate(false);
		
		exit = new Exit(ORIGIN_X + DEFAULT_TILE_SIZE*32, ORIGIN_Y + DEFAULT_TILE_SIZE * 10, exitAnimation, 32, 64, Direction.EAST);
		
		playerAnimation = new Animation(Ressources.PLAYER_SPRITESHEET, 100);
		playerAnimation.setPingPong(true);
		playerAnimation.setAutoUpdate(false);
		
		targetAnimation = new Animation(Ressources.TARGET_SPRITESHEET, 100);
		targetAnimation.setPingPong(true);
		targetAnimation.setAutoUpdate(false);
		
		deathAnimation = new Animation(Ressources.TARGET_DEATH_SPRITESHEET, 80);
		deathAnimation.setAutoUpdate(false);
		
		alarmMusic = Ressources.ALARM_MUSIC;
		gameMusic = Ressources.GAME_MUSIC;
		endMusic = Ressources.END_MUSIC;
		deathMusic = Ressources.DEATH_MUSIC;
		exitSound = Ressources.EXIT_SOUND;
		leverSound = Ressources.LEVER_SOUND;
		game_Background = Ressources.GAME_BACKGROUND;
		UI_Background = Ressources.UI_BACKGROUND;
		
		levelMap = new LevelMap(solids,exit);
		aPath = new AStarPathFinder(levelMap, 1000, true, new ManhattanHeuristic(1));
		
		
		alarmText = Ressources.TUTORIAL_ALARM;
		leverText = Ressources.TUTORIAL_LEVER;
		exitText = Ressources.TUTORIAL_EXIT;
		goalText = Ressources.TUTORIAL_GOAL;
		arrowKeysText = Ressources.TUTORIAL_ARROW_KEYS;
		
		super.reset(container, game);	
	}
	
	@Override
	protected void renderSpecificBefore(GameContainer container, StateBasedGame game, Graphics g)
	{
		UI_Background.draw(0,0);
		game_Background.draw(100, 356, 1055, 160);
		//game_Background.draw(100, 100, 1055, 671);
	}
	
	@Override
	public void renderSpecificAfter(GameContainer container,StateBasedGame game,Graphics g)
	{
		
		if(!mission)
		{
			goalText.draw(400,100);
		}
		
		
		if(player.getX()<300)
		{
			arrowKeysText.draw(300,300);
		}
		
		
		if(mission && !alarm)
		{
			exitText.draw(370,100);
		}
		
		if(alarm)
		{
			alarmText.draw(430,60);
			
			leverText.draw(780,270);
	     }
	}
	
	@Override
	public void onLoad(GameContainer container) throws SlickException 
	{
		
		Laser laser1 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 20.5f,ORIGIN_Y + DEFAULT_TILE_SIZE * 9, Ressources.LASER.copy(),16,16,Direction.SOUTH);
		laser.add(laser1);
		
		
		Lever lever1 = new Lever(ORIGIN_X + DEFAULT_TILE_SIZE * 26f, ORIGIN_Y + DEFAULT_TILE_SIZE * 9f, Ressources.LEVER_UP.copy(), Ressources.LEVER_DOWN.copy(),16,16, Direction.SOUTH,new Laser[]{laser1});
		lever.add(lever1);
		
		player = new Player (ORIGIN_X + DEFAULT_TILE_SIZE * 2, ORIGIN_Y + DEFAULT_TILE_SIZE * 10.5f, playerAnimation, 20, 20);
		target = new Target(ORIGIN_X + DEFAULT_TILE_SIZE * 10, ORIGIN_Y + DEFAULT_TILE_SIZE * 10.5f,targetAnimation, deathAnimation, 20, 20);
		
		super.initObjects(container);
	}
}


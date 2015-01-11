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
	
	/**The player animation*/
	private Animation playerAnimation;
	
	private int laserTimer = 0;
	
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
		
		exit = new Exit(ORIGIN_X + DEFAULT_TILE_SIZE * 32, ORIGIN_Y + DEFAULT_TILE_SIZE * 10, exitAnimation, 32, 96, Direction.EAST);
		
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
		deathMusic = Ressources.DEATH_MUSIC;
		exitSound = Ressources.EXIT_SOUND;
		leverSound = Ressources.LEVER_SOUND;
		game_Background = Ressources.GAME_BACKGROUND;
		UI_Background = Ressources.UI_BACKGROUND;
		
		levelMap = new LevelMap(solids,exit);
		aPath = new AStarPathFinder(levelMap, 1000, true, new ManhattanHeuristic(1));
		
		super.resetOnLeave(container, game);	
	}
	
	@Override
	protected void renderSpecificBefore(GameContainer container, StateBasedGame game, Graphics g)
	{
		UI_Background.draw(0,0);
		game_Background.draw(100, 356, 1055, 160);
	}
	
	@Override
	public void renderSpecificAfter(GameContainer container,StateBasedGame game,Graphics g)
	{
		
			g.setColor(Color.black);
			g.drawString("Now you have to Sprint\nPress X to avtivate Sprinting", 250, 300);
		
		if(player.getX()>440)
		{
			g.drawString("Sprinting makes more noise than normal movement\nand consumes more enery than stealth", 440, 600);
		}
		if(player.getX()>650)
		{
			g.drawString("Try to sneak past the guards by\npressing Space additionally to the arrow keys", 630, 300);
		}
	}
	
	@Override
	public void updateSpecific(GameContainer container, StateBasedGame game, int delta)
	{
			watches.get(0).setDirection(270);
			watches.get(0).setStopped(true);
			watches.get(1).setDirection(90);
			watches.get(1).setStopped(true);
			
			laserTimer++;
			
			if(laserTimer>150)
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
		
		Laser laser1 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 7.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, Ressources.LASER.copy(),16,16,Direction.SOUTH,1);
		Laser laser2 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 8.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, Ressources.LASER.copy(),16,16,Direction.SOUTH,1);
		Laser laser3 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 9.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, Ressources.LASER.copy(),16,16,Direction.SOUTH,1);
		Laser laser4 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 10.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, Ressources.LASER.copy(),16,16,Direction.SOUTH,1);
		Laser laser5 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 11.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, Ressources.LASER.copy(),16,16,Direction.SOUTH,1);
		Laser laser6 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 12.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, Ressources.LASER.copy(),16,16,Direction.SOUTH,1);
		Laser laser7 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 13.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, Ressources.LASER.copy(),16,16,Direction.SOUTH,1);
		Laser laser8 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 14.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, Ressources.LASER.copy(),16,16,Direction.SOUTH,1);
		Laser laser9 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 15.25f, ORIGIN_Y + DEFAULT_TILE_SIZE * 9, Ressources.LASER.copy(),16,16,Direction.SOUTH,1);
		
		Laser laser10 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 7.75f, ORIGIN_Y + DEFAULT_TILE_SIZE * 12.5f, Ressources.LASER.copy(),16,16,Direction.NORTH,1);
		Laser laser11 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 8.75f, ORIGIN_Y + DEFAULT_TILE_SIZE * 12.5f, Ressources.LASER.copy(),16,16,Direction.NORTH,1);
		Laser laser12 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 9.75f, ORIGIN_Y + DEFAULT_TILE_SIZE * 12.5f, Ressources.LASER.copy(),16,16,Direction.NORTH,1);
		Laser laser13 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 10.75f, ORIGIN_Y + DEFAULT_TILE_SIZE * 12.5f, Ressources.LASER.copy(),16,16,Direction.NORTH,1);
		Laser laser14 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 11.75f, ORIGIN_Y + DEFAULT_TILE_SIZE * 12.5f, Ressources.LASER.copy(),16,16,Direction.NORTH,1);
		Laser laser15 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 12.75f, ORIGIN_Y + DEFAULT_TILE_SIZE * 12.5f, Ressources.LASER.copy(),16,16,Direction.NORTH,1);
		Laser laser16 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 13.75f, ORIGIN_Y + DEFAULT_TILE_SIZE * 12.5f, Ressources.LASER.copy(),16,16,Direction.NORTH,1);
		Laser laser17 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 14.75f, ORIGIN_Y + DEFAULT_TILE_SIZE * 12.5f, Ressources.LASER.copy(),16,16,Direction.NORTH,1);
		Laser laser18 = new Laser(ORIGIN_X + DEFAULT_TILE_SIZE * 15.75f, ORIGIN_Y + DEFAULT_TILE_SIZE * 12.5f, Ressources.LASER.copy(),16,16,Direction.NORTH,1);
		
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
		laser.add(laser14);
		laser.add(laser15);
		laser.add(laser16);
		laser.add(laser17);
		laser.add(laser18);
		
		
		Vector2f[] watchpoints = {new Vector2f(21,55)};
		
		// ORIGIN_X + DEFAULT_TILE_SIZE * 2, ORIGIN_Y + DEFAULT_TILE_SIZE * 10.5f = 21,55
		
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 22, ORIGIN_Y + DEFAULT_TILE_SIZE * 9.25f,enemyAnimation,20,20, watchpoints,aPath));
		watches.add(new Watch(ORIGIN_X + DEFAULT_TILE_SIZE * 22, ORIGIN_Y + DEFAULT_TILE_SIZE * 11.75f,enemyAnimation,20,20, watchpoints,aPath));
		
		player = new Player (ORIGIN_X + DEFAULT_TILE_SIZE * 2, ORIGIN_Y + DEFAULT_TILE_SIZE * 10.5f, playerAnimation, 20, 20);
		
		target = new Target(ORIGIN_X + DEFAULT_TILE_SIZE * 27, ORIGIN_Y + DEFAULT_TILE_SIZE * 10.5f,targetAnimation, deathAnimation, 20, 20);
		
		super.initObjects(container);
	}
}

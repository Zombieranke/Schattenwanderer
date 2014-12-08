package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class LevelHandler extends BasicGameState
{
	
	protected static final int levelOffset = 5;
	protected Player player;
	protected Target target;
	protected Watch watch;
	protected boolean alarm;
	protected int alarmTime;
	protected static final int alarmTimeDefault = 500;
	protected int playerHealth;
	protected static final int playerHealthDefault = 200;
	protected int playerEnergy;
	protected static final int playerEnergyDefault = 300;
	protected int durationChecker;
	protected int levelCount;
	protected ArrayList<SolidObject> solids = new ArrayList<SolidObject>();
	protected ArrayList<Laser> laser;
	protected ArrayList<Lever> lever;
	private boolean debug;
	private int state;
	private int isMoving;
	private int enemyIsMoving;
	private boolean mission = false;
	
	public void reset()
	{
		alarm = false;
		laser = new ArrayList<Laser>();
		lever = new ArrayList<Lever>();
		debug = false;
		state = 1;
		isMoving = 0;
		enemyIsMoving = 0;
	}
	
	public abstract void onLoad(GameContainer container) throws SlickException;
	
	public void initializeObjects(GameContainer container)
	{
		for(Laser l : laser){
			l.init(container.getWidth(),container.getHeight(),solids);
		}
		for(Lever l : lever){
			l.init();
		}
		watch.updateSight(solids);
		playerHealth = playerHealthDefault;
		playerEnergy = playerEnergyDefault;
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
	{
		reset();
		onLoad(container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.setColor(Color.black);
		g.drawString("Bewegung: Pfeiltasten\nWachenbewegung: WASD\nWachendrehung: Q,E", 40, 40);
		for(SolidObject w : solids)
		{
			w.render(g);
		}
		for(Laser l: laser)
		{
			l.render(g);
		}
		for(Lever l: lever)
		{
			l.render(g);
		}
		player.render(g);
		target.render(g);
		watch.render(g);
		if(debug)
		{
			for(SolidObject w : solids)
			{
				w.renderCollisionArea(g);
			}
			player.renderCollisionArea(g);
			watch.renderCollisionArea(g);
			target.renderCollisionArea(g);
			for(Laser l: laser)
			{
				l.renderCollisionArea(g);
			}
			for(Lever l: lever)
			{
				l.renderCollisionArea(g);
			}
		}
		if(alarm) //Alarmbalken und Info oben
		{
			g.setColor(Color.red);
			g.drawString("ALARM", container.getWidth()/2, 50);
			g.fillRect(container.getWidth()/2-125, 70, alarmTime/2, 10);
		}
		
		//Lebensbar anzeigen
		g.setColor(Color.green);
		g.drawString("Health", (container.getWidth()/4)*3-30, container.getHeight()-250);
		g.fillRect((container.getWidth()/4)*3-150, container.getHeight()-230, playerHealth*3/2, 20);
		
		if (mission){
			g.drawString("Target successfully killed",40, 850);
		}
		
		//Energiebalken
		g.setColor(Color.yellow);
		g.drawString("Energy", (container.getWidth()/4)+20,container.getHeight()-250);
		g.fillRect((container.getWidth()/4)-100, container.getHeight()-230, playerEnergy, 20);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		Input input = container.getInput();
		//Ganzes Alarmskrimskrams Anfang
		state = 0;
		
		player.animation.update(delta);
		watch.animation.update(delta);
		
		for(Laser l: laser)
		{
			if(l.isOn())
			{
				if(player.checkCollision(l.getBeam())&& player.getStealth()==false)
			    {
			    	alarm = true;
			    	alarmTime = alarmTimeDefault;
			    }

			}
		}
		
		if(player.checkCollision(watch.getSight())&& player.getStealth()==false)
	    {
	    	alarm = true;
	    	alarmTime = alarmTimeDefault;
	    	playerHealth -= Math.ceil(durationChecker/20);
	    	durationChecker++;
	    }
		else
		{
			durationChecker = 0;
		}
		
		alarmTime--;
		
    	if(alarmTime<0)
    	{
    		alarm = false;
    	}
    	
    	//Ganzes Alarmskrimskrams Ende
		
		//Debug Toggle
    	if(input.isKeyPressed(Input.KEY_F3))
    	{
    		debug = !debug;
    	}   	
    	
		if(input.isKeyPressed(Input.KEY_ESCAPE)||playerHealth<=0)
		{
			game.enterState(1);
			playerHealth = playerHealthDefault;
			alarm = false;
		}
		
		if(input.isKeyPressed(Input.KEY_F))
		{
			player.setStealth(false);
			for(Lever l : lever){
				if(player.checkCollision(l)){
					l.flipLever();
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_LEFT))
		{
			player.setStealth(false);
			isMoving = 1;
			for(int i = -2;i<0;i++)
			{
				if(player.canMove(i, 0, solids))
				{
					player.move(i, 0);
					state += 8;
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			player.setStealth(false);
			isMoving = 1;
			for(int i = 2;i>0;i--)
			{
				if(player.canMove(i, 0, solids))
				{
					player.move(i, 0);
					state += 2;
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_UP))
		{
			player.setStealth(false);
			isMoving = 1;
			for(int i = -2;i<0;i++)
			{
				if(player.canMove(0, i, solids))
				{
					player.move(0, i);
					state += 1;
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_DOWN))
		{
			player.setStealth(false);
			isMoving = 1;
			for(int i = 2;i>0;i--)
			{
				if(player.canMove(0, i, solids))
				{
					player.move(0, i);
					state += 4;
					break;
				}
		    }
		}
		
		if (input.isKeyDown(Input.KEY_DOWN) == false && input.isKeyDown(Input.KEY_LEFT) == false && 
				input.isKeyDown(Input.KEY_RIGHT) == false && input.isKeyDown(Input.KEY_UP)  == false && isMoving == 1)
		{
			isMoving = 0;
		}
		
		//Stealth skill
		if(input.isKeyPressed(Input.KEY_C))
		{
			player.switchStealth();
		}
		
		if(player.getStealth()==true)
		{
			playerEnergy-=2;
		}
		else if(playerEnergy<playerEnergyDefault)
		{
			playerEnergy++;
		}
		if(playerEnergy<=0)
		{
			player.setStealth(false);
		}
		
	    //Debug Watch Movement
		if(input.isKeyDown(Input.KEY_A))
		{
			enemyIsMoving = 1;
			for(int i = -2;i<0;i++)
			{
				if(watch.canMove(i, 0, solids))
				{
					
					watch.move(i, 0);
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_D))
		{
			enemyIsMoving = 1;
			for(int i = 2;i>0;i--)
			{
				if(watch.canMove(i, 0, solids))
				{
					
					watch.move(i, 0);
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_W))
		{
			enemyIsMoving = 1;
			for(int i = -2;i<0;i++)
			{
				if(watch.canMove(0, i, solids))
				{
					
					watch.move(0, i);
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_S))
		{
			enemyIsMoving = 1;
			for(int i = 2;i>0;i--)
			{
				if(watch.canMove(0, i, solids))
				{
					
					watch.move(0, i);
					break;
				}
		    }
		}
		
		if (input.isKeyDown(Input.KEY_S) == false && input.isKeyDown(Input.KEY_A) == false && 
				input.isKeyDown(Input.KEY_D) == false && input.isKeyDown(Input.KEY_W)  == false && enemyIsMoving == 1)
		{
			enemyIsMoving = 0;
		}
		
		if(input.isKeyDown(Input.KEY_Q))
		{
			watch.setDirection(watch.getDirection()-1);
		}
		
		if(input.isKeyDown(Input.KEY_E))
		{
			watch.setDirection(watch.getDirection()+1);
		}
		
		switch(state)
		{
		case 1: player.setRotation(90); break;
		case 2: player.setRotation(180); break;
		case 3: player.setRotation(135); break;
		case 4: player.setRotation(270); break;
		case 6: player.setRotation(225); break;
		case 12: player.setRotation(315); break;
		case 8: player.setRotation(360); break;
		case 9: player.setRotation(45); break;
		}
		
		if (isMoving == 0)
		{
			player.animation.setCurrentFrame(2);
			player.animation.stop();
		}
		else 
		{
			player.animation.start();
		}
		
		if (enemyIsMoving == 0)
		{
			watch.animation.setCurrentFrame(2);
			watch.animation.stop();
		}
		
		else
		{
			watch.setRotation(watch.getDirection()+180);
			watch.animation.start();
		}
		
		player.update(delta);
		watch.update(delta);
		watch.updateSight(solids);
		
		if(target.checkCollision(player)){
			mission = true;
		}
	}

	@Override
	public int getID()
	{
		return levelOffset + levelCount;
	}
}

package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
public abstract class LevelHandler extends BasicGameState
{
	
	protected static final int levelOffset = 5;
	protected Player player;
	protected Target target;
	protected Exit exit;
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
	private boolean isMoving;
	private boolean enemyIsMoving;
	private boolean mission;
	
	public void reset()
	{
		alarm = false;
		laser = new ArrayList<Laser>();
		lever = new ArrayList<Lever>();
		debug = false;
		state = 1;
		isMoving = false;
		enemyIsMoving = false;
		mission = false;
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
		watch.setRotation(watch.getDirection()+180);
		player.setRotation(0);
		target.animation.setCurrentFrame(2);
		target.animation.stop();
		exit.animation.setCurrentFrame(0);
		exit.animation.stop();
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
		g.drawString("Bewegung: Pfeiltasten\nSchalter betätigen: F\nStealth: C", 40, 40);
		g.drawString("Wachenbewegung: WASD\nWachendrehung: Q,E", 300, 40);
		
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
		target.render(g);
		watch.render(g);
		player.render(g);
		exit.render(g);
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
		target.missionAccomplished(mission);
		
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
		exit.animation.update(delta);
		
		
		for(Laser l: laser)
		{
			if(l.isOn())
			{
				if(player.checkCollision(l.getBeam())&& !player.isStealth())
			    {
			    	alarm = true;
			    	alarmTime = alarmTimeDefault;
			    }

			}
		}
		
		if(player.checkCollision(watch.getSight())&& !player.isStealth())
	    {
	    	alarm = true;
	    	alarmTime = alarmTimeDefault;
	    	durationChecker++;
	    	if(durationChecker>=10)
	    	{
	    		playerHealth -= 3;
	    	}
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
    	
		if(input.isKeyPressed(Input.KEY_ESCAPE))
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
		
		isMoving = false;
		
		if(input.isKeyDown(Input.KEY_LEFT))
		{
			player.setStealth(false);
			isMoving = true;
			for(int i = -2;i<0;i++)
			{
				if(player.canMove(i, 0, solids, exit))
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
			isMoving = true;
			for(int i = 2;i>0;i--)
			{
				if(player.canMove(i, 0, solids, exit))
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
			isMoving = true;
			for(int i = -2;i<0;i++)
			{
				if(player.canMove(0, i, solids, exit))
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
			isMoving = true;
			for(int i = 2;i>0;i--)
			{
				if(player.canMove(0, i, solids, exit))
				{
					player.move(0, i);
					state += 4;
					break;
				}
		    }
		}
		
		
		//Stealth skill
		if(input.isKeyPressed(Input.KEY_C))
		{
			player.switchStealth();
		}
		
		if(player.isStealth()==true)
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
		
		enemyIsMoving = false;
		
		if(input.isKeyDown(Input.KEY_A))
		{
			enemyIsMoving = true;
			for(int i = -2;i<0;i++)
			{
				if(watch.canMove(i, 0, solids, exit))
				{
					
					watch.move(i, 0);
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_D))
		{
			enemyIsMoving = true;
			for(int i = 2;i>0;i--)
			{
				if(watch.canMove(i, 0, solids, exit))
				{
					
					watch.move(i, 0);
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_W))
		{
			enemyIsMoving = true;
			for(int i = -2;i<0;i++)
			{
				if(watch.canMove(0, i, solids, exit))
				{
					
					watch.move(0, i);
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_S))
		{
			enemyIsMoving = true;
			for(int i = 2;i>0;i--)
			{
				if(watch.canMove(0, i, solids, exit))
				{
					
					watch.move(0, i);
					break;
				}
		    }
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
		
		if (!isMoving)
		{
			player.animation.setCurrentFrame(2);
			player.animation.stop();
		}
		else 
		{
			player.animation.start();
		}
		
		if (!enemyIsMoving)
		{
			watch.animation.setCurrentFrame(2);
			watch.animation.stop();
		}
		
		else
		{
			watch.setRotation(watch.getDirection()+180);
			watch.animation.start();
		}
		
		//Funktion des Ausgangs Anfang
		if(mission && !alarm)
		{
			exit.animation.start();
			exit.animation.stopAt(7); //Falls die Bedingung erfüllt ist, öffnet sich die Tür und bleibt offen
		}
		
		if(exit.animation.getFrame()==7 && !alarm)
		{
			exit.setOpen(true);
		}
		else
		{
			exit.setOpen(false);
		}
		
		if(exit.animation.getFrame()>0 && alarm && mission)
		{
			exit.animation.stop();
			exit.animation.start();	//Stoppt, falls der Spieler den Alarm auslöst und rennt dann bis die Tür wieder ganz zu ist
			exit.animation.stopAt(0);	//Leider muss sie einmal ganz aufgehen (=pingpong) bevor sie dann ganz zugeht
		}
		//Funktion des Ausgangs Ende
		
		//Spielende
		if(playerHealth<=0)
		{
			game.enterState(3, new FadeOutTransition(), new FadeInTransition());
			playerHealth = playerHealthDefault;
			alarm = false;
		}
		
		if(player.checkCollision(exit) && exit.isOpen()==true)
		{
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
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

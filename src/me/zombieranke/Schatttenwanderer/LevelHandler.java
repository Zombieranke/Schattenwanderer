package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
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
	protected static final int alarmTimeDefault = 600;
	protected int playerHealth;
	protected static final int playerHealthDefault = 600;
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
	
	
	//Musik - bleibt ja gleich - vorerst zumindest
	protected Music alarmMusic;
	protected Music gameMusic;
	protected Sound exitSound;
	protected boolean exitSoundWasPlayed;
	protected Sound leverSound;
	
	
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
	
	public void initializeObjects(GameContainer container) throws SlickException
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
		alarmMusic = new Music("res/Alarm_Music.ogg",false);
		gameMusic = new Music("res/Game_Music.ogg",false);
		exitSound = new Sound("res/Exit_Sound.ogg");
		leverSound = new Sound("res/Lever_Sound.ogg");
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
		g.drawString("Bewegung: Pfeiltasten\nSchalter betätigen: F\nStealth: C\nSprint: V", 40, 20);
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
		player.render(g);
		target.render(g);
		watch.render(g);
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
		g.setColor(new Color(0.2f,0.2f,0.2f));
		g.fillRect(755, 789, 310, 30);
		g.setColor(Color.green);
		g.fillRect(760, 794, playerHealth/2, 20); //300px Healthbar
		
		if (mission){
			g.drawString("Target successfully killed",40, 850);
			
		}
		target.missionAccomplished(mission);
		
		//Energiebalken
		g.setColor(new Color(0.2f,0.2f,0.2f));
		g.fillRect(215, 789, 310, 30);
		g.setColor(Color.yellow);
		g.fillRect(220, 794, playerEnergy, 20); //300px Energybar
		
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
	    		playerHealth -= 9;
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
    	
    	if(!alarm && (playerHealth<(playerHealthDefault/2)))
    	{
    		playerHealth++;
    	}
    	
    	
    	
    	
    	//Ganzes Alarmskrimskrams Ende
		
		//Debug Toggle
    	if(input.isKeyPressed(Input.KEY_F3))
    	{
    		debug = !debug;
    	}   	
    	
    	if(input.isKeyPressed(Input.KEY_H))
    	{
    		alarm=false;
    	}  
    	
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			gameMusic.stop();
			alarmMusic.stop();
			playerHealth = playerHealthDefault;
			alarm = false;
			game.enterState(1);
		}
		
		if(input.isKeyPressed(Input.KEY_F))
		{
			player.setStealth(false);
			for(Lever l : lever){
				if(player.checkCollision(l)){
					l.flipLever();
					leverSound.play(1,0.3f);
				}
			}
		}
		
		//Player Movement Start
		isMoving = false;
		
		if(input.isKeyDown(Input.KEY_LEFT))
		{
			player.setStealth(false);
			isMoving = true;
			if(player.isSprint())
			{
				for(int i = -4;i<0;i+=2)
				{
					if(player.canMove(i, 0, solids, exit))
					{
						player.move(i, 0);
						state += 8;
						break;
					}
			    }
			}
			else
			{
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
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			player.setStealth(false);
			isMoving = true;
			if(player.isSprint())
			{
				for(int i = 4;i>0;i-=2)
				{
					if(player.canMove(i, 0, solids, exit))
					{
						player.move(i, 0);
						state += 2;
						break;
					}
			    }
			}
			else
			{
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
		}
		
		if(input.isKeyDown(Input.KEY_UP))
		{
			player.setStealth(false);
			isMoving = true;
			if(player.isSprint())
			{
				for(int i = -4;i<0;i+=2)
				{
					if(player.canMove(0, i, solids, exit))
					{
						player.move(0, i);
						state += 1;
						break;
					}
			    }
			}
			else
			{
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
		}
		
		if(input.isKeyDown(Input.KEY_DOWN))
		{
			player.setStealth(false);
			isMoving = true;
			if(player.isSprint())
			{
				for(int i = 4;i>0;i-=2)
				{
					if(player.canMove(0, i, solids, exit))
					{
						player.move(0, i);
						state += 4;
						break;
					}
			    }
			}
			else
			{
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
		}
		//Player Movement Ende
		
		//Stealth und Sprint skill
		if(input.isKeyPressed(Input.KEY_V))
		{
			player.switchSprint();
		}
		
		if(input.isKeyPressed(Input.KEY_C))
		{
			player.switchStealth();
		}
		
		if(player.isStealth() || player.isSprint())
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
			player.setSprint(false);
		}
		//Skills Ende
		
		
		
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
			if(!exitSoundWasPlayed)
			{
				exitSound.play(1,0.5f);
				exitSoundWasPlayed = true;
			}
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
		
		if(alarm)
    	{
    		exitSoundWasPlayed = false;
    	}
		//Funktion des Ausgangs Ende
		
		//Miracle of Sound Anfang
    	if(alarm && !alarmMusic.playing())
    	{
    		gameMusic.stop();
    		alarmMusic.loop(1,1);
    	}
    	if(!alarm && !gameMusic.playing())
    	{
    		alarmMusic.stop();
    		gameMusic.loop(1,0.4f);
    	}
		//Miracle of Sound Ende
    	
    	
		//Spielende
		if(playerHealth<=0)
		{
			game.enterState(3, new FadeOutTransition(), new FadeInTransition());
			playerHealth = playerHealthDefault;
			alarm = false;
			gameMusic.stop();
			alarmMusic.stop();
		}
		
		if(player.checkCollision(exit) && exit.isOpen()==true)
		{
			gameMusic.stop();
			alarmMusic.stop();
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

package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;

/**The basic game Logic that every Level must extend*/
public abstract class LevelHandler extends BasicGameState
{
	/**The index where the levels start(allows virtual IDs of level)*/
	protected static final int levelOffset = 5;
	
	/**Indicates the number of the level (levelOffset + levelCount = levelID)*/
	protected int levelCount;
	
	/**The Player*/
	protected Player player;
	
	/**The target that the player has to kill*/
	protected Target target;
	
	/**The exit that the player has to escape through*/
	protected Exit exit;
	
	protected int watchSpeed;
	
	protected boolean sneaking;
	
	/**A boolean indicating whether the alarm is currently active*/ 
	protected boolean alarm;
	
	/**The remaining alarm time*/
	protected int alarmTime;
	
	/**The default time that an alarm lasts*/
	protected static final int alarmTimeDefault = 600;
		
	/**A variable to track how long the player stood in the WatchSightArea*/
	protected int gracePeriod;
	
	/**Keeps track when the player last get out of stealth*/
	protected int stealthCooldown;
	
	/**Default time it takes for a lever to reset in alarm situations*/
	protected static final int leverResetTimeDefault = 150;
	
	/**The remaining time until all levers reset*/
	protected int leverResetTime = 0;
	
	protected ArrayList<Watch> watches;
	
	/**Everything that cannot be trespassed*/
	protected ArrayList<SolidObject> solids = new ArrayList<SolidObject>();
	
	/**All lasers in the level
	 * @see Laser
	 */
	protected ArrayList<Laser> laser;
	
	/**All levers in the level
	 * @see Lever
	 */
	protected ArrayList<Lever> lever;
	
	/**Indicates if we are in debug view(shows all hitboxes)*/
	private boolean debug;
	
	/**Indicates which direction the player looks(use with caution)*/
	private int state;
	
	/**Indicates whether the player has succeeded in his mission(killed the target)*/
	private boolean mission;
	
	/**The music to play when there is an alarm*/
	protected Music alarmMusic;
	
	/**The music to play when there is no alarm*/
	protected Music gameMusic;
	
	protected Music endMusic;
	
	/**The sound to play when the exit opens*/
	protected Sound exitSound;
	
	/**The sound to play when the player flicks a lever*/
	protected Sound leverSound;
	
	/** Background Image */
	protected Image game_background;
	
	/** Background for the "outer layer"/UI Elements outside of boundaries as a tile */
	protected Image UI_Background;
	
	protected LevelMap levelMap;
	
	protected AStarPathFinder aPath;
	
	/**Reset everything that is the same between levels*/
	public void reset()
	{
		alarm = false;
		laser = new ArrayList<Laser>();
		lever = new ArrayList<Lever>();
		watches = new ArrayList<Watch>();
		debug = false;
		state = 1;
		mission = false;
		exit.setOpen(false);
		exit.animation.setCurrentFrame(0);
	}
	
	/**Function to reload stuff on level entry
	 * 
	 * @param container The container holding the game
	 */ 
	public abstract void onLoad(GameContainer container) throws SlickException;
	
	/**Initialize all game objects to be able to use them later
	 * 
	 * @param container The container holding the game
	 * @throws SlickException
	 */
	public void initObjects(GameContainer container) throws SlickException
	{
		for(Laser l : laser)
		{
			l.init(container.getWidth(),container.getHeight(),solids);
		}
		for(Lever l : lever)
		{
			l.init();
		}
		
		for(Watch w: watches)
		{
			w.init(solids);
			w.setRotation(w.getDirection()+180);
			w.setMoving(false);
			w.setNoise(w.getNoiseDefault());
		}
		
		player.setHealth();
		player.setEnergy();
		player.setRotation(0);
		target.animation.setCurrentFrame(2);
		target.animation.stop();
		exit.animation.setCurrentFrame(0);
		exit.animation.stop();
		player.setMoving(false);
	}
	
	/** Used to reset the Level when exiting
	 * 
	 * @param container The container holding the game
	 * @param game The game holding this state
	 * @throws SlickException
	 */
	public void resetOnLeave(GameContainer container, StateBasedGame game) throws SlickException
	{
		reset();
		onLoad(container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		
		UI_Background.draw(0, 0);
		game_background.draw(100, 100);
		g.setColor(Color.black);
		g.drawString("Bewegung: Pfeiltasten\nSchalter betätigen: F\nStealth: C\nSprint: V", 40, 20);
		g.drawString("Schleichen: Shift", 300, 20);
		g.drawString("Wachenbewegung: WASD\nWachendrehung: Q,E", 300, 60);
		
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
		
		for(Watch w: watches)
		{
			w.render(g);
		}
		
		target.render(g);
		player.render(g);
		exit.render(g);
		if(debug)
		{
			for(SolidObject w : solids)
			{
				w.renderCollisionArea(g);
			}
			player.renderCollisionArea(g);
			for(Watch w : watches)
			{
				w.renderCollisionArea(g);
			}
			
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
			if(leverResetTime>0)
			{
				g.drawString("Lever Reset in "+ Math.ceil(leverResetTime/50.0)+"!", container.getWidth()/2 +100, 60);
			}
		}
		
		//Lebensbar anzeigen
		g.setColor(new Color(0.2f,0.2f,0.2f));
		g.fillRect(755, 789, 310, 30);
		g.setColor(Color.green);
		g.fillRect(760, 794, player.getHealth(), 20); //300px Healthbar
		
		if (mission){
			g.drawString("Target successfully killed",40, 850);
		}
		
		//Energiebalken
		g.setColor(new Color(0.2f,0.2f,0.2f));
		g.fillRect(215, 789, 310, 30);
		g.setColor(Color.yellow);
		g.fillRect(220, 794, player.getEnergy(), 20); //300px Energybar
		
		g.setColor(Color.red);	
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		Input input = container.getInput();
		
		state = 0;
		
		player.animation.update(delta);
		for(Watch w : watches)
		{
			w.animation.update(delta);
		}
		exit.animation.update(delta);
		
		checkAlarm();
		
    	if(!alarm && (player.getHealth()<(player.getHealthDefault()/2)))
    	{
    		player.setHealth(player.getHealth() + 0.5f);
    	}
    	
		//Debug Toggle
    	if(input.isKeyPressed(Input.KEY_F3))
    	{
    		debug = !debug;
    	}   	
    	
    	if(input.isKeyPressed(Input.KEY_H))
    	{
    		deactivateAlarm();
    		player.setHealth();
    		player.setEnergy();
    	}  
    	
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			gameMusic.stop();
			alarmMusic.stop();
			resetOnLeave(container, game);
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
		player.setMoving(false);
		sneaking = false;
		
		if((input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT)) && !player.isSprint())
		{
			sneaking = true;
		}
		
		if(input.isKeyDown(Input.KEY_LEFT))
		{
			player.setStealth(false);
			player.setMoving(true);
			state += 8;
			if(player.isSprint())
			{
				if(player.canMove(-player.getSpeedSprint(), 0, solids, exit))
				{
					player.move(-player.getSpeedSprint(), 0);
				}
			}
			else if(sneaking)
			{

				if(player.canMove(-player.getSpeedSneak(), 0, solids, exit))
				{
					player.move(-player.getSpeedSneak(), 0);
				}
			}
			else
			{

				if(player.canMove(-player.getSpeedWalk(), 0, solids, exit))
				{
					player.move(-player.getSpeedWalk(), 0);
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			player.setStealth(false);
			player.setMoving(true);
			state += 2;
			if(player.isSprint())
			{
				if(player.canMove(player.getSpeedSprint(), 0, solids, exit))
				{
					player.move(player.getSpeedSprint(), 0);
				}
			}
			else if(sneaking)
			{
				if(player.canMove(player.getSpeedSneak(), 0, solids, exit))
				{
					player.move(player.getSpeedSneak(), 0);
				}
			}
			else
			{
				if(player.canMove(player.getSpeedWalk(), 0, solids, exit))
				{
					player.move(player.getSpeedWalk(), 0);
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_UP))
		{
			player.setStealth(false);
			player.setMoving(true);
			state += 1;
			if(player.isSprint())
			{
				if(player.canMove(0, -player.getSpeedSprint(), solids, exit))
				{
					player.move(0, -player.getSpeedSprint());
				}
			}
			else if(sneaking)
			{
				if(player.canMove(0, -player.getSpeedSneak(), solids, exit))
				{
					player.move(0, -player.getSpeedSneak());
				}
			}
			else
			{
				if(player.canMove(0, -player.getSpeedWalk(), solids, exit))
				{
					player.move(0, -player.getSpeedWalk());
				} 
			}
		}
		
		if(input.isKeyDown(Input.KEY_DOWN))
		{
			player.setStealth(false);
			player.setMoving(true);
			state += 4;
			if(player.isSprint())
			{
				if(player.canMove(0, player.getSpeedSprint(), solids, exit))
				{
					player.move(0, player.getSpeedSprint());
				}
			}
			else if(sneaking)
			{
				if(player.canMove(0, player.getSpeedSneak(), solids, exit))
				{
					player.move(0, player.getSpeedSneak());
				}
			}
			else
			{
				if(player.canMove(0, player.getSpeedWalk(), solids, exit))
				{
					player.move(0, player.getSpeedWalk());
				}
			}
		}
		//Player Movement Ende
		
		//Stealth und Sprint skill
		if(input.isKeyPressed(Input.KEY_V))
		{
			player.switchSprint();
		}
		
		if(input.isKeyPressed(Input.KEY_C) && stealthCooldown<=0)
		{
			if(player.isStealth())
			{
				stealthCooldown = 50;
			}
			player.switchStealth();
		}
		
		if(!player.isStealth())
		{
			stealthCooldown--;
		}
		
		if(player.isStealth())
		{
			player.setEnergy(player.getEnergy() - 1.2f);
		}
		
		if(player.isSprint())
		{
			player.setEnergy(player.getEnergy() - 2.5f);
		}
		
		
		if(!(player.isStealth() || player.isSprint()) && player.getEnergy()<player.getEnergyDefault())
		{
			player.setEnergy(player.getEnergy() + 0.5f);
		}
		if(player.getEnergy()<=0)
		{
			player.setStealth(false);
			player.setSprint(false);
		}
		//Skills Ende
		
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
		
		if(!player.isMoving())
		{
			player.animation.setCurrentFrame(2);
			player.animation.stop();
		}
		else 
		{
			player.animation.start();
		}
		
		//Funktion des Ausgangs Anfang(old)
		/*if(mission && !alarm)
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
			exit.animation.start();	//Stoppt, falls der Spieler den Alarm ausl�st und rennt dann bis die T�r wieder ganz zu ist
			exit.animation.stopAt(0);	//Leider muss sie einmal ganz aufgehen (=pingpong) bevor sie dann ganz zugeht
		}
		
		if(alarm)
    	{
    		exitSoundWasPlayed = false;
    	}*/
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
		if(player.getHealth()<=0)
		{
			Fail fail = (Fail) game.getState(3);
			fail.setLast(this.getID());
			gameMusic.stop();
			alarmMusic.stop();
			resetOnLeave(container, game);
			game.enterState(3, new FadeOutTransition(), new FadeInTransition());
		}
		
		if(player.checkCollision(exit) && exit.isOpen())
		{
			Success success = (Success) game.getState(2);
			success.setLast(this.getID());
			gameMusic.stop();
			alarmMusic.stop();
			endMusic.loop(1,1);
			resetOnLeave(container, game);
			game.enterState(2, new FadeOutTransition(), new FadeInTransition());
		}
		
		
		//Hear Circle Logic
		float hearMax = player.isSprint() ? 75 : 50;
		float incrementPerUpdate = player.isSprint() ? 2 : 1;
		float alarmMultiplier = alarm ? 1.3f : 1;
		hearMax *= alarmMultiplier;
		float calmDown = 1.7f - alarmMultiplier;
		
		for(Watch w : watches)
		{
			if(player.isMoving() && (w.getNoise() < hearMax) && !sneaking)
			{
				if( w.getNoise() < hearMax - incrementPerUpdate)
				{
					w.addNoise(incrementPerUpdate);
				}
			}
			else if(w.getNoise()>w.getNoiseDefault()*alarmMultiplier)
			{
				w.addNoise(-calmDown);
			}
		}
			
		//Updates
		player.update(delta);
		
		for(Watch w : watches)
		{
			w.move(solids,exit);
			w.update(delta);
			w.updateSight(solids);
		}
		
		//Kill target
		if(target.checkCollision(player) && !mission){
			mission = true;
			target.setDead(true);
			target.animation2.setCurrentFrame(0);
			target.animation2.start();
			target.animation2.stopAt(9);
			openExit();
		}
		
		//Death Animation
			target.animation2.update(delta);
		
	}
	
	/**Checks if the alarm should be activated, ticks it down if it is, deactivates it if alarmTime has reached 0 and 
	 * deals damage to the player if he stands in front of a watch*/
	private void checkAlarm()
	{

		for(Laser l: laser)
		{
			if(l.isOn())
			{
				if(player.checkCollision(l.getBeam())&& !player.isStealth())
			    {
			    	activateAlarm();			    
			    }
			}
		}
		
		boolean inSight = false;
		
		for(Watch w : watches)
		{
			if((player.checkCollision(w.getSightCone()) || player.checkCollision(w.getHearCircle()))  && !player.isStealth())
		    {
				inSight = true;
				if(!alarm)
				{
					activateAlarm();
				}
				
				if(w.getPath() == null)
				{
					w.calculatePath(new Vector2f(player.getX()/8, player.getY()/8));
				}
		    }

			
			if(target.checkCollision(w.getSightCone()) && !target.isDiscovered())
			{
				activateAlarm();
				target.setDiscovered(true);
			}
		}
		
		if(inSight)
		{
			gracePeriod++;
			if(gracePeriod>=10)
    		{
    			player.setHealth(player.getHealth() - 3);
    		}
		}
		else
		{
			gracePeriod = 0;
		}
		
		if(alarm)
			{	
			alarmTime--;
			
	    	if(alarmTime<0)
	    	{
	    		deactivateAlarm();
	    	}
		}
		
		leverResetTime --;
		if(leverResetTime==0)
		{
			for(Lever l: lever)
			{
				if(l.isFlipped())
				{
					l.flipLever();
				}
			}
		}
	}
	
	/**Activates the alarm
	 * 
	 * @param alarmTime How long the alarm should last
	 */
	private void activateAlarm()
	{
			alarm = true;
	    	this.alarmTime = alarmTimeDefault;
	    	onAlarmActivate();
	}
	
	@SuppressWarnings("unused")
	private void activateAlarm(int alarmTime)
	{
			alarm = true;
	    	this.alarmTime = alarmTime;
	    	onAlarmActivate();
	}
	
	/**Deactivates the alarm*/
	private void deactivateAlarm()
	{
			alarm = false;
			onAlarmDeactivate();
	}
	
	/**Event that is fired when the alarm gets activated. Extends watch sight radius and closes the door*/
	public void onAlarmActivate()
	{
		for(Watch w : watches)
		{
			w.setSightRadius(150);
			w.setAlarmed(alarm);
			w.setPath(null);
		}
		
		//leverResetTime = leverResetTimeDefault;
		closeExit();
	}
	
	/**Event that gets fired when the alarm gets deactivated. Resets watch sights radius and reopens the door(if mission has already been accomplished)*/
	public void onAlarmDeactivate()
	{
		for(Watch w : watches)
		{
			w.setSightRadius(100);
			w.setAlarmed(alarm);
		}
		
		openExit();
	}
	
	
	/**Function to open the exit*/
	public void openExit()
	{
		if(mission && !alarm)
		{
			exit.animation.start();
			exit.animation.stopAt(7);
			exitSound.play(1,0.5f);
			exit.setOpen(true);
		}
	}
	/**Function to close the exit*/
	public void closeExit()
	{
		exit.animation.start();
		exit.animation.stopAt(0);
		exit.setOpen(false);
	}
	
	@Override
	public int getID()
	{
		return levelOffset + levelCount;
	}
}

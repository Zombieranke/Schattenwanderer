package me.zombieranke.Schatttenwanderer;

import java.io.IOException;
import java.util.ArrayList;

import me.zombieranke.utils.Ressources;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SavedState;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Circle;
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
	protected static final int levelOffset = 8;
	
	/**Indicates the number of the level (levelOffset + levelCount = levelID)*/
	protected int levelNumber;
	
	public static int levelCount;
	
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
	
	private boolean inLaser = false;
	
	/**Indicates whether the player has succeeded in his mission(killed the target)*/
	protected boolean mission;
	
	/**The music to play when there is an alarm*/
	protected Music alarmMusic;
	
	/**The music to play when there is no alarm*/
	protected Music gameMusic;
	
	protected Music endMusic;
	
	protected Music deathMusic;
	
	/**The sound to play when the exit opens*/
	protected Sound exitSound;
	
	/**The sound to play when the player flicks a lever*/
	protected Sound leverSound;
	
	/** Background Image */
	protected Image game_Background;
	
	/** Background for the "outer layer"/UI Elements outside of boundaries as a tile */
	protected Image UI_Background;
	
	protected LevelMap levelMap;
	
	private boolean toReset;
	
	protected AStarPathFinder aPath;
	
	protected void renderSpecificBefore(GameContainer container, StateBasedGame game, Graphics g){}
	
	protected void renderSpecificAfter(GameContainer container, StateBasedGame game, Graphics g){}
	
	protected void updateSpecific(GameContainer container, StateBasedGame game, int delta){}
	
	/**Reset everything that is the same between levels*/
	public void reset()
	{
		alarm = false;
		laser = new ArrayList<Laser>();
		lever = new ArrayList<Lever>();
		watches = new ArrayList<Watch>();
		debug = false;
		mission = false;
		exit.setOpen(false);
		exit.animation.setCurrentFrame(0);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
	{
		if(toReset)
		{
			reset(container, game);
		}
		toReset=false;
	}
	
	/**Function to reload stuff on level leave
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
		player.setSprint(false);
		player.setStealth(false);
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
	public void reset(GameContainer container, StateBasedGame game) throws SlickException
	{
		if(levelCount<levelNumber)
		{
			levelCount = levelNumber;
		}
		reset();
		onLoad(container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		renderSpecificBefore(container,game,g);
		
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
			exit.renderCollisionArea(g);
			for(Laser l: laser)
			{
				l.renderCollisionArea(g);
			}
			for(Lever l: lever)
			{
				l.renderCollisionArea(g);
			}
			g.setColor(Color.red);
			g.fillRect(10,14,63,11);
			container.setShowFPS(true);
			levelMap.renderTiles(g);
		}
		else
		{
			container.setShowFPS(false);
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
		g.fillRect(760, 794, player.getHealth(), 20); //300px Healthbar
		
		
		//Energiebalken
		g.setColor(new Color(0.2f,0.2f,0.2f));
		g.fillRect(215, 789, 310, 30);
		g.setColor(Color.yellow);
		g.fillRect(220, 794, player.getEnergy(), 20); //300px Energybar
		
		g.setColor(Color.red);	
		
		renderSpecificAfter(container,game,g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		updateSpecific(container,game,delta);
		Input input = container.getInput();
		
		if(input.isKeyPressed(Input.KEY_L) && input.isKeyPressed(Input.KEY_T)){
			Success success = (Success) game.getState(4);
			success.setLast(this.getID());
			
			SavedState st = new SavedState("/Schattenwanderer_State");
			try 
			{
				st.load();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			if(st.getNumber("lastSuccessful") < this.getID() - levelOffset)
			{
				st.setNumber("lastSuccessful", this.getID() - levelOffset);
			}
			
			try
			{
				st.save();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			gameMusic.stop();
			alarmMusic.stop();
			toReset = true;
			if(levelNumber==levelCount)
			{
				game.enterState(6, new FadeOutTransition(), new FadeInTransition());
			}
			else
			{
				game.enterState(4, new FadeOutTransition(), new FadeInTransition());
			}
		}
		
		if(input.isKeyPressed(Input.KEY_P))
		{
			Pause success = (Pause) game.getState(8);
			success.setLast(this.getID());
			game.enterState(8);
		}
		
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
    	
    	if(input.isKeyPressed(Input.KEY_H) && input.isKeyPressed(Input.KEY_O))
    	{
    		deactivateAlarm();
    		player.setHealth();
    		player.setEnergy();
    	}  
    	
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			gameMusic.stop();
			alarmMusic.stop();
			toReset = true;
			game.enterState(1);
		}
		
		if(input.isKeyPressed(Input.KEY_C))
		{
			player.setStealth(false);
			for(Lever l : lever){
				if(player.checkCollision(l)){
					l.flipLever();
					leverSound.play(1,Ressources.Volume);
				}
			}
		}
		
		//Player Movement Start
		player.setMoving(false);
		sneaking = false;
		
		if((input.isKeyDown(Input.KEY_SPACE) && !player.isSprint()))
		{
			sneaking = true;
		}
		
		Vector2f dir = new Vector2f(0,0);
		
		if(input.isKeyDown(Input.KEY_LEFT))
		{
			dir.x -= 1;
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			dir.x += 1;
		}
		
		if(input.isKeyDown(Input.KEY_UP))
		{
			dir.y -=1;
		}
		
		if(input.isKeyDown(Input.KEY_DOWN))
		{
			dir.y += 1;
		}
		
		if(dir.length()>0){
			player.setStealth(false);
			player.setMoving(true);
			int speed = player.isSprint() ? player.getSpeedSprint() : (sneaking ? player.getSpeedSneak() : player.getSpeedWalk());
			dir.normalise().scale(speed);
			if(player.canMove(dir.x, dir.y, solids, exit))
			{
				player.move(dir.x, dir.y);
			}
			else if(player.canMove(dir.x, 0, solids, exit))
			{
				player.move(dir.x, 0);
			}
			else if(player.canMove(0, dir.y, solids, exit))
			{
				player.move(0, dir.y);
			}
			player.setRotation(Math.round(dir.getTheta()));
		}
		//Player Movement Ende
		
		//Stealth und Sprint skill
		if(input.isKeyPressed(Input.KEY_X))
		{
			player.switchSprint();
		}
		
		if((input.isKeyPressed(Input.KEY_Y) || input.isKeyPressed(Input.KEY_Z)))
		{
			player.switchStealth();
		}
		
		
		if(player.isStealth())
		{
			player.setEnergy(player.getEnergy() - 1.2f);
			if(player.isSprint())
			{
				player.switchSprint();
			}
		}
		
		if(player.isSprint())
		{
			player.setEnergy(player.getEnergy() - 2f);
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
    		alarmMusic.loop(1,Ressources.Volume * 2.333f);
    	}
    	if(!alarm && !gameMusic.playing())
    	{
    		alarmMusic.stop();
    		gameMusic.loop(1,Ressources.Volume * 1.333f);
    	}
		//Miracle of Sound Ende
    	
    	
		//Spielende
		if(player.getHealth()<=0)
		{
			Fail fail = (Fail) game.getState(5);
			fail.setLast(this.getID());
			gameMusic.stop();
			alarmMusic.stop();
			toReset = true;
			game.enterState(5, new FadeOutTransition(), new FadeInTransition());
		}
		
		if(player.checkCollision(exit) && exit.isOpen())
		{
			Success success = (Success) game.getState(4);
			success.setLast(this.getID());
			
			SavedState st = new SavedState("/Schattenwanderer_State");
			try 
			{
				st.load();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			if(st.getNumber("lastSuccessful") < this.getID() - levelOffset)
			{
				st.setNumber("lastSuccessful", this.getID() - levelOffset);
			}
			
			try
			{
				st.save();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			gameMusic.stop();
			alarmMusic.stop();
			toReset = true;
			if(levelNumber==levelCount)
			{
				game.enterState(6, new FadeOutTransition(), new FadeInTransition());
			}
			else
			{
				game.enterState(4, new FadeOutTransition(), new FadeInTransition());
			}
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
			else if(w.getNoise()>w.getNoiseDefault()*alarmMultiplier*alarmMultiplier)
			{
				w.addNoise(-calmDown);
			}
		}
			
		//Updates
		//player.update(delta);
		
		for(Watch w : watches)
		{
			w.move(solids,exit);
			w.update(delta);
			ArrayList<SolidObject> toPass = new ArrayList<SolidObject>();
			Circle vicinity = new Circle(w.getX() + w.colX/2, w.getY() + w.colY/2, w.getSightRadius());
			for(SolidObject s: solids)
			{
				if(s.checkCollision(vicinity))
				{
					toPass.add(s);
				}
			}
				w.updateSight(toPass);
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
		input.clearControlPressedRecord();
		input.clearKeyPressedRecord();
		input.clearMousePressedRecord();
	}
	
	/**Checks if the alarm should be activated, ticks it down if it is, deactivates it if alarmTime has reached 0 and 
	 * deals damage to the player if he stands in front of a watch*/
	private void checkAlarm()
	{
		boolean inLaserNow = false;
		for(Laser l: laser)
		{
			if(l.isOn())
			{
				if(player.checkCollision(l.getBeam())&& !player.isStealth())
			    {
			    	activateAlarm();
			    	inLaserNow = true;
			    	if(!inLaser)
			    	{
			    		for(Watch w: watches)
			    		{
			    			w.calculatePath(new Vector2f(Math.round(player.x/8),Math.round(player.y/8)), solids, exit);
			    		}
			    	}
			    }
			}
		}
		
		inLaser = inLaserNow;
		
		boolean inSight = false;
		
		for(Watch w : watches)
		{
			if((player.checkCollision(w.getSightCone()) || player.checkCollision(w.getHearCircle()))  && !player.isStealth())
		    {
				gracePeriod++;
				if((player.checkCollision(w.getSightCone()))&& !player.isStealth())
				{
					inSight = true;
				}
				
				if(gracePeriod>=10 || alarm)
	    		{
					activateAlarm();
					w.setPLayerLastKnown(new Vector2f(player.getX(),player.getY()));
	    		}
		    }
			
			if(target.checkCollision(w.getSightCone()) && !target.isDiscovered())
			{
				activateAlarm();
				target.setDiscovered(true);
			}
		}
		
		if(inSight && alarm)
		{
			player.setHealth(player.getHealth() - 3);
		}
		
		if(!inSight)
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
		
	}
	
	/**Activates the alarm
	 * 
	 * @param alarmTime How long the alarm should last
	 */
	private void activateAlarm()
	{
		if(!alarm)
    	{
			alarm = true;
	    	onAlarmActivate();
	    }
	    this.alarmTime = alarmTimeDefault;
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
			exitSound.play(1,Ressources.Volume * 1.666f);
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
		return levelOffset + levelNumber;
	}
}

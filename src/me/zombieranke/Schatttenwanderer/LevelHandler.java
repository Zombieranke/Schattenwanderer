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
	protected Watch watch;
	protected boolean alarm = false;
	protected int alarmTime;
	protected static int alarmTimeDefault = 200;
	protected int levelCount;
	protected ArrayList<SolidObject> solids = new ArrayList<SolidObject>();
	protected ArrayList<Laser> laser = new ArrayList<Laser>();
	protected ArrayList<Lever> lever = new ArrayList<Lever>();
	private boolean debug = false;
	private int state = 1;
	private int isMoving = 0;
	private int EnemyisMoving = 0;
	
	public void initializeObjects(GameContainer container)
	{
		for(Laser l : laser){
			l.init(container.getWidth(),container.getHeight(),solids);
		}
		for(Lever l : lever){
			l.init();
		}
		watch.updateSight(solids);
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
		watch.render(g);
		if(debug)
		{
			for(SolidObject w : solids)
			{
				w.renderCollisionArea(g);
			}
			player.renderCollisionArea(g);
			watch.renderCollisionArea(g);
			for(Laser l: laser)
			{
				l.renderCollisionArea(g);
			}
			for(Lever l: lever)
			{
				l.renderCollisionArea(g);
			}
		}
		if(alarm)
		{
			g.setColor(Color.red);
			g.drawString("ALARM", container.getWidth()/2, 50);
		}
		
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
				if(player.checkCollision(l.getBeam()))
			    {
			    	alarm = true;
			    	alarmTime = alarmTimeDefault;
			    }
			}
		}
		
		if(player.checkCollision(watch.getSight()))
	    {
	    	alarm = true;
	    	alarmTime = alarmTimeDefault;
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
		}
		
		if(input.isKeyPressed(Input.KEY_F)){
			for(Lever l : lever){
				if(player.checkCollision(l)){
					l.flipLever();
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_LEFT))
		{
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
		
	    //Debug Watch Movement
		if(input.isKeyDown(Input.KEY_A))
		{
			EnemyisMoving = 1;
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
			EnemyisMoving = 1;
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
			EnemyisMoving = 1;
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
			EnemyisMoving = 1;
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
				input.isKeyDown(Input.KEY_D) == false && input.isKeyDown(Input.KEY_W)  == false && EnemyisMoving == 1)
		{
			EnemyisMoving = 0;
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
		
		if (EnemyisMoving == 0)
		{
			watch.animation.setCurrentFrame(2);
			watch.animation.stop();
		}
		
		else
		{
			watch.animation.start();
		}
		
		player.update(delta);
		watch.update(delta);
		watch.updateSight(solids);
	}

	@Override
	public int getID()
	{
		return levelOffset + levelCount;
	}

}

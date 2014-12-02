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
	protected ArrayList<SolidObject> walls = new ArrayList<SolidObject>();
	private boolean debug = false;


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.setColor(Color.black);
		g.drawString("Bewegung: Pfeiltasten\nWachenbewegung: WASD\nWachendrehung: Q,E", 40, 40);
		for(SolidObject w : walls)
		{
			w.render(g);
		}
		player.render(g);
		watch.render(g);
		if(debug)
		{
			for(SolidObject w : walls)
			{
				w.renderCollisionArea(g);
			}
			player.renderCollisionArea(g);
			watch.renderCollisionArea(g);
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
		
		if(input.isKeyDown(Input.KEY_LEFT))
		{
			for(int i = -2;i<0;i++)
			{
				if(player.canMove(i, 0, walls))
				{
					player.move(i, 0);
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			for(int i = 2;i>0;i--)
			{
				if(player.canMove(i, 0, walls))
				{
					player.move(i, 0);
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_UP))
		{
			for(int i = -2;i<0;i++)
			{
				if(player.canMove(0, i, walls))
				{
					player.move(0, i);
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_DOWN))
		{
			for(int i = 2;i>0;i--)
			{
				if(player.canMove(0, i, walls))
				{
					player.move(0, i);
					break;
				}
		    }
		}
		
	    //Debug Watch Movement
		if(input.isKeyDown(Input.KEY_A))
		{
			for(int i = -2;i<0;i++)
			{
				if(watch.canMove(i, 0, walls))
				{
					watch.move(i, 0);
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_D))
		{
			for(int i = 2;i>0;i--)
			{
				if(watch.canMove(i, 0, walls))
				{
					watch.move(i, 0);
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_W))
		{
			for(int i = -2;i<0;i++)
			{
				if(watch.canMove(0, i, walls))
				{
					watch.move(0, i);
					break;
				}
		    }
		}
		
		if(input.isKeyDown(Input.KEY_S))
		{
			for(int i = 2;i>0;i--)
			{
				if(watch.canMove(0, i, walls))
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
		
		player.update(delta);
		watch.update(delta);
		watch.updateSight(walls);
	}

	@Override
	public int getID()
	{
		return levelOffset + levelCount;
	}

}

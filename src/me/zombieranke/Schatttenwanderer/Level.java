package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Level extends BasicGameState
{
	
	protected static final int levelOffset = 5;
	protected Player player;
	protected Watch watch;
	protected boolean alarm = false;
	protected int alarmTime;
	protected static int alarmTimeDefault = 200;
	protected int levelCount;
	protected ArrayList<SolidObject> Walls = new ArrayList<SolidObject>();
	private boolean debug = false;




	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.setColor(Color.black);
		g.drawString("Bewegung: Pfeiltasten\nWachenbewegung: WASD\nWachendrehung: Q,E", 100, 100);
		for(SolidObject w : Walls)
		{
			w.render(g);
		}
		player.render(g);
		watch.render(g);
		if(debug){
			for(SolidObject w : Walls)
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
    	if(input.isKeyPressed(Input.KEY_F3)){
    		debug = !debug;
    	}   	
    	
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			game.enterState(1);
		}
		
		if(input.isKeyDown(Input.KEY_LEFT))
		{
		    player.setX(player.getX()-2);
			player.update(delta);
			for(SolidObject w: Walls)
			{
			    if(player.checkCollision(w))
			    {
			    	player.setX(player.getX()+2);
			    }
			}
		    
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
	        player.setX(player.getX()+2);
			player.update(delta);
			for(SolidObject w: Walls)
			{
		        if(player.checkCollision(w))
			    {
			    	player.setX(player.getX()-2);
			    }
			}

		}
		
		if(input.isKeyDown(Input.KEY_UP))
		{
			player.setY(player.getY()-2);
			player.update(delta);
			for(SolidObject w: Walls)
			{
		        if(player.checkCollision(w))
			    {
			    	player.setY(player.getY()+2);
			    }
			}
		}
		
		if(input.isKeyDown(Input.KEY_DOWN))
		{
		    player.setY(player.getY()+2);
			player.update(delta);
			for(SolidObject w : Walls)
			{
		        if(player.checkCollision(w))
			    {
		        	//System.out.println("Detected Collision with Wall at X: "+w.getX()+"Y: "+w.getY());
			    	player.setY(player.getY()-2);
			    }
			}

		}
		
		
	    //Debug Watch Movement
		if(input.isKeyDown(Input.KEY_A))
		{
		    watch.setX(watch.getX()-2);
			watch.update(delta);
			for(SolidObject w: Walls)
			{
		        if(watch.checkCollision(w))
			    {
			    	watch.setX(watch.getX()+2);
			    	watch.updateSight(delta,Walls);
			    }
			}
		}
		
		if(input.isKeyDown(Input.KEY_D))
		{
	        watch.setX(watch.getX()+2);
			watch.update(delta);
			for(SolidObject w: Walls)
			{
		        if(watch.checkCollision(w))
			    {
			    	watch.setX(watch.getX()-2);
			    	watch.updateSight(delta,Walls);
			    }
			}
		}
		
		if(input.isKeyDown(Input.KEY_W))
		{
			watch.setY(watch.getY()-2);
			watch.update(delta);
			for(SolidObject w: Walls)
			{
		        if(watch.checkCollision(w))
			    {
			    	watch.setY(watch.getY()+2);
			    	watch.updateSight(delta,Walls);
			    }
			}
		}
		
		if(input.isKeyDown(Input.KEY_S))
		{
		    watch.setY(watch.getY()+2);
			watch.update(delta);
			for(SolidObject w: Walls)
			{
		        if(watch.checkCollision(w))
			    {
			    	watch.setY(watch.getY()-2);
			    	watch.updateSight(delta,Walls);
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
	}

	@Override
	public int getID()
	{
		return levelOffset + levelCount;
	}

}

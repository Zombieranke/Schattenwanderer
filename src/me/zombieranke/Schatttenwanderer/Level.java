package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Level extends BasicGameState
{
	
	private int ID = 2;
	private Wall wall;
	private Player p;
	private Watch w;
	private boolean alarm = false;
	private int alarmTime;
	private static int alarmTimeDefault = 200;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		wall = new Wall(120,200, new Image("res/Wall_Type_1.jpg"),32,32);
		p = new Player (100,200);
		w = new Watch(300,300,new Image("res/Watch_Placeholder.png"),5,5, wall);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		g.setColor(Color.black);
		g.drawString("Hier wird ein Spiel entstehen", 100, 100);
		g.drawString("Press 1 to enter Menu", 100, 115);
		wall.render(g);
		p.render(g);
		w.render(g);
		if(alarm)
		{
			g.setColor(Color.red);
			g.drawString("ALARM", container.getWidth()/2, 50);
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		w.update(delta);
		w.updateSight(delta);
		Input input = container.getInput();
		
		//Ganzes Alarmskrimskrams Anfang
		
		if(p.checkCollision(w.getSight()))
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
		
		if(input.isKeyPressed(Input.KEY_1))
		{
			game.enterState(1);
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			container.exit();
		}
		
		if(input.isKeyDown(Input.KEY_LEFT))
		{
		    p.setX(p.getX()-2);
			p.update(delta);
		    if(p.checkCollision(wall))
		    {
		    	p.setX(p.getX()+2);
		    }
		    
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
	        p.setX(p.getX()+2);
			p.update(delta);
	        if(p.checkCollision(wall))
		    {
		    	p.setX(p.getX()-2);
		    }

		}
		
		if(input.isKeyDown(Input.KEY_UP))
		{
			p.setY(p.getY()-2);
			p.update(delta);
			if(p.checkCollision(wall))
		    {
		    	p.setY(p.getY()+2);
		    }

		}
		
		if(input.isKeyDown(Input.KEY_DOWN))
		{
		    p.setY(p.getY()+2);
			p.update(delta);
		    if(p.checkCollision(wall))
		    {
		    	p.setY(p.getY()-2);
		    	//System.out.println("Collisions detected");
		    }

		}
		
		
	    //Debug Watch Movement
		if(input.isKeyDown(Input.KEY_A))
		{
		    w.setX(w.getX()-2);
			w.update(delta);
		    if(w.checkCollision(wall))
		    {
		    	w.setX(w.getX()+2);
		    	w.updateSight(delta);
		    }
		}
		
		if(input.isKeyDown(Input.KEY_D))
		{
	        w.setX(w.getX()+2);
			w.update(delta);
	        if(w.checkCollision(wall))
		    {
		    	w.setX(w.getX()-2);
		    	w.updateSight(delta);
		    }
		}
		
		if(input.isKeyDown(Input.KEY_W))
		{
			w.setY(w.getY()-2);
			w.update(delta);
			if(w.checkCollision(wall))
		    {
		    	w.setY(w.getY()+2);
		    	w.updateSight(delta);
		    }
		}
		
		if(input.isKeyDown(Input.KEY_S))
		{
		    w.setY(w.getY()+2);
			w.update(delta);
		    if(w.checkCollision(wall))
		    {
		    	w.setY(w.getY()-2);
		    	w.updateSight(delta);
		    } 
		}
		
		if(input.isKeyDown(Input.KEY_Q))
		{
			w.setDirection(w.getDirection()-1);
		}
		
		if(input.isKeyDown(Input.KEY_E))
		{
			w.setDirection(w.getDirection()+1);
		}
	}

	@Override
	public int getID()
	{
		return ID;
	}

}

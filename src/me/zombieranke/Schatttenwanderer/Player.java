package me.zombieranke.Schatttenwanderer;




import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Player extends MovableObject
{
	/*Basic Implementation. Needs a lot of work. */
	
	private static final int DEFAULT_COL_X = 20;
	private static final int DEFAULT_COL_Y = 20;
	private boolean stealth = false;
	private boolean sprint = false;
	
	public Player(int x, int y, Image img)
	{
		super(x, y, img,DEFAULT_COL_X,DEFAULT_COL_Y);
	}
	
	public Player(int x, int y)
	{
		super(x, y,DEFAULT_COL_X,DEFAULT_COL_Y);
	}
	
	public Player(int x, int y, Image img,int colX,int colY)
	{
		super(x, y, img,colX,colY);
	}
	public Player(int x, int y, int colX, int colY)
	{
		super(x, y,colX,colY);
	}
	
	public Player(int x, int y, Animation animation, int colX, int colY)
	{
		super(x, y, animation, colX, colY);
	}
	
	
	public void render(Graphics g)
	{
		
		if(stealth)
		{
			animation.draw(x, y, new Color(1f,1f,1f,0.3f));
		}
		else if(sprint)
		{
			for(int i=0;i<5;i++)
			{
				animation.setDuration(i,50);
			}
			animation.draw(x, y, new Color(1f,0.6f,0.2f));
		}
		else
		{
			if(animation.getDuration(0)==50)
			{
				for(int i=0;i<5;i++)
				{
					animation.setDuration(i,100);
				}
			}
			animation.draw(x,y);
		}
		
	}
	
	public boolean isStealth()
	{
		return stealth;
	}
	
	public void switchStealth()
	{
		stealth = !stealth;
	}
	
	public void setStealth(boolean stealth)
	{
		this.stealth = stealth;
	}
	
	public boolean isSprint()
	{
		return sprint;
	}
	
	public void switchSprint()
	{
		sprint = !sprint;
	}
	
	public void setSprint(boolean sprint)
	{
		this.sprint = sprint;
	}
	
}
	


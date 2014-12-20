package me.zombieranke.Schatttenwanderer;




import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Player extends MovableObject
{
	
	/**The health the player starts with by default*/
	protected static final float playerHealthDefault = 300;
	/**The current health of the player(might migrate this into player actually)*/
	protected float playerHealth;
	
	/**The energy the player starts with by default*/
	protected static final float playerEnergyDefault = 300;
	/**The current energy of the player(might migrate this into player actually)*/
	protected float playerEnergy;
	
	protected static int speedSprint = 4;
	protected static int speedWalk = 2;
	protected static int speedSneak = 1;
	
	private static final float DEFAULT_COL_X = 20;
	private static final float DEFAULT_COL_Y = 20;
	private boolean stealth = false;
	private boolean sprint = false;
	
	public Player(float x, float y, Image img)
	{
		super(x, y, img,DEFAULT_COL_X,DEFAULT_COL_Y);
	}
	
	public Player(float x, float y)
	{
		super(x, y,DEFAULT_COL_X,DEFAULT_COL_Y);
	}
	
	public Player(float x, float y, Image img,float colX,float colY)
	{
		super(x, y, img,colX,colY);
	}
	public Player(float x, float y, float colX, float colY)
	{
		super(x, y,colX,colY);
	}
	
	public Player(float x, float y, Animation animation, float colX, float colY)
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
	
	public float getHealthDefault()
	{
		return playerHealthDefault;
	}
	
	public void setHealth()
	{
		playerHealth = playerHealthDefault;
	}
	
	public void setHealth(float x)
	{
		playerHealth = x;
	}
	
	public float getHealth()
	{
		return playerHealth;
	}
	
	public float getEnergyDefault()
	{
		return playerEnergyDefault;
	}
	
	public void setEnergy()
	{
		playerEnergy = playerEnergyDefault;
	}
	
	public void setEnergy(float x)
	{
		playerEnergy = x;
	}
	
	public float getEnergy()
	{
		return playerEnergy;
	}
	
	public int getSpeedSprint()
	{
		return speedSprint;
	}
	
	public int getSpeedWalk()
	{
		return speedWalk;
	}
	
	public int getSpeedSneak()
	{
		return speedSneak;
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
	


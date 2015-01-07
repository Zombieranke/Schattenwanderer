package me.zombieranke.Schatttenwanderer;

import me.zombieranke.utils.Direction;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

/**Class representing the exit the player can escape through*/
public class Exit extends SolidObject
{
	/**Indicates whether the exit is open or not*/
	private boolean open;
	
	private Direction orient;
	
	/**Creates an exit at the position x,y
	 * 
	 * @param x The x coordinate of the exit
	 * @param y The y coordinate of the exit
	 */
	public Exit(float x,float y)
	{
		super(x,y);
		open = false;
	}
	
	/**Creates an exit at the position x,y with an animation and a collision box
	 * 
	 * @param x The x coordinate of the exit
	 * @param y The y coordinate of the exit
	 * @param animation The animation to play when opening and closing the exit
	 * @param colX The vertical size of the collision box
	 * @param colY The horizontal size of the collision box
	 */
	public Exit(float x, float y, Animation animation,float colX, float colY, Direction orient)
	{
		super(x,y,animation,colX,colY);
		open = false;
		this.orient = orient;
	}
	
	@Override
	public void render(Graphics g)
	{		
		switch(orient)
		{
		case NORTH:
			animation.getCurrentFrame().setRotation(0);
			break;
			
		case EAST:
			animation.getCurrentFrame().setRotation(90);
			break;
			
		case SOUTH:
			animation.getCurrentFrame().setRotation(180);
			break;
		
		case WEST:
			animation.getCurrentFrame().setRotation(0);
			break;
		
		}
		animation.draw(x, y);
	}
	
	/**Check if the exit is open
	 * 
	 * @return true if the exit is open
	 */
	public boolean isOpen()
	{
		return open;
	}
	
	/**Sets the exits open state
	 * 
	 * @param open The state to set it on
	 */
	public void setOpen(boolean open)
	{
		this.open = open;
	}
}

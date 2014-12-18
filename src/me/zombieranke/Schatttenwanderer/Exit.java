package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

/**Class representing the exit the player can escape through*/
public class Exit extends SolidObject
{
	/**Indicates whether the exit is open or not*/
	boolean open;
	
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
	 * @param colYThe horizontal size of the collision box
	 */
	public Exit(float x, float y, Animation animation,float colX, float colY)
	{
		super(x,y,animation,colX,colY);
		open = false;
	}
	
	@Override
	public void render(Graphics g)
	{
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

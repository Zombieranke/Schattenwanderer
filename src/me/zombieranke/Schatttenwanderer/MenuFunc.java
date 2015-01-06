package me.zombieranke.Schatttenwanderer;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;

/**Basic menu button*/
public class MenuFunc
{
	/**The area that can be mouseovered*/
	private MouseOverArea area; // Create a Mouseover area
	
	private int stateToEnter;
	
	private boolean locked = false;
	
	// Constructors
	public MenuFunc(MouseOverArea area, Image image, int stateToEnter)
	{
		this.area = area;
		this.stateToEnter = stateToEnter;
		area.setMouseOverImage(image);
	}
	
	public void draw(Graphics g, GameContainer container)
	{
		area.render(container, g);
	}
		
	public boolean isMouseOver()
	{
		return area.isMouseOver();
	}
	
	public boolean isClickable()
	{
		return isMouseOver() && !locked;
	}
	
	public int getState()
	{
		return stateToEnter;
	}
	
	public void setLock(boolean lock)
	{
		if(lock)
		{
			//area.setNormalImage(Ressources.);
		}
		locked = lock;
	}
	
	public boolean isLocked()
	{
		return locked;
	}

}

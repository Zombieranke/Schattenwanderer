package me.zombieranke.Schatttenwanderer;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;

/**Basic menu button*/
public class MenuFunc
{
	/**The area that can be mouseovered*/
	private MouseOverArea area; // Create a Mouseover area
	
	private int stateToEnter;
	
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
	
	public int getState()
	{
		return stateToEnter;
	}

}

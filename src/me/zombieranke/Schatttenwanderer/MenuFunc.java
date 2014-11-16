package me.zombieranke.Schatttenwanderer;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;

public class MenuFunc
{

	private MouseOverArea area; // Create a Mouseover area
	private Image image; // Create an image to switch the MouseOverArea
	
	// Constructors
	public MenuFunc(MouseOverArea area, Image image)
	{
		this.area = area;
		this.image = image;
	}
	
	public void draw(Graphics g, GameContainer container)
	{
		area.render(container, g);
	}
	
	public void update (int delta)
	{
		area.setMouseOverImage(image);
	}
	
	public boolean isMouseOver()
	{
		return area.isMouseOver();
	}

}

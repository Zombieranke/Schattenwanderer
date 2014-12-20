package me.zombieranke.Schatttenwanderer;

import me.zombieranke.utils.Direction;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class Lever extends GameObject {
	
	/**The corresponding laser controlled by this lever*/
	private Laser laser;
	
	/**The image to be render when this lver is flipped*/
	private Image altImg;
	
	/**Indicates if this lever is flipped*/
	private boolean flipped = false;
	
	/**The direction this lever is facing*/
	private Direction facing;

	/**Creates a lever
	 * 
	 * @param x The x coordinate of the lever
	 * @param y The y coordinate of the lever
	 * @param img The image when the lever is not flipped
	 * @param altImg The image when the lever is flipped
	 * @param colX The vertical size of the collision box
	 * @param colY The horizontal size of the collision box
	 * @param facing The direction the lever is facing
	 * @param laser
	 */
	public Lever(float x, float y, Image img, Image altImg, float colX, float colY, Direction facing, Laser laser) {
		super(x, y, img, colX, colY);
		this.laser  = laser;
		this.altImg = altImg;
		this.facing = facing;
	}
	
	/**Creates a lever
	 * 
	 * @param x The x coordinate of the lever
	 * @param y The y coordinate of the lever
	 * @param img The image when the lever is not flipped
	 * @param altImg The image when the lever is flipped
	 * @param collisionArea The collision area of the lver
	 * @param facing The direction the lever is facing
	 * @param laser The corresponding laser
	 */
	public Lever(float x, float y, Image img, Image altImg, Shape collisionArea, Direction facing, Laser laser) {
		super(x, y, img, collisionArea);
		this.laser  = laser;
		this.altImg = altImg;
		this.facing = facing;
	}

	/**Creates a lever
	 * 
	 * @param x The x coordinate of the lever
	 * @param y The y coordinate of the lever
	 * @param img The image when the lever is not flipped
	 * @param altImg The image when the lever is flipped
	 * @param facing The direction the lever is facing
	 * @param laser The corresponding laser
	 */
	public Lever(float x, float y, Image img, Image altImg, Direction facing, Laser laser) {
		super(x, y, img);
		this.laser  = laser;
		this.altImg = altImg;
		this.facing = facing;
	}

	@Override
	public void render(Graphics g) {
		if(flipped)
		{
			altImg.draw(x,y);
		}
		else
		{
			img.draw(x,y);
		}
	}
	
	/**Flip the lever and toggle the according laser*/
	public void flipLever(){
		laser.toggle();
		flipped = !flipped;
	}
	
	public boolean isFlipped()
	{
		return flipped;
	}
	
	/**Initialize the lever by rotating it according to facing*/
	public void init(){
		
		switch(facing)
		{
			case NORTH:
				img.setRotation(270);
				altImg.setRotation(270);
				break;
				
			case WEST:
				img.setRotation(180);
				altImg.setRotation(180);
				break;
			
			case SOUTH:
				img.setRotation(90);
				altImg.setRotation(90);
				break;
			
			default:
				break;
		}
	}
	
	

}

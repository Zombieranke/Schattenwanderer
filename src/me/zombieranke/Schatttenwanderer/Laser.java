package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import me.zombieranke.utils.Direction;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.GeomUtil;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.GeomUtil.HitResult;
import org.newdawn.slick.geom.Vector2f;

public class Laser extends SolidObject
{
	/**A line representing the laser beam*/
	private Line laserBeam;
	
	/**The direction the laser is facing*/
	private Direction facing;
	
	/**Indicates whether the laser is turned on*/
	private boolean on = true;
	
	/**The class number for a laser cluster*/
	private int clusterNumber = 0;

	/**Creates a Laser at x,y
	 * 
	 * @param x The x coordinate of the laser
	 * @param y The y coordinate of the laser
	 */
	public Laser(float x, float y) {
		super(x, y);
	}

	/**Creates a laser at x,y with an image, a collision box and a direction it is facing
	 * 
	 * @param x The x coordinate of the laser
	 * @param y The y coordinate of the laser
	 * @param img The image of the laser
	 * @param colX The vertical size of the collision box
	 * @param colY The horizontal size of the collision box
	 * @param facing The direction the laser is facing 
	 */
	public Laser(float x, float y, Image img, float colX, float colY, Direction facing) {
		super(x, y, img, colX, colY);
		this.facing = facing;
	}

	/**Creates a laser at x,y with an image, a collision box and a direction it is facing
	 * 
	 * @param x The x coordinate of the laser
	 * @param y The y coordinate of the laser
	 * @param img The image of the laser
	 * @param colX The vertical size of the collision box
	 * @param colY The horizontal size of the collision box
	 * @param facing The direction the laser is facing 
	 * @param clusterNumber Laser group for controlling certain laser clusters
	 */
	public Laser(float x, float y, Image img, float colX, float colY, Direction facing,int clusterNumber) {
		super(x, y, img, colX, colY);
		this.facing = facing;
		this.clusterNumber = clusterNumber;
	}
	
	/**Creates a laser at x,y with an image, a collision box and a direction it is facing
	 * 
	 * @param x The x coordinate of the laser
	 * @param y The y coordinate of the laser
	 * @param img The image of the laser
	 * @param colX The vertical size of the collision box
	 * @param colY The horizontal size of the collision box
	 * @param facing The direction the laser is facing 
	 * @param active whether the Laser is on or out
	 */
	public Laser(float x, float y, Image img, float colX, float colY, Direction facing, boolean active) {
		super(x, y, img, colX, colY);
		this.facing = facing;
		on = active;
	}
	
	/**Creates a laser at x,y with an image, a collision box and a direction it is facing
	 * 
	 * @param x The x coordinate of the laser
	 * @param y The y coordinate of the laser
	 * @param img The image of the laser
	 * @param colX The vertical size of the collision box
	 * @param colY The horizontal size of the collision box
	 * @param facing The direction the laser is facing 
	 * @param active whether the Laser is on or out
	 * @param clusterNumber Laser group for controlling certain laser clusters
	 */
	public Laser(float x, float y, Image img, float colX, float colY, Direction facing, boolean active, int clusterNumber) {
		super(x, y, img, colX, colY);
		this.facing = facing;
		on = active;
		this.clusterNumber = clusterNumber;
	}
	/**Creates a laser at x,y with an image, a collision box and a direction it is facing
	 * 
	 * @param x The x coordinate of the laser
	 * @param y The y coordinate of the laser
	 * @param img The image of the laser
	 * @param collisionArea The collision area
	 * @param facing The direction the laser is facing
	 */
	public Laser(float x, float y, Image img, Shape collisionArea, Direction facing) {
		super(x, y, img, collisionArea);
		this.facing = facing;
	}

	/**Creates a laser at x,y with an image and a direction it is facing
	 * 
	 * @param x The x coordinate of the laser
	 * @param y The y coordinate of the laser
	 * @param img The image of the laser
	 * @param facing The direction the laser is facing
	 */
	public Laser(float x, float y, Image img, Direction facing) {
		super(x, y, img);
		this.facing = facing;
	}
	
	/**Creates a laser at x,y with a collision box and a direction it is facing
	 * 
	 * @param x The x coordinate of the laser
	 * @param y The y coordinate of the laser
	 * @param colX The vertical size of the collision box
	 * @param colY The horizontal size of the collision box
	 * @param facing The direction the laser is facing
	 */
	public Laser(float x, float y, float colX, float colY, Direction facing) {
		super(x, y, colX, colY);
		this.facing = facing;
	}

	/**Creates a laser at x,y with a collision box and a direction it is facing
	 * 
	 * @param x The x coordinate of the laser
	 * @param y The y coordinate of the laser
	 * @param collisionArea The collision area
	 * @param facing The direction the laser is facing
	 */
	public Laser(float x, float y, Shape collisionArea, Direction facing) {
		super(x, y, collisionArea);
		this.facing = facing;
	}

	@Override
	public void render(Graphics g) {
		if(on)
		{
			Color boxCol = new Color(Color.red);
			boxCol.a = 0.5f;
			g.setColor(boxCol);
			g.draw(laserBeam);
		}
		img.draw(x,y);
	}

	/**Initialize the laser by turning it in the right direction and creating the laser beam
	 * 
	 * @param boundX The maximum x coordinate of the laser end
	 * @param boundY The maximum y coordinate of the laser end
	 * @param solids A list of objects that the laser cannot pass through
	 */
	public void init(int boundX, int boundY, ArrayList<SolidObject> solids) {
		Vector2f start = new Vector2f(x+colX/2,y+colY/2);
		Vector2f end = new Vector2f(x+colX/2,y+colY/2);
		
		switch(facing)
		{
			case EAST:
				end.x = boundX;
				break;
				
			case NORTH:
				end.y = 0;
				img.setRotation(270);
				break;
				
			case WEST:
				end.x = 0;
				img.setRotation(180);
				break;
			
			case SOUTH:
				end.y = boundY;
				img.setRotation(90);
				break;
			
			default:
				break;
		}
		laserBeam = new Line(start,end);
		GeomUtil util = new GeomUtil();
		for(SolidObject s : solids)
		{
			Shape colBox = s.getCollisionArea();
			if(laserBeam.intersects(colBox))
			{
				HitResult hr = util.intersect(colBox, laserBeam);
				laserBeam.set(start,hr.pt);
			}
		}
		
	}

	/**Gets a line representing the laser beam
	 * 
	 * @return The laser beam
	 * @see Line
	 */
	public Line getBeam() {
		return laserBeam;
	}

	/**Sets the laser beam to this line(unused)
	 * 
	 * @param laserBeam The Line to set the laser beam to
	 * @see Line
	 */
	public void setBeam(Line laserBeam) {
		this.laserBeam = laserBeam;
	}

	/**Gets the direction the laser is facing
	 * 
	 * @return the direction the laser is facing
	 */
	public Direction getFacing() {
		return facing;
	}

	/**Sets the direction the laser should face(unused)
	 * 
	 * @param facing the facing to set
	 */
	public void setFacing(Direction facing) {
		this.facing = facing;
		switch(facing)
		{
			case EAST:
				img.setRotation(0);
				break;
				
			case NORTH:
				img.setRotation(270);
				break;
				
			case WEST:
				img.setRotation(180);
				break;
			
			case SOUTH:
				img.setRotation(90);
				break;
			
			default:
				break;
		}
	}
	
	/**Indicates if this laser is on
	 * 
	 * @return true if the laser is on
	 */
	public boolean isOn(){
		return on;
	}
	
	/**Toggles the laser on/off*/
	public void toggle(){
		on = !on;
	}
	
	public int getClusterNumber()
	{
		return clusterNumber;
	}
	
}

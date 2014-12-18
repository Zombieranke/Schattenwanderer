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

	
	public Laser(float x, float y) {
		super(x, y);
	}

	public Laser(float x, float y, Image img, float colX, float colY, Direction facing) {
		super(x, y, img, colX, colY);
		this.facing = facing;
	}

	public Laser(float x, float y, Image img, Shape collisionArea, Direction facing) {
		super(x, y, img, collisionArea);
		this.facing = facing;
	}

	public Laser(float x, float y, Image img, Direction facing) {
		super(x, y, img);
		this.facing = facing;
	}

	public Laser(float x, float y, float colX, float colY, Direction facing) {
		super(x, y, colX, colY);
		this.facing = facing;
	}

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
	 * @return the laserBeam
	 * @see Line
	 */
	public Line getBeam() {
		return laserBeam;
	}

	/**Sets the laser beam to this line(unused)
	 * 
	 * @param laserBeam the laserBeam to set
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

	/**Sets the direction the laser should face(not fully implemented)
	 * 
	 * @param facing the facing to set
	 */
	public void setFacing(Direction facing) {
		this.facing = facing;
	}
	
	public boolean isOn(){
		return on;
	}
	
	public void toggle(){
		on = !on;
	}
	
}

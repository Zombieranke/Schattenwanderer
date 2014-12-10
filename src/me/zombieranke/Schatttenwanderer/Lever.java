package me.zombieranke.Schatttenwanderer;

import me.zombieranke.utils.Direction;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class Lever extends GameObject {
	
	private Laser laser;
	private Image altImg;
	private boolean flipped = false;
	private Direction facing;

	public Lever(float x, float y, Animation animation, float colX, float colY, Direction facing, Laser laser) {
		super(x, y, animation, colX, colY);
		this.laser  = laser;
		this.facing = facing;
	}

	public Lever(float x, float y, Image img, Image altImg, float colX, float colY, Direction facing, Laser laser) {
		super(x, y, img, colX, colY);
		this.laser  = laser;
		this.altImg = altImg;
		this.facing = facing;
	}

	public Lever(float x, float y, Image img, Image altImg, Shape collisionArea, Direction facing, Laser laser) {
		super(x, y, img, collisionArea);
		this.laser  = laser;
		this.altImg = altImg;
		this.facing = facing;
	}

	public Lever(float x, float y, Image img, Image altImg, Direction facing, Laser laser) {
		super(x, y, img);
		this.laser  = laser;
		this.altImg = altImg;
		this.facing = facing;
	}

	public Lever(float x, float y, float colX, float colY, Direction facing, Laser laser) {
		super(x, y, colX, colY);
		this.laser  = laser;
		this.facing = facing;
	}

	public Lever(float x, float y, Shape collisionArea,Direction facing,  Laser laser) {
		super(x, y, collisionArea);
		this.laser  = laser;
		this.facing = facing;
	}

	public Lever(float x, float y, Direction facing, Laser laser) {
		super(x, y);
		this.laser  = laser;
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
	
	public void flipLever(){
		laser.toggle();
		flipped = !flipped;
	}
	
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

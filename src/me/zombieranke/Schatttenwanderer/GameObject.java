package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject {
	
	public abstract void render(Graphics g);
	public void update(int delta){}

	protected int x;
	protected int y;
	protected Image img;
	protected boolean solid = false;	//für später, damit die Collisiondetections funktionieren
	protected Shape collisionArea = new Rectangle(0,0,1,1);
	
	public GameObject(int x, int y, Image img, Shape collisionArea){
		this.x = x;
		this.y = y;
		this.img = img;
		this.collisionArea = collisionArea;
	}
	
	public GameObject(int x, int y, Shape collisionArea){
		this.x = x;
		this.y = y;
		this.collisionArea = collisionArea;
	}
	
	public GameObject(int x, int y, Image img, int colX, int colY){
		this.x = x;
		this.y = y;
		this.img = img;
		collisionArea = new Rectangle(x,y,colX,colY);
	}
	
	public GameObject(int x, int y, int colX, int colY){
		this.x = x;
		this.y = y;
		collisionArea = new Rectangle(x,y,colX,colY);
	}
	
	public GameObject(int x, int y, Image img){
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	public GameObject(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean checkCollision(GameObject other)
	{
		//System.out.println(collisionArea.getMinX()+","+collisionArea.getMinY()+","+collisionArea.getMaxX()+","+collisionArea.getMaxY()+","+other.getCollisionArea().getMinX()+","+other.getCollisionArea().getMinY()+","+other.getCollisionArea().getMaxX()+","+other.getCollisionArea().getMaxY());
		return collisionArea.intersects(other.getCollisionArea());
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public boolean isSolid() {
		return solid;
	}
	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	public Shape getCollisionArea() {
		return collisionArea;
	}
	public void setCollisionArea(Shape collisionArea) {
		this.collisionArea = collisionArea;
	}
}

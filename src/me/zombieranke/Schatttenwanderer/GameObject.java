package me.zombieranke.Schatttenwanderer;

import java.io.Serializable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject implements Serializable
{
	
	public abstract void render(Graphics g);
	public void update(int delta){}

	protected int x;
	protected int y;
	protected Image img;
	protected boolean solid = false;	//für später, damit die Collisiondetections funktionieren
	protected Shape collisionArea = new Rectangle(0,0,1,1);
	
	public GameObject(int x, int y, Image img, Shape collisionArea)
	{
		this(x, y, img);
		this.collisionArea = collisionArea;
	}
	
	public GameObject(int x, int y, Shape collisionArea)
	{
		this(x, y);
		this.collisionArea = collisionArea;
	}
	
	public GameObject(int x, int y, Image img, int colX, int colY)
	{
		this(x, y, img, new Rectangle(x,y,colX,colY));
	}
	
	public GameObject(int x, int y, int colX, int colY)
	{
		this(x, y, new Rectangle(x,y,colX,colY));
	}
	
	public GameObject(int x, int y, Image img)
	{
		this(x, y);
		this.img = img;
	}
	
	public GameObject(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void renderCollisionArea(Graphics g)
	{
		g.setColor(Color.green);
		g.draw(collisionArea);
	}
	
	public boolean checkCollision(GameObject other)
	{
		boolean a = collisionArea.intersects(other.getCollisionArea());
		boolean b = collisionArea.contains(other.getCollisionArea());
		boolean c = other.getCollisionArea().contains(collisionArea);
		return a||b||c;
	}
	
	public boolean checkCollision(Shape other)
	{
		boolean a = collisionArea.intersects(other);
		boolean b = collisionArea.contains(other);
		boolean c = other.contains(collisionArea);
		return a||b||c;
	}
	
	
	
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
	public boolean isSolid()
	{
		return solid;
	}
	public void setSolid(boolean solid)
	{
		this.solid = solid;
	}
	public Shape getCollisionArea()
	{
		return collisionArea;
	}
	public void setCollisionArea(Shape collisionArea)
	{
		this.collisionArea = collisionArea;
	}
}

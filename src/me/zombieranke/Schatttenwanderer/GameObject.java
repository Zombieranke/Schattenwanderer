package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject
{
	
	public abstract void render(Graphics g);
	public void update(int delta){}

	protected float x;
	protected float y;
	protected Image img;
	protected Animation animation;
	protected boolean solid = false;	//f�r sp�ter, damit die Collisiondetections funktionieren
	protected Shape collisionArea = new Rectangle(x,y,1,1);
	protected float colX = 0;
	protected float colY = 0;
	
	public GameObject(float x, float y, Image img, Shape collisionArea)
	{
		this(x, y, img);
		this.collisionArea = collisionArea;
	}
	
	public GameObject(float x, float y, Shape collisionArea)
	{
		this(x, y);
		this.collisionArea = collisionArea;
	}
	
	public GameObject(float x, float y, Image img, float colX, float colY)
	{
		this(x, y, img, new Rectangle(x,y,colX,colY));
		this.colX=colX;
		this.colY=colY;
	}
	
	public GameObject(float x, float y, float colX, float colY)
	{
		this(x, y, new Rectangle(x,y,colX,colY));
		this.colX=colX;
		this.colY=colY;
	}
	
	public GameObject(float x, float y, Image img)
	{
		this(x, y);
		this.img = img;
	}
	
	public GameObject(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public GameObject(float x, float y, Animation animation, float colX, float colY)
	{
		this(x, y, new Rectangle(x, y, colX, colY));
		this.x = x;
		this.y = y;
		this.animation = animation;
		this.colX = colX;
		this.colY = colY;
	}
	
	public GameObject(float x, float y, Animation animation, Image image, float colX, float colY)
	{
		this(x, y, new Rectangle(x, y, colX, colY));
		this.x = x;
		this.y = y;
		this.animation = animation;
		this.img = image;
		this.colX = colX;
		this.colY = colY;
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
	
	
	
	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
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

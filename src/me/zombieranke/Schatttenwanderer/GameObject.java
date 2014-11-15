package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class GameObject {
	
	public abstract void render(Graphics g);
	public void update(int delta){}

	protected int x;
	protected int y;
	protected Image img;
	protected boolean solid = false;
	
	public GameObject(int x, int y, Image img){
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	public GameObject(int x, int y){
		this.x = x;
		this.y = y;
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
}

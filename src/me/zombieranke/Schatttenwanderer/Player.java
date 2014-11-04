package me.zombieranke.Schatttenwanderer;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player extends GameObject {
	/*Basic Implementation. Needs a lot of work. */
	
	public Player(int x, int y, Image img){
		super(x, y, img);
	}
	
	public void render(Graphics g){
		img.drawCentered(x,y);
	}
}

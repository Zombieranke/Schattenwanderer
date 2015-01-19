package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class LevelMap implements TileBasedMap  {

	private int width = 160;
	private int height = 112;
	private boolean blocked[][];
	private final float SQRT_2 = (float) Math.sqrt(2);
	
	public LevelMap(ArrayList<SolidObject> solids, Exit exit)
	{
		Watch w = new Watch(0, 0, 20, 20);
		blocked = new boolean[width][height];
		for(int i = 0; i<blocked.length; i++)
		{
			for(int j = 0; j<blocked[0].length; j++)
			{
				blocked[i][j] = !w.canMoveAbsolute(i*8, j*8, solids, exit);
			}
		}
	}
	
	
	@Override
	public int getWidthInTiles() {
		return width;
	}

	@Override
	public int getHeightInTiles() {
		return height;
	}
	
	public void renderTiles(Graphics g)
	{
		for(int i= 0; i<width;i++)
		{
			for(int j = 0; j<height; j++)
			{
				if(blocked[i][j])
				{
					g.setColor(Color.red);
				}
				else
				{
					g.setColor(Color.green);
				}
				g.drawRect(i*8, j*8, 0, 0);
			}
		}
	}

	@Override
	public void pathFinderVisited(int x, int y) {
	}

	@Override
	public boolean blocked(PathFindingContext context, int tx, int ty)
	{
		return blocked[tx][ty];
	}

	@Override
	public float getCost(PathFindingContext context, int tx, int ty)
	{
		int deltaX = context.getSourceX()-tx;
		int deltaY = context.getSourceY()-ty;
		if(deltaX!=0 && deltaY!=0)
		{
			return SQRT_2; 
		}
		return 1;
		
	}

}

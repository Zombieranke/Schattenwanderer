package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class LevelMap implements TileBasedMap  {

	private int width = 160;
	private int height = 128;
	private ArrayList<SolidObject> solids;
	private Exit exit;
	private final float SQRT_2 = (float) Math.sqrt(2);
	
	LevelMap(ArrayList<SolidObject> solids, Exit exit)
	{
		this.solids = solids;
		this.exit = exit;
	}
	
	
	@Override
	public int getWidthInTiles() {
		return width;
	}

	@Override
	public int getHeightInTiles() {
		return height;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
	}

	@Override
	public boolean blocked(PathFindingContext context, int tx, int ty) {
		if(context.getMover() instanceof Watch)
		{
			Watch w = (Watch) context.getMover();
			return !w.canMoveAbsolute(tx*8, ty*8, solids, exit);
		}
		return false;
	}

	@Override
	public float getCost(PathFindingContext context, int tx, int ty) {
		int deltaX = context.getSourceX()-tx;
		int deltaY = context.getSourceY()-ty;
		if(deltaX!=0 && deltaY!=0)
		{
			return SQRT_2; 
		}
		return 1;
		
	}

}

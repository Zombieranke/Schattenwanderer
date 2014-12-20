package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class LevelMap implements TileBasedMap  {

	private int width = 320;
	private int height = 256;
	private ArrayList<SolidObject> solids;
	private Exit exit;
	
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
			return !w.canMoveAbsolute(tx*4, ty*4, solids, exit);
		}
		return false;
	}

	@Override
	public float getCost(PathFindingContext context, int tx, int ty) {
		return 1;
	}

}

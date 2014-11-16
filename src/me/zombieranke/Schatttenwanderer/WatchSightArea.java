package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.geom.GeomUtil;
import org.newdawn.slick.geom.GeomUtil.HitResult;
import org.newdawn.slick.geom.GeomUtilListener;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.util.FastTrig;

@SuppressWarnings("serial")
public class WatchSightArea extends Shape
{
	
	
	protected static final int DEFAULT_SEGMENT_COUNT = 50;
	
	 private int segmentCount;

	    private float radius;
	    private float direction;
	    private float angle;
	    private Wall sightCollision;
	    private GeomUtil util = new GeomUtil();

	    public WatchSightArea(float centerPointX, float centerPointY, float radius, float direction, float angle, Wall sightCollision)
	    {
	        this(centerPointX, centerPointY, radius, direction, angle,sightCollision, DEFAULT_SEGMENT_COUNT);
	    }

	    public WatchSightArea(float centerPointX, float centerPointY, float radius, float direction, float angle, Wall sightCollision, int segmentCount) 
	    {
	        this.x = centerPointX;
	        this.y = centerPointY;
	        this.radius = radius;
	        this.segmentCount = segmentCount;
	        this.direction = direction;
	        this.angle = angle;
	        this.sightCollision = sightCollision;
	        checkPoints();
	    }

	@Override
	public Shape transform(Transform transform)
	{
		return null;
	}

	@Override
	protected void createPoints()
	{
        ArrayList<Float> tempPoints = new ArrayList<Float>();

        maxX = -Float.MIN_VALUE;
        maxY = -Float.MIN_VALUE;
        minX = Float.MAX_VALUE;
        minY = Float.MAX_VALUE;
        
        Shape colBox = sightCollision.getCollisionArea(); 

        float start = direction-angle/2;
        float end = direction+angle/2;
        
        tempPoints.add(new Float(x));
        tempPoints.add(new Float(y));
        
        int step = (int) (angle / segmentCount);
        
        for (float a=start;a<=end+step;a+=step) 
        {
            float ang = a;
            if (ang > end) 
            {
                ang = end;
            }
            float newX = (float) (x + (FastTrig.cos(Math.toRadians(ang)) * radius));
            float newY = (float) (y + (FastTrig.sin(Math.toRadians(ang)) * radius));

            if(newX > maxX) 
            {
                maxX = newX;
            }
            if(newY > maxY) 
            {
                maxY = newY;
            }
            if(newX < minX)
            {
            	minX = newX;
            }
            if(newY < minY)
            {
            	minY = newY;
            }
            
            Line colLine = new Line(x, y, newX, newY);
            
            if(colLine.intersects(colBox))
            {
            	HitResult hr = util.intersect(colBox, colLine);
            	newX = hr.pt.getX();
            	newY = hr.pt.getY();
            }

            	tempPoints.add(new Float(newX));
            	tempPoints.add(new Float(newY));
        }
        points = new float[tempPoints.size()];
        for(int i=0;i<points.length;i++) 
        {
            points[i] = ((Float)tempPoints.get(i)).floatValue();
        }
		
	}

}

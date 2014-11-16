package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.GeomUtil;
import org.newdawn.slick.geom.GeomUtilListener;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.svg.Gradient;

public class ShapeTests extends BasicGameState implements GeomUtilListener {
	
	/** The shape we're cutting out of */
	private Shape source;
	/** The shape we're cutting */
	private Line cut;
	/** The resulting shape */
	private Shape[] result;
	private Shape transformed;
	private float angle = 2.0f;
	
	/** The points used */
	private ArrayList points = new ArrayList();
	/** The points intersected */
	private ArrayList marks = new ArrayList();
	/** The points excluded */
	private ArrayList exclude = new ArrayList();
	
	/** True if we're moving the shape around */
	private boolean dynamic;
	/** The util under test */
	private GeomUtil util = new GeomUtil();
	/** The x position of the shape */
	private int xp;
	/** The y position of the shape */
	private int yp;
	
	/** The circle cutting tool */
	private Circle circle;
	private Line line;

	private boolean union;
	
	/**
	 * Create a simple test
	 */

	/**
	 * Perform the cut
	 */
	public void init()
	{
		Circle source = new Circle(100,100,50);
		this.source = source;
		//circle = new Circle(0,0,50);	
		//this.cut = circle;
		line = new Line(100,100,200,200);
		this.cut = line;
		cut.setLocation(203,78);
		xp = (int) cut.getCenterX();
		yp = (int) cut.getCenterY();
		makeBoolean();
	}
	
	/**
	 * @see BasicGame#init(GameContainer)
	 */
	public void init(GameContainer container,StateBasedGame game) throws SlickException
	{
		util.setListener(this);
		init();
		container.setVSync(true);
	}

	/**
	 * @see BasicGame#update(GameContainer, int)
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		if (container.getInput().isKeyPressed(Input.KEY_ENTER))
		{
			union = !union;
			makeBoolean();
		}	
			//xp = container.getInput().getMouseX();
			//yp = container.getInput().getMouseY();
			xp = 100;
			yp = 100;
			cut.set(xp,yp,xp+100,yp+100);
			makeBoolean();
	}

	/**
	 * Make the boolean operation
	 */
	private void makeBoolean()
	{
		if (union)
		{
			result = util.union(source, cut);
		} 
		else
		{
			result = util.subtract(source, cut);
			transformed = result[0].transform(Transform.createRotateTransform(angle,xp,yp));
		}
	}
	
	/**
	 * @see org.newdawn.slick.Game#render(GameContainer, Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		g.setColor(Color.white);
		g.fill(result[0], new GradientFill(0, 0, new Color(Color.red), 0f , 50f, new Color(Color.white), true));
		//g.translate(0,300);
		g.draw(transformed);
		g.fill(transformed, new GradientFill(0, 0, new Color(Color.red), 35.4f , 35.4f, new Color(Color.white), true));	
		g.drawString("Polys:"+result.length,10,100);
		g.drawString("X:"+xp,10,120);
		g.drawString("Y:"+yp,10,130);
	}
		
	
	
	/**
	 * Entry point to our test
	 * 
	 * @param argv The arguments passed to the test
	 */
	



	@Override
	public int getID()
	{
		return 3;
	}

	@Override
	public void pointExcluded(float x, float y)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pointIntersected(float x, float y)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pointUsed(float x, float y)
	{
		// TODO Auto-generated method stub
		
	}
}



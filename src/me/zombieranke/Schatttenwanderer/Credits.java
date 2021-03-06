package me.zombieranke.Schatttenwanderer;

import me.zombieranke.utils.Ressources;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Credits extends BasicGameState
{
	private Image background;
	private Image text;
	private Image image1;
	private Image image2;
	private Image image3;
	private static final int ID = 7;
	private int shiftUpwards = 1300;
	private int fadeIn = 0;
	private int fadeIn2 = 0;
	private int fadeIn3 = 0;
	private Music music = Ressources.CREDITS_MUSIC;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = Ressources.BACKGROUND_CREDITS;
		text = Ressources.CREDITS_TEXT;
		image1 = Ressources.CREDITS_IMAGE1;
		image2 = Ressources.CREDITS_IMAGE2;
		image3 = Ressources.BACKGROUND_SUCCESS;
	}
	
	@Override
	public void enter(GameContainer container,StateBasedGame game)
	{
		shiftUpwards = 1300;
		fadeIn = 0;
		fadeIn2 = 0;
		fadeIn3 = 0;
		music.loop(1,Ressources.Volume * 2.333f);
		music.fade(0, Ressources.Volume * 2.333f, false);
	}
	
	@Override
	public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException
	{
		background.draw();
		text.draw(container.getWidth()/2 - 250, shiftUpwards);
		image1.draw(80, 150, new Color(1f,1f,1f,0.01f*fadeIn));
		image2.draw(970, 400, new Color(1f,1f,1f,0.01f*fadeIn2));
		image3.draw(container.getWidth()/2-320,330,640,512, new Color(1f,1f,1f,0.01f*fadeIn3));
		if(shiftUpwards<0)
		{
			g.setColor(Color.red);
			g.drawString("Like this music? It's from CarboHydroM. http://www.carbohydrom.net/", 330, 300);
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		Input input = container.getInput();
		
		if(input.isKeyDown(Input.KEY_SPACE))
		{
			shiftUpwards -= 3;
		}
		else
		{
			shiftUpwards -= 1;
		}
		
		if(shiftUpwards<-300)
		{
			music.fade(3000,0,true);
		}
		
		if(shiftUpwards<-500)
		{
			shiftUpwards = 1300;
			game.enterState(1, new FadeOutTransition(), new FadeInTransition());
		}
		
		if((450 < shiftUpwards) && (shiftUpwards < 800) && (fadeIn<100))
		{
			fadeIn++;
		}
		if((shiftUpwards < 450) && (fadeIn > 0))
		{
			fadeIn--;
		}
		
		if((250 < shiftUpwards) && (shiftUpwards < 600) && (fadeIn2<100))
		{
			fadeIn2++;
		}
		if((shiftUpwards < 250) && (fadeIn2 > 0))
		{
			fadeIn2--;
		}
		
		if((-400 < shiftUpwards) && (shiftUpwards < 0) && (fadeIn3<100))
		{
			fadeIn3++;
		}
		if((shiftUpwards < -400) && (fadeIn3 > 0))
		{
			fadeIn3--;
		}
		input.clearControlPressedRecord();
		input.clearKeyPressedRecord();
		input.clearMousePressedRecord();
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
}

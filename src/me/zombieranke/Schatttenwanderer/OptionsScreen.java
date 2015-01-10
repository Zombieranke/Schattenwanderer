package me.zombieranke.Schatttenwanderer;

import java.util.ArrayList;

import me.zombieranke.utils.Ressources;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class OptionsScreen extends BasicGameState {

	private static final int ID = 3;
	private Image background;
	private MouseOverArea soundBar;
	private MenuFunc soundSlider;
	private MenuFunc back;
	private ArrayList<MenuFunc> sound = new ArrayList<MenuFunc>(); 
	private MenuFunc m;
	private float barLength = 500f;
	private float tempVolume = 0f;
	private Music menuMusic;
	

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		background = Ressources.BACKGROUND_MENU;
		menuMusic = Ressources.MENU_MUSIC;
		back = new MenuFunc(new MouseOverArea(container, Ressources.BACK_UNLIGHTED, 100, 500), Ressources.BACK_LIGHTED, 1);
		soundBar = new MouseOverArea(container, Ressources.SOUND_BAR, 400, 230);
		soundSlider = new MenuFunc(new MouseOverArea(container, Ressources.SOUND_SLIDER_UNLIGHTED, 450, 220), Ressources.SOUND_SLIDER_LIGHTED, 3);
		for (int i = 0; i <= 3; i++)
		{
			sound.add(new MenuFunc(new MouseOverArea(container, new Image("res/Sound" + i + "_Unlighted.png"), 300, 200), new Image("res/Sound" + i + "_Lighted.png"), 3));
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw();
		soundBar.render(container, g);
		soundSlider.draw(g, container);
		back.draw(g, container);
		m.draw(g, container);
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		Input input = container.getInput();
		/* �berpr�fe den Volume und stelle das jeweilge Mouse Over Area ein (Stufe 0-3)*/
		if (Ressources.Volume == 0)
		{
			m = sound.get(0);
		}
		else if (Ressources.Volume > 0 && Ressources.Volume < (barLength/3)/500f)
		{
			m = sound.get(1);
		}
		else if (Ressources.Volume > (barLength/3f)/500f && Ressources.Volume < (2f * (barLength/3f))/500f)
		{
			m = sound.get(2);
		}
		else if (Ressources.Volume > (2f * (barLength/3f))/500f)
		{
			m = sound.get(3);
		}
		
		/* Wenn der Sound button gedr�ckt wird: Mute */
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			if(m.isMouseOver())
			{
				if (Ressources.Volume == 0f)
				{
					Ressources.Volume = tempVolume;
					menuMusic.play(1f, Ressources.Volume);
					
				}
				else
				{
					tempVolume = Ressources.Volume;
					Ressources.Volume = 0f;
					menuMusic.pause();
				}
			}
			if (back.isMouseOver())
			{
				game.enterState(back.getState());
			}
			
		}
		
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
		{
			if (soundBar.isMouseOver())
			{
				soundSlider.setX(input.getMouseX());
				float getVolume = (input.getMouseX()-400)/500f;
				Ressources.Volume = getVolume;
				menuMusic.setVolume(Ressources.Volume);
			}
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE))
		{
			game.enterState(1);
		}

	}
	
	 @Override
	 public void enter(GameContainer container, StateBasedGame game)
	 {
		 if(!menuMusic.playing())
		 {
			 menuMusic.loop(1f,Ressources.Volume);
		 }
	}
	 

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}

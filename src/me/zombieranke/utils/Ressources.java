package me.zombieranke.utils;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class Ressources
{
	public static Image GAME_BACKGROUND;
	public static Image UI_BACKGROUND;
	public static Image BACKGROUND_FAIL;
	public static Image BACKGROUND_MENU;
	public static Image BACKGROUND_SUCCESS;
	public static Image BACKGROUND_CREDITS;
	public static Image DEATH_1;
	public static Image EXIT_LIGHTED;
	public static Image EXIT_UNLIGHTED;
	public static Image LASER;
	public static Image LEVELS_LIGHTED;
	public static Image LEVELS_UNLIGHTED;
	public static Image LEVER_DOWN;
	public static Image LEVER_UP;
	public static Image NEW_GAME_LIGHTED;
	public static Image NEW_GAME_UNLIGTHED;
	public static Image OPTIONS_LIGHTED;
	public static Image OPTIONS_UNLIGHTED;
	public static Image WALL_TYPE_1;
	public static Image GAME_WON;
	public static Image LEVEL1_UNLIGHTED;
	public static Image LEVEL1_LIGHTED;
	public static Image LEVEL2_UNLIGHTED;
	public static Image LEVEL2_LIGHTED;
	public static Image LEVEL_GREYED;
	public static Image BACK_LIGHTED;
	public static Image BACK_UNLIGHTED;
	public static Image SOUND_BAR;
	public static Image SOUND_SLIDER_UNLIGHTED;
	public static Image SOUND_SLIDER_LIGHTED;
	public static Image MISSION_ACCOMPLISHED;
	public static Image EXIT_MISSION_CLICKED;
	public static Image EXIT_MISSION_UNCLICKED;
	public static Image NEXT_MISSION_CLICKED;
	public static Image NEXT_MISSION_UNCLICKED;
	public static Image CREDITS_TEXT;
	
	public static SpriteSheet ENEMY_SPRITESHEET;
	public static SpriteSheet EXIT_SPRITESHEET;
	public static SpriteSheet PLAYER_SPRITESHEET;
	public static SpriteSheet TARGET_SPRITESHEET;
	public static SpriteSheet TARGET_DEATH_SPRITESHEET;
	public static SpriteSheet GAME_WON_SPRITESHEET;
	
	public static Music ALARM_MUSIC;
	public static Music GAME_MUSIC;
	public static Music MENU_MUSIC;
	public static Music END_MUSIC;
	public static Music DEATH_MUSIC;
	
	public static Sound EXIT_SOUND;
	public static Sound LEVER_SOUND;
	
	/** Initializiere den Volume als Variable f�r alle Sounds im Spiel */
	public static float Volume = 0.3f;
	
	public static void init() throws SlickException
	{
		GAME_BACKGROUND 	= new Image("res/Game_Background.jpg");
		UI_BACKGROUND 		= new Image("res/UI_Background.png");
		BACKGROUND_FAIL		= new Image("res/Background_Fail.jpg");
		BACKGROUND_MENU		= new Image("res/Background_Menu.jpg");
		BACKGROUND_SUCCESS	= new Image("res/Background_Success.jpg");
		BACKGROUND_CREDITS  = new Image("res/Background_Credits.jpg");
		DEATH_1				= new Image("res/Death_1.png");
		EXIT_LIGHTED		= new Image("res/Exit_Lighted.png");
		EXIT_UNLIGHTED		= new Image("res/Exit_Unlighted.png");
		LASER				= new Image("res/Laser.png");
		LEVELS_LIGHTED		= new Image("res/Levels_Lighted.png");
		LEVELS_UNLIGHTED	= new Image("res/Levels_Unlighted.png");
		LEVER_DOWN			= new Image("res/Lever_Down.png");
		LEVER_UP			= new Image("res/Lever_Up.png");
		NEW_GAME_LIGHTED	= new Image("res/New_Game_Lighted.png");
		NEW_GAME_UNLIGTHED	= new Image("res/New_Game_Unlighted.png");
		OPTIONS_LIGHTED		= new Image("res/Options_Lighted.png");
		OPTIONS_UNLIGHTED	= new Image("res/Options_Unlighted.png");
		WALL_TYPE_1			= new Image("res/Wall_Type_1.png");
		LEVEL1_UNLIGHTED	= new Image("res/Level1_Unlighted.png");
		LEVEL1_LIGHTED		= new Image("res/Level1_Lighted.png");
		LEVEL2_UNLIGHTED	= new Image("res/Level2_Unlighted.png");
		LEVEL2_LIGHTED		= new Image("res/Level2_Lighted.png");
		LEVEL_GREYED		= new Image("res/Level_Greyed.png");
		BACK_LIGHTED 		= new Image("res/Back_Lighted.png");
		BACK_UNLIGHTED		= new Image("res/Back_Unlighted.png");
		SOUND_BAR 			= new Image("res/Sound_Bar.png");
		SOUND_SLIDER_UNLIGHTED = new Image("res/Sound_Slider_Unlighted.png");
		SOUND_SLIDER_LIGHTED = new Image ("res/Sound_Slider_Lighted.png");
		MISSION_ACCOMPLISHED = new Image ("res/Mission_Accomplished.png");
		EXIT_MISSION_CLICKED = new Image ("res/Mission_Accomplished_Exit_Clicked.png");
		EXIT_MISSION_UNCLICKED = new Image ("res/Mission_Accomplished_Exit_Unclicked.png");
		NEXT_MISSION_CLICKED = new Image ("res/Mission_Accomplished_Next_Clicked.png");
		NEXT_MISSION_UNCLICKED = new Image("res/Mission_Accomplished_Next_Unclicked.png");
		CREDITS_TEXT		= new Image ("res/Credits_Text.png");
		
		ENEMY_SPRITESHEET	= new SpriteSheet("res/Enemy_SpriteSheet.png", 20, 20);
		EXIT_SPRITESHEET	= new SpriteSheet("res/Exit_SpriteSheet.png", 96, 32);
		PLAYER_SPRITESHEET	= new SpriteSheet("res/Player_SpriteSheet.png", 20, 20);
		TARGET_SPRITESHEET	= new SpriteSheet("res/Target_SpriteSheet.png", 20, 20);
		TARGET_DEATH_SPRITESHEET = new SpriteSheet("res/Death_SpriteSheet.png", 60, 50);
		GAME_WON_SPRITESHEET = new SpriteSheet("res/Dripping_Blood.png", 29, 248);
		
		ALARM_MUSIC			= new Music("res/Alarm.ogg",false);
		GAME_MUSIC			= new Music("res/Game_Music2.ogg",false);
		MENU_MUSIC          = new Music("res/Main Theme.ogg",false);
		END_MUSIC           = new Music("res/End_Music3.ogg",false);
		DEATH_MUSIC         = new Music("res/Death.ogg",false);
		
		EXIT_SOUND			= new Sound("res/Exit_Sound.ogg");
		LEVER_SOUND			= new Sound("res/Lever_Sound.ogg");
	}
	
	
	
	
	
}

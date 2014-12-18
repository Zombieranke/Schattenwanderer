package me.zombieranke.utils;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

public class Ressources
{
	public static Image GAME_BACKGROUND;
	public static Image BACKGROUND_FAIL;
	public static Image BACKGROUND_MENU;
	public static Image BACKGROUND_SUCCESS;
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
	
	public static SpriteSheet ENEMY_SPRITESHEET;
	public static SpriteSheet EXIT_SPRITESHEET;
	public static SpriteSheet PLAYER_SPRITESHEET;
	public static SpriteSheet TARGET_SPRITESHEET;
	public static SpriteSheet TARGET_DEATH_SPRITESHEET;
	
	public static Music ALARM_MUSIC;
	public static Music GAME_MUSIC;
	
	public static Sound EXIT_SOUND;
	public static Sound LEVER_SOUND;
	
	public static void init() throws SlickException
	{
		GAME_BACKGROUND 	= new Image("res/Game_Background.jpg");
		BACKGROUND_FAIL		= new Image("res/Background_Fail.jpg");
		BACKGROUND_MENU		= new Image("res/Background_Menu.jpg");
		BACKGROUND_SUCCESS	= new Image("res/Background_Success.jpg");
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
		WALL_TYPE_1			= new Image("res/Wall_Type_1.jpg");
		
		ENEMY_SPRITESHEET	= new SpriteSheet("res/Enemy_SpriteSheet.png", 20, 20);
		EXIT_SPRITESHEET	= new SpriteSheet("res/Exit_SpriteSheet.png", 96, 32);
		PLAYER_SPRITESHEET	= new SpriteSheet("res/Player_SpriteSheet.png", 20, 20);
		TARGET_SPRITESHEET	= new SpriteSheet("res/Target_SpriteSheet.png", 20, 20);
		TARGET_DEATH_SPRITESHEET = new SpriteSheet("res/Death_SpriteSheet.png", 50, 50);
		
		ALARM_MUSIC			= new Music("res/Alarm_Music.ogg",false);
		GAME_MUSIC			= new Music("res/Game_Music.ogg",false);
		
		EXIT_SOUND			= new Sound("res/Exit_Sound.ogg");
		LEVER_SOUND			= new Sound("res/Lever_Sound.ogg");
	}
	
	
	
	
	
}

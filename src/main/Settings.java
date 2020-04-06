package main;

import java.util.ArrayList;
import java.util.List;

import gamestate.GameState;
import gamestate.GameStates;

public class Settings {
	
	// General Settings
	public static final int SCREEN_WIDTH = 1600;
	public static final int SCREEN_HEIGHT = 900;
	public static final float SCREEN_RATIO = SCREEN_HEIGHT / SCREEN_WIDTH;
	public static final int FPS_CAP = 100000;
	public static final String TITLE = "A Side Scrolling game";
	
	public static GameStates gameState = GameStates.NORMAL;
	public static GameState currentGameState;
	public static List<GameState> gameStates = new ArrayList<GameState>();
	
	// Rendering Settings
	public static boolean running = false;
	public static boolean initialization = false;
	
	// Logic Settings
	public static final int PHYSICS_FREQ = 60;
}

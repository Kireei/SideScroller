package gamestate;

import main.Settings;
import models.Camera;

public abstract class GameState {
	public GameState() {
		Settings.gameStates.add(this);
	}
	
	public abstract void inputs(double deltaTime);
	public abstract void setCamera(Camera camera);
	public abstract Camera getCamera();
	public abstract GameStates getGameStates();
}

package gamestate;

import org.lwjgl.input.Keyboard;

import main.Rendering;
import models.Camera;

public class GameStateNormal extends GameState{
	private static Camera camera;

	public void inputs(double deltaTime) {
		// Returns a single key however long pressed
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				switch(Keyboard.getEventKey()) {
				case Keyboard.KEY_W:
					//Rendering.camera.moveForward();
					break;
				}
			}
		}
		
		// Below gives key continuously if pressed
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			Rendering.camera.moveForward(deltaTime);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			Rendering.camera.moveBackward(deltaTime);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			Rendering.camera.moveLeft(deltaTime);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			Rendering.camera.moveRight(deltaTime);
		}
		
		
		
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
		
	}
	@Override
	public Camera getCamera() {
		// TODO Auto-generated method stub
		return null;
	}

	public GameStates getGameStates() {
		return GameStates.NORMAL;
	}

	
	
}

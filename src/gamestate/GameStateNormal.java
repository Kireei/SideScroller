package gamestate;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import main.Rendering;
import main.Settings;
import models.Camera;
import rendering.display.DisplayManager;

public class GameStateNormal extends GameState{
	private Camera camera;

	public void inputs(double deltaTime) {
		// Returns a single key however long pressed
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				switch(Keyboard.getEventKey()) {
				case Keyboard.KEY_W:
					//Rendering.camera.moveForward();
					break;
				case Keyboard.KEY_ESCAPE:
					System.exit(0);
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
		//Mouse.setCursorPosition((int)(Settings.SCREEN_WIDTH*0.5), (int)(Settings.SCREEN_HEIGHT*0.5));
		Rendering.camera.addPitch((float)-Mouse.getDY()/16);
		Rendering.camera.addYaw((float)Mouse.getDX()/16);
		
		
		
		
		
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

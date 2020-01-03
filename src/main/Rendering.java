package main;

import org.lwjgl.opengl.Display;

import rendering.display.DisplayManager;

public class Rendering extends Thread{

	
	public void run() {
		DisplayManager.createDisplay();
		while (!Display.isCloseRequested() || !Settings.running) {
			
			DisplayManager.updateDisplay();
		}
		
		
		
		Settings.running = false;
		DisplayManager.closeDisplay();
	}
}

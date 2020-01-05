package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import models.Camera;
import models.Light;
import rendering.display.DisplayManager;
import rendering.display.Renderer;



public class Rendering extends Thread{

	double firstFrameTime = 0;
	public void run() {
		DisplayManager.createDisplay();
		Camera camera = new Camera();
		Light light = new Light(new Vector3f(0,0,0), new Vector3f(1,1,1));
		Renderer renderer = new Renderer(camera);
		
		
		
		
		while (!Display.isCloseRequested() || !Settings.running) {
			double start = System.nanoTime();
			renderer.render(light, camera);

			DisplayManager.updateDisplay();
			
			double done = System.nanoTime();
			if(firstFrameTime / Math.pow(10, 9) <= 1) {
				firstFrameTime += done - start;	
			}else{
				firstFrameTime = 0;
				System.out.println(Math.round(1 / ((done - start) / Math.pow(10, 9))));
			}
		}
		
		
		
		Settings.running = false;
		
		DisplayManager.closeDisplay();
	}
}

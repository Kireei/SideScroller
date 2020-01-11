package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import models.Camera;
import models.Entity;
import models.Light;
import models.ModelTexture;
import models.OBJLoader;
import models.RawModel;
import models.TexturedModel;
import rendering.display.DisplayManager;
import rendering.display.Renderer;



public class Rendering extends Thread{
	public static Loader loader;
	public static Entity newdragon;
	private static Renderer renderer;
	private static Light light;
	private static Camera camera;
	
	
	public Rendering() {
		
		loader = new Loader();
	}
	double firstFrameTime = 0;
	public void run() {
		
		initialize();
		
		while (!Display.isCloseRequested() || !Settings.running) {
			if(Display.isCloseRequested()) Settings.running = false;
			double start = System.nanoTime();
			
			renderer.processEntity(newdragon);
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
		Settings.running = false;
	}
	
	public void initialize() {
		DisplayManager.createDisplay();
		camera = new Camera();
		light = new Light(new Vector3f(0,0,0), new Vector3f(1,1,1));
		renderer = new Renderer(camera);
		RawModel dragon = OBJLoader.loadObjModel("dragon", Rendering.loader);
		TexturedModel Dragon = new TexturedModel(dragon, new ModelTexture(loader.loadTexture("chimp")));
		newdragon = new Entity(Dragon, new Vector3f(0,-3,-15),0,0,0, new Vector3f(1,1,1));
		Settings.initialization = true;
	}
}

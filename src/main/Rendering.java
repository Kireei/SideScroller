package main;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import fontRendering.TextMaster;
import models.Camera;
import models.Entity;
import models.Light;
import models.ModelTexture;
import models.OBJLoader;
import models.RawModel;
import models.TexturedModel;
import rendering.display.DisplayManager;
import rendering.display.Renderer;
import ui.UIElement;
import ui.UIHandler;
import ui.UIMaster;
import windows.Controller;
import windows.MaterialProperties;

public class Rendering extends Thread{
	public static Loader loader;
	public static Entity newdragon;
	private static Renderer renderer;
	private static Light light;
	public static Camera camera;
	
	public static UIMaster uimaster;
	public static UIHandler uihandler;
	public static TextMaster textmaster;
	
	public static List<UIElement> uies = new ArrayList<UIElement>();
	
	public Rendering() {
		
		loader = new Loader();
	}
	double firstFrameTime = 0;
	public synchronized void run() {
		
		initialize();
		
		while (!Display.isCloseRequested() || !Settings.running) {
			if(Display.isCloseRequested()) Settings.running = false;
			double start = System.nanoTime();
			
			renderer.processEntity(newdragon);
			renderer.render(light, camera);
			uimaster.updateUI();
			textmaster.render();
			DisplayManager.updateDisplay();
			
			double done = System.nanoTime();
			if(firstFrameTime / Math.pow(10, 9) <= 1) {
				firstFrameTime += done - start;	
			} else {
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
		uihandler = new UIHandler();
		uimaster = new UIMaster();
		textmaster = new TextMaster();
		textmaster.init();
		uihandler.init();
		uimaster.init();
		//UIHandler.openWindow(MaterialProperties.createWindow());
		Settings.initialization = true;
	}
}

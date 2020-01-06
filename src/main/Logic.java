package main;

import models.OBJLoader;
import models.RawModel;

public class Logic extends Thread{

	public Logic() {
		Settings.running = true;
	}
	public void run() {
		
		double startTime = System.nanoTime();
		double deltaTime = 0;
		while (Settings.running) {
			double currentTime = System.nanoTime();
			deltaTime = currentTime - startTime;
			if(Rendering.loader == null) System.err.println("Loader is null");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			startTime = currentTime;
		}
		
		
	}
}

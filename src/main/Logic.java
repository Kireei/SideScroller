package main;

import gamestate.GameState;
import gamestate.GameStateNormal;

public class Logic extends Thread{

	public Logic() {
		Settings.running = true;
	}
	public void run() {
		
		double startTime = System.nanoTime();
		double deltaTime = 0;
		Settings.currentGameState = new GameStateNormal();
		Settings.currentGameState.setCamera(Rendering.camera);
		while (Settings.running) {
			double currentTime = System.nanoTime();
			deltaTime = (currentTime - startTime)/1000000;
			if(Rendering.loader == null) System.err.println("Loader is null");
			if(Rendering.newdragon == null) System.err.println("Dragon is null");
			
			if(Settings.initialization) {
				Rendering.newdragon.increaseRotation(0, (float) (deltaTime*5*Math.PI/180), 0);
			}
			try {
				
				Settings.currentGameState.inputs(deltaTime);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			
			/*try {
				System.out.println(deltaTime);
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			startTime = currentTime;
		}
		
		
	}
}

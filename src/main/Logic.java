package main;

public class Logic extends Thread{

	public Logic() {
		Settings.running = true;
	}
	public void run() {
		int k = 0;
		while (Settings.running) {
			System.out.println("Thread:" + Thread.currentThread() + ". " + k); 
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			k++;
		}
		
		
	}
}

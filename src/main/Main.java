package main;

public class Main {
	public static void main(String args[]) {	
		//Helst en tr�d f�r att ladda in grejer
		
		
		//En tr�d f�r logiken
		Logic logic = new Logic();
		logic.start();
		
		//En tr�d f�r rendering�
		Rendering rendering = new Rendering();
		rendering.start();
		
		
	}
}

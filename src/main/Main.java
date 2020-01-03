package main;

public class Main {
	public static void main(String args[]) {	
		//Helst en tråd för att ladda in grejer
		
		
		//En tråd för logiken
		Logic logic = new Logic();
		logic.start();
		
		//En tråd för rendering¨
		Rendering rendering = new Rendering();
		rendering.start();
		
		
	}
}

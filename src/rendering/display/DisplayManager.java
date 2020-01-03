package rendering.display;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.PixelFormat;

import main.Settings;

public class DisplayManager {
	
	
	public static void createDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT));
			Display.create(new PixelFormat(0,8,0,8));
			Display.setTitle(Settings.TITLE);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT, 0, 0, 1);
		GL11.glViewport(0, 0, Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT);
		
	}
	
	public static void updateDisplay() {
		Display.sync(Settings.FPS_CAP);
		Display.update();
	}
	
	public static void closeDisplay() {
		Display.destroy();
	}
}

package windows;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import main.Rendering;
import ui.UIElement;
import ui.UIHandler;

public class MaterialProperties {
	public static List<UIElement> createWindow(){
		List<UIElement> window = Rendering.uihandler.createWindow(new Vector2f(5,15), new Vector2f(0,0));
		
		UIElement topEdge = window.get(1);
		
		topEdge.createTitle("Controller", 1,  new Vector2f(0,0));
		
		//UIElement.addSlider(topEdge.createSlider(5, new Vector2f(0.05f, 1), "reflectionSlider"), window);
		//topEdge.getSliders().get(0)[1].createTitle("Reflectivity", 0.5f, new Vector2f(-0.18f, 0.5f*0.028f));
		
		//UIElement.addSlider(topEdge.createSlider(5, new Vector2f(0.05f, 1.06f), "shineDamperSlider"), window);
		//topEdge.getSliders().get(1)[1].createTitle("Shine Damper", 0.5f, new Vector2f(-0.18f, 0.5f*0.028f));
		
		
		Rendering.uihandler.openWindow(window);
		Rendering.uihandler.closeWindow(window);
		
		return window;
	}
}

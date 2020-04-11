package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import main.Rendering;
import windows.ColorPicker;
import windows.Controller;
import windows.MaterialProperties;
import windows.UIWindow;

public class UIMaster {
	public static List<UIElement> uies = Rendering.uies;
	
	public static UIWindow mProp;
	
	public static UIState uiState = UIState.NORMAL;
	public static String testText = new String();
	private static List<String> words = new ArrayList<String>();
	public static List<UIWindow> activeWindows = new ArrayList<UIWindow>();
	public static Map<String, List<UIElement>> windows = new HashMap<String, List<UIElement>>();
	
	public static UIElement activeText;
	
	public static void init() {
		mProp = MaterialProperties.createWindow();
		
	}
	
	public static void updateUI() {
		
		
		if(activeWindows.size() > 0) {
			for(UIWindow uiw: activeWindows) {
				if(uiw.isActive()) {
					UIHandler.closeWindow(uiw);
					UIHandler.openWindow(uiw);
					for(UIElement uie: uiw.getWindow()) {
						uie.checkMouse();
					}
				}
			}		
		}
	}
	
	private static void getTextInput() {
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				if(Keyboard.getEventKey() == Keyboard.KEY_END) uiState = UIState.NORMAL;
				if(Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
					words.add(testText);
					testText = "";
				}else {
					testText = testText.concat(Keyboard.getKeyName(Keyboard.getEventKey()));
				}
				System.out.println(words);
				System.out.println(testText);
				if(testText.equals("STOP")) System.exit(0);
				
				
			}
		}
		
	}
	public static void updateText(UIElement uie) {
		List<GUIText> texts = uie.getTexts();
		Vector3f color = new Vector3f(1,1,1);
		for(GUIText text : texts) {
			color = text.getColour();
			Rendering.textmaster.removeText(text);
		}
		
		texts.clear();
	
		uie.createTitle(TextValues.getValue(uie.getId()), 0.5f, new Vector2f(0.01f,0.5f*0.028f), color);
		
		
		for(GUIText text : texts) {
			Rendering.textmaster.loadText(text);
		}

	}
	public void clearUpdatedText() {
		for(GUIText text : mProp.getWindow().get(1).getSliders().get(0)[2].getTexts()) {
			Rendering.textmaster.removeText(text);
		}
	}
}

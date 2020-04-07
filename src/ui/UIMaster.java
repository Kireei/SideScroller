package ui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import main.Rendering;
import windows.ColorPicker;
import windows.Controller;
import windows.MaterialProperties;

public class UIMaster {
	public static List<UIElement> uies = Rendering.uies;
	
	public List<UIElement> mProp;
	
	public static UIState uiState = UIState.NORMAL;
	public static String testText = new String();
	private static List<String> words = new ArrayList<String>();
	static List<List<UIElement>> activeWindows = new ArrayList<List<UIElement>>();
	
	public static UIElement activeText;
	
	public void init() {
		mProp = MaterialProperties.createWindow();
		
		
	}
	
	public void updateUI() {
		if(uiState == UIState.NORMAL) {
			
			while(Keyboard.next()) {
				if(Keyboard.getEventKeyState()) {
					if(Keyboard.getEventKey() == Keyboard.KEY_G) {
						
					}
					if(Keyboard.getEventKey() == Keyboard.KEY_E) {
						System.out.println("TEST");
						
					}
				} else {
					if(Keyboard.getEventKey() == Keyboard.KEY_G) {
						System.out.println("TEST");						if(!mProp.get(1).isActive()) {
							Rendering.uihandler.openWindow(mProp);
							return;
							}
						if(mProp.get(1).isActive()) {
							Rendering.uihandler.closeWindow(mProp);
							
							
						}
					}
				}
			}
		} else if(uiState == UIState.TEXT_INPUT) {
			getTextInput();
		}
		
		if(activeWindows.size() > 0) {
			for(UIElement uie: activeWindows.get(activeWindows.size() - 1)) {
				if(uie.isActive()) {
					uie.checkMouse();
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
		for(GUIText text : mProp.get(1).getSliders().get(0)[2].getTexts()) {
			Rendering.textmaster.removeText(text);
		}
	}
}

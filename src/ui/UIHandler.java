package ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import main.Loader;
import main.Rendering;
import models.ModelTexture;
import models.RawModel;
import models.TexturedModel;

public class UIHandler {
	public static Loader loader = Rendering.loader;
	public static RawModel rawModel;
	public static FontType font;
	
	public static float sizeRadioButton = 0.03f;
	public static float sizeSlider = 0.03f;
	
	public static TexturedModel radioButtonUnchecked;
	public static TexturedModel radioButtonChecked;
	public static TexturedModel leftSlider;
	public static TexturedModel middleSlider;
	public static TexturedModel rightSlider;
	public static TexturedModel slider;
	
	public List<UIElement> uies;
	
	
	
	public void init() {
		//loader = Rendering.loader;
		rawModel = rawModel();
		font = new FontType(loader.loadFont("arial"), new File("res/Fonts/arial.fnt"));
		radioButtonUnchecked = new TexturedModel(rawModel, new ModelTexture(loader.loadTexture("GUI/Radiobutton Unclicked")));
		radioButtonChecked = new TexturedModel(rawModel, new ModelTexture(loader.loadTexture("GUI/Radiobutton Clicked")));
		leftSlider = new TexturedModel(rawModel, new ModelTexture(loader.loadTexture("GUI/Slider edge left")));
		middleSlider = new TexturedModel(rawModel, new ModelTexture(loader.loadTexture("GUI/Slider middle")));
		rightSlider = new TexturedModel(rawModel, new ModelTexture(loader.loadTexture("GUI/Slider edge right")));
		slider = new TexturedModel(rawModel, new ModelTexture(loader.loadTexture("GUI/Slider")));
		
	}
	private RawModel rawModel() {
		float aspectRatio = 9f / 16f;
		float[] positions = {-aspectRatio, 1.0f, 0.0f, -aspectRatio, -1.0f, 0.0f, aspectRatio, -1.0f, 0.0f, aspectRatio, 1.0f, 0.0f};
		float[] textureCoords = {0, 0, 0, 1, 1, 1, 1, 0};
		float[] normals = {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1};
		int[] indices = {0, 1, 2, 0, 2, 3};
		
		return loader.loadToVAO(positions, textureCoords, normals, indices);
	}

	
	public List<UIElement> createWindow(Vector2f scale, Vector2f position) {
		float tileSize = 0.06f;
		float tileSpaceX = tileSize*18f/16f;
		float tileSpaceY = tileSize*2;
		 
		
		
		List<UIElement> window = new ArrayList<UIElement>();
		UIElement TLCorner = new UIElement(new Vector3f(position.x, -position.y,0), new Vector3f(0,0,0), new Vector2f(tileSize, tileSize), new ModelTexture(loader.loadTexture("GUI/Corner 1")));
		UIElement topEdge = new UIElement(new Vector3f(tileSpaceX + position.x, -position.y, 0), new Vector3f(0,0,0), new Vector2f(tileSize*(scale.x-2), tileSize), new ModelTexture(loader.loadTexture("GUI/Edge 1")));
		UIElement TRCorner = new UIElement(new Vector3f(tileSpaceX * (scale.x - 1) + position.x, -position.y, 0), new Vector3f(0,0,0), new Vector2f(tileSize, tileSize), new ModelTexture(loader.loadTexture("GUI/Corner 2")));
		UIElement LEdge = new UIElement(new Vector3f(position.x, -tileSpaceY - position.y, 0), new Vector3f(0,0,0), new Vector2f(tileSize, tileSize * (scale.y - 2)), new ModelTexture(loader.loadTexture("GUI/Edge 2")));
		UIElement middle = new UIElement(new Vector3f(tileSpaceX + position.x, -tileSpaceY - position.y, 0), new Vector3f(0,0,0), new Vector2f(tileSize * (scale.x - 2), tileSize * (scale.y - 2)), new ModelTexture(loader.loadTexture("GUI/Background")));
		UIElement REdge = new UIElement(new Vector3f(tileSpaceX * (scale.x - 1) + position.x, -tileSpaceY - position.y,0), new Vector3f(0,0,0), new Vector2f(tileSize, tileSize * (scale.y - 2)), new ModelTexture(loader.loadTexture("GUI/Edge 3")));
		UIElement LLCorner = new UIElement(new Vector3f(position.x, -tileSpaceY * (scale.y - 1) - position.y,0), new Vector3f(0,0,0), new Vector2f(tileSize, tileSize), new ModelTexture(loader.loadTexture("GUI/Corner 3")));
		UIElement bottomEdge = new UIElement(new Vector3f(tileSpaceX+ position.x, -tileSpaceY * (scale.y - 1) - position.y, 0), new Vector3f(0,0,0), new Vector2f(tileSize * (scale.x - 2), tileSize), new ModelTexture(loader.loadTexture("GUI/Edge 4")));
		UIElement LRCorner = new UIElement(new Vector3f(tileSpaceX * (scale.x - 1) + position.x, -tileSpaceY * (scale.y - 1) - position.y,0), new Vector3f(0,0,0), new Vector2f(tileSize, tileSize), new ModelTexture(loader.loadTexture("GUI/Corner 4")));
		window.add(TLCorner);
		window.add(topEdge);
		window.add(TRCorner);
		window.add(LEdge);
		window.add(middle);
		window.add(REdge);
		window.add(LLCorner);
		window.add(bottomEdge);
		window.add(LRCorner);
		return window;
	}
	
	public void openWindow(List<UIElement> window) {
		window.get(1).setActive(true);
		
		for(int i = 0; i < window.size(); i++) {
			Rendering.uies.add(window.get(i));
			
			for(UIElement[] slider: window.get(i).getSliders()) {
				Rendering.uies.add(slider[0]);
				Rendering.uies.add(slider[1]);
				Rendering.uies.add(slider[2]);
				Rendering.uies.add(slider[3]);
				for(int j = 0; j < 4; j++) {
					for(GUIText text: slider[j].getTexts()) {
						//TextMaster.loadText(text);
					}
				}
			}
			
			
			for(UIElement rb: window.get(i).getRadioButtons()) {
				Rendering.uies.add(rb);
				for(GUIText text: rb.getTexts()) {
					Rendering.textmaster.loadText(text);
				}
				
			}
			for(GUIText text: window.get(i).getTexts()) {
				Rendering.textmaster.loadText(text);
			}
			
		}
		UIMaster.activeWindows.add(window);
	}
	
	public void closeWindow(List<UIElement> window) {
		for(int i = 0; i < window.size(); i++) {
			for(UIElement rb: window.get(i).getRadioButtons()) {
				Rendering.uies.remove(rb);
				for(GUIText text: rb.getTexts()) {
					Rendering.textmaster.removeText(text);
					
				}
			}
			for(UIElement[] slider: window.get(i).getSliders()) {
				Rendering.uies.remove(slider[0]);
				Rendering.uies.remove(slider[1]);
				Rendering.uies.remove(slider[2]);
				Rendering.uies.remove(slider[3]);
				for(int j = 0; j < 4; j++) {
					for(GUIText text: slider[j].getTexts()) {
						
						Rendering.textmaster.removeText(text);
					}
				}
			}
			for(GUIText text: window.get(i).getTexts()) {
				try{
					Rendering.textmaster.removeText(text);
				}catch (Exception e){
					System.err.println(e);
				}
			}
			Rendering.uies.remove(window.get(i));
			window.get(1).setActive(false);
			UIMaster.activeWindows.remove(window);
		}
	}
	
	
	
	public void destroyWindow() {
		uies.clear();
	}
}

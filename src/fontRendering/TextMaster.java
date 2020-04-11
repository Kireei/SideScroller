package fontRendering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontMeshCreator.TextMeshData;
import main.Loader;
import main.Rendering;

public class TextMaster {
	//private static Loader loader = Rendering.loader;
	public static Map<FontType, List<GUIText>> texts = new HashMap<FontType, List<GUIText>>();
	private static FontRenderer renderer;
	
	public void init(){
		renderer = new FontRenderer();
	}
	
	public void render(){
		renderer.render(texts);
	}
	
	public void loadText(GUIText text){
		FontType font = text.getFont();
		TextMeshData data = font.loadText(text);
		int vao = Rendering.getLoader().loadToVAO(data.getVertexPositions(), data.getTextureCoords());
		text.setMeshInfo(vao, data.getVertexCount());
		List<GUIText> textBatch = texts.get(font);
		if(textBatch == null){
				textBatch = new ArrayList<GUIText>();
				texts.put(font, textBatch);
		}
		textBatch.add(text);
	}
	
	public void removeText(GUIText text){
		List<GUIText> textBatch = texts.get(text.getFont());
		textBatch.remove(text);
		if(textBatch.isEmpty()){
			texts.remove(text.getFont());
		}
	}
	
	public void cleanUp(){
		renderer.cleanUp();
	}
	
	
}

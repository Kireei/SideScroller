package rendering.display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import main.Rendering;
import models.Camera;
import models.Entity;
import models.Light;
import models.TexturedModel;
import shaders.StaticShader;
import shaders.UIShader;
import ui.UIRenderer;

public class Renderer {
	
	public static final float FOV = 45;
	public static final float NEAR_PLANE = 0.1f;
	public static final float FAR_PLANE = 1000f;
	
	public static Vector3f backgroundColor;
	
	private Matrix4f projectionMatrix;
	
	private StaticShader shader = new StaticShader();
	private EntityRenderer renderer;
	private UIShader uishader = new UIShader();
	private UIRenderer uirenderer;
	
	
	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();	
	
	public Renderer(Camera camera){
		GL11.glFrontFace(GL11.GL_CCW);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		createProjectionMatrix();
		renderer = new EntityRenderer(shader, projectionMatrix);
		uirenderer = new UIRenderer(uishader, projectionMatrix);
		backgroundColor = new Vector3f(0, 0, 1);

	}
	
	
	public void render(Light sun, Camera camera){
		prepare();
		shader.start();
		shader.loadLight(sun);
		shader.loadViewMatrix(camera);
		
		renderer.render(entities);
		shader.stop();
		uishader.start();
		uirenderer.render(Rendering.uies);
		uishader.stop();
		
		entities.clear();
		
		
	}
	
	
	
	public void processEntity(Entity entity){
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if(batch != null){
			batch.add(entity);
		} else {
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}
	
	public void prepare(){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(backgroundColor.x, backgroundColor.y, backgroundColor.z, 1);
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
	}
	
	private void createProjectionMatrix(){
	  	projectionMatrix = new Matrix4f();
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))));
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}
	
		
	public void cleanUp(){
		shader.cleanUp();
	}

}

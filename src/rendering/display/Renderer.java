package rendering.display;

import org.lwjgl.opengl.GL30;

public class Renderer {
	private StaticShader shader;
	public Renderer(StaticShader shader) {
		this.shader = shader;
	}
	
	public void render() {
		prepare();
	}
	
}

package models;

public class ModelTexture {
	private int textureID;
	
	private float shineDamper = 0.5f;
	private float reflectivity = 0.5f;
	
	public ModelTexture(int id){
		this.textureID = id;
	}
	
	public int getID(){
		return this.textureID;
	}

	public int getTextureID() {
		return textureID;
	}

	public void setTextureID(int textureID) {
		this.textureID = textureID;
	}

	public float getShineDamper() {
		return shineDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}

	
}

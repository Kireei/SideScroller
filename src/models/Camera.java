package models;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import main.Settings;

public class Camera {
	
	private Vector3f position = new Vector3f(0, 1, 0);
	private float pitch;
	private float yaw;
	private float roll;
	
	private float speed = 0.05f;
	private float rotationSpeed = 0.1f;
	
	public Camera(){
		
	}
	public void moveForward(double deltaTime){
		position.z -= Math.cos(Math.toRadians(yaw)) * deltaTime * speed;
		position.x += Math.sin(Math.toRadians(yaw)) * deltaTime * speed;
	}
	public void moveBackward(double deltaTime){
		position.z += Math.cos(Math.toRadians(yaw)) * deltaTime * speed;
		position.x -= Math.sin(Math.toRadians(yaw)) * deltaTime * speed;
	}
	public void moveLeft(double deltaTime){
		position.z -= Math.cos(Math.toRadians(yaw - 90)) * deltaTime * speed;
		position.x += Math.sin(Math.toRadians(yaw - 90)) * deltaTime * speed;
	}
	public void moveRight(double deltaTime){
		position.z -= Math.cos(Math.toRadians(yaw + 90)) * deltaTime * speed;
		position.x += Math.sin(Math.toRadians(yaw + 90)) * deltaTime * speed;
	}
	public void rotateUp(double deltaTime) {
		pitch -= rotationSpeed * deltaTime;
	}
	public void rotateDown(double deltaTime) {
		pitch += rotationSpeed * deltaTime;
	}
	public void rotateLeft(double deltaTime) {
		yaw -= rotationSpeed * deltaTime;
	}
	public void rotateRight(double deltaTime) {
		yaw += rotationSpeed * deltaTime;
	}
	
	
	public void move(){
		//if(UIMaster.uiState == UIState.NORMAL) {
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				
				position.z -= Math.cos(Math.toRadians(yaw)) * speed;
				position.x += Math.sin(Math.toRadians(yaw)) * speed;
				
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				position.z -= Math.cos(Math.toRadians(yaw + 90)) * speed;
				position.x += Math.sin(Math.toRadians(yaw + 90)) * speed;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				position.z -= Math.cos(Math.toRadians(yaw - 90)) * speed;
				position.x += Math.sin(Math.toRadians(yaw - 90)) * speed;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)){
				position.z += Math.cos(Math.toRadians(yaw)) * speed;
				position.x -= Math.sin(Math.toRadians(yaw)) * speed;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				position.y += speed;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
				position.y -= speed;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				speed = 0.8f;
			} else {
				speed = 0.5f;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
				System.exit(0);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				yaw -= 0.75;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				yaw += 0.75;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
				pitch -= 0.5;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
				pitch += 0.5;
			}
			if(Mouse.isButtonDown(1)) {
				pitch += (float)Mouse.getDY() * 0.05f;
				yaw += (float) Mouse.getDX() * (-0.1f * (9f/16f));
			}
		//}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	public void setRoll(float roll) {
		this.roll = roll;
	}
	public void addPitch(float increment) {
		this.pitch += increment;
	}
	public void addRoll(float increment) {
		this.roll+= increment;
	}
	public void addYaw(float increment) {
		this.yaw += increment;
	}

}

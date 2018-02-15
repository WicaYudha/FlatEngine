package com.engine.entities;

import org.lwjgl.input.*;
import org.lwjgl.util.vector.*;

public class Camera {
	
	private Vector3f position = new Vector3f(0, 0 ,0);
	private float pitch;
	private float yaw;
	private float roll;
	
	private float distanceFromPlayer = 7;
	private float angleAroundPlayer = 0;
	
	private final int maxLookUp = 90;
	private final int maxLookDown = -90;
	private final float mouseSpeed = 0.35f;
	
	public void move() {
		calculatePitch();
		calculateAngleAroundPlayer();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 360 - angleAroundPlayer;
		yaw %= 360;
	}
	
	private void calculateCameraPosition(float horizontalDistance, float verticalDistance) {
		float theta = angleAroundPlayer;
		float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(theta)));
		position.x = offsetX;
		position.z = offsetZ;
		position.y = verticalDistance + 2;
		
	}

	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}

	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}

	private void calculateAngleAroundPlayer() {
		if(Mouse.isButtonDown(0)){
			float angleChange = Mouse.getDX() * mouseSpeed;
			angleAroundPlayer -= angleChange;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_R)){
			angleAroundPlayer+= 0.5f;
		}
		
	}

	private void calculatePitch() {
		if(Mouse.isButtonDown(1)) {
			float pitchChange = Mouse.getDY() * mouseSpeed;
			pitch -= pitchChange;
			
			if(pitch < maxLookDown) {
				pitch = maxLookDown;
			} else if(pitch > maxLookUp) {
				pitch = maxLookUp;
			}
			
		}
		
	}

	public float getPitch() {
		return pitch;
	}
	
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	
	public float getYaw() {
		return yaw;
	}
	
	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	
	public float getRoll() {
		return roll;
	}
	
	public void setRoll(float roll) {
		this.roll = roll;
	}

	public Vector3f getPosition() {
		return position;
	}
	
	
	
}

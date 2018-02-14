package com.engine.entities;

import org.lwjgl.util.vector.*;

public class Camera {
	
	private Vector3f position;
	private float pitch;
	private float yaw;
	private float roll;
	
	private final int maxLookUp = 90;
	private final int maxLookDown = -90;
	private final float mouseSpeed = 0.45f;
	
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

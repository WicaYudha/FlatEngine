package com.engine.entities;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

import com.engine.utils.*;

public class Camera {
	
	private static final float PITCH_SENSITIVITY = 0.3f;
	private static final float YAW_SENSITIVITY = 0.3f;
	private static final float MAX_PITCH = 90;
	
	private static final float FOV = 90;
	private static final float NEAR_PLANE = 0.4f;
	private static final float FAR_PLANE = 2500;

	private static final float Y_OFFSET = 5;
	
	private Matrix4f projectionMatrix;
	private Matrix4f viewMatrix = new Matrix4f();
	private Matrix4f reflectedMatrix = new Matrix4f();
	
	private boolean reflected = false;
	
	private Vector3f position = new Vector3f(0, 0 ,0);
	
	private float yaw;
	private SmoothFloat pitch = new SmoothFloat(10, 10);
	private float roll;
	
	private SmoothFloat distanceFromPlayer = new SmoothFloat(0, 10);
	private SmoothFloat angleAroundPlayer = new SmoothFloat(10, 5);
	
	public Camera() {
		this.projectionMatrix = createProjectionMatrix();
	}
	
	private Matrix4f createProjectionMatrix() {
		Matrix4f projectionMatrix = new Matrix4f();
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float yScale = (float)((1.0f / Math.tan(Math.toRadians(FOV / 2.0f))));
		float xScale = yScale / aspectRatio;
		
		float frustumLength = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix.m00 = xScale;
		projectionMatrix.m11 = yScale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustumLength);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustumLength);
		projectionMatrix.m33 = 0;
		
		return projectionMatrix;
	}

	public void move() {
		calculatePitch();
		calculateAngleAroundPlayer();
		calculateZoom();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 360 - angleAroundPlayer.getActual();
		this.yaw %= 360;
		updateViewMatrix();
	}
	
	private void calculateZoom() {
		float targetZoom = distanceFromPlayer.getTarget();
		float zoomLevel = Mouse.getDWheel() * 0.0008f * targetZoom;
		targetZoom -= zoomLevel;
		if (targetZoom < 1) {
			targetZoom = 1;
		}
		distanceFromPlayer.setTarget(targetZoom);
		distanceFromPlayer.update(0.01f);
	}

	private void updateViewMatrix() {
		Maths.updateViewMatrix(viewMatrix, position.x, position.y, position.z, pitch.getActual(), yaw);
		float posY = position.y - (2 * position.y);
		float pitchReflect = -pitch.getActual();
		Maths.updateViewMatrix(reflectedMatrix, position.x, posY, position.z, pitchReflect, yaw);
		
	}

	private void calculateCameraPosition(float horizontalDistance, float verticalDistance) {
		float theta = angleAroundPlayer.getActual();
		position.x = 15 / 2f + (float) (horizontalDistance * Math.sin(Math.toRadians(theta)));
		position.y = verticalDistance + Y_OFFSET;
		position.z = 15 / 2f + (float) (verticalDistance * Math.cos(Math.toRadians(theta)));
		
	}

	private float calculateVerticalDistance() {
		return (float) (distanceFromPlayer.getActual() * Math.sin(Math.toRadians(pitch.getActual())));
	}

	private float calculateHorizontalDistance() {
		return (float) (distanceFromPlayer.getActual() * Math.cos(Math.toRadians(pitch.getActual())));
	}

	private void calculateAngleAroundPlayer() {
		if (Mouse.isButtonDown(0)) {
			float angleChange = Mouse.getDX() * YAW_SENSITIVITY;
			angleAroundPlayer.increaseTarget(-angleChange);
		}
		angleAroundPlayer.update(1.0f / 60.0f);
	}

	private void calculatePitch() {
		if(Mouse.isButtonDown(0)) {
			float pitchChange = Mouse.getDY() * PITCH_SENSITIVITY;
			pitch.increaseTarget(-pitchChange);
			clampPitch();
			
			pitch.update(1.0f / 60.0f);
		}
		
	}

	private void clampPitch() {
		if (pitch.getTarget() < 0) {
			pitch.setTarget(0);
		} else if (pitch.getTarget() > MAX_PITCH) {
			pitch.setTarget(MAX_PITCH);
		}
	}
	
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	
	public void reflect(){
		this.reflected = !reflected;
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

	public double getPitch() {
		return this.pitch.getActual();
	}
	
	
	
}

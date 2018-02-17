package com.engine.utils;

import org.lwjgl.util.vector.*;

import com.engine.entities.*;

public class Maths {

	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {

		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1, 0, 0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0, 1, 0), matrix, matrix);
		Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0, 0, 1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
		return matrix;
	}

	public static Matrix4f createViewMatrix(Camera camera) {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
		return viewMatrix;

	}

	public static void updateViewMatrix(Matrix4f viewMatrix, float x, float y, float z, float pitch,
			float yaw) {
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(pitch), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(yaw), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Vector3f negativeCameraPos = new Vector3f(-x, -y, -z);
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
		
	}
	
	public static float clamp(float value, float min, float max){
		return Math.max(Math.min(value, max), min);
	}
	
	public static Vector3f calcNormal(Vector3f vertex0, Vector3f vertex1, Vector3f vertex2) {
		Vector3f tangentA = Vector3f.sub(vertex1, vertex0, null);
		Vector3f tangentB = Vector3f.sub(vertex2, vertex0, null);
		Vector3f normal = Vector3f.cross(tangentA, tangentB, null);
		normal.normalise();
		return normal;
	}

}

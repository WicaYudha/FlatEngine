package com.engine.shader;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector3f;

public class UniformVector3f extends Uniform {
	
	private float currentX, currentY, currentZ;
	private boolean used = false;
	
	public UniformVector3f(String name) {
		super(name);
	}
	
	public void loadVector3f(float x, float y, float z) {
		if(!used || currentX != x || currentY != y ||  currentZ != z) {
			GL20.glUniform3f(super.getLocation(), x, y, z);
			this.currentX = x;
			this.currentY = y;
			this.currentY = z;
			used = true;
		}
	}
	
	public void loadVector3f(Vector3f vector) {
		loadVector3f(vector.x, vector.y, vector.z);
	}

}

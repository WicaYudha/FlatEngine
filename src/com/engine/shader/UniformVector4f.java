package com.engine.shader;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector4f;

public class UniformVector4f extends Uniform {
	
	private float currentW, currentX, currentY, currentZ;
	private boolean used = false;
	
	public UniformVector4f(String name) {
		super(name);
	}
	
	public void loadVector4f(float w, float x, float y, float z) {
		if(!used || currentW != w || currentX != x || currentY != y ||  currentZ != z) {
			GL20.glUniform4f(super.getLocation(), w, x, y, z);
			this.currentW = w;
			this.currentX = x;
			this.currentY = y;
			this.currentY = z;
			used = true;
		}
	}
	
	public void loadVector4f(Vector4f vector) {
		loadVector4f(vector.w, vector.x, vector.y, vector.z);
	}

}

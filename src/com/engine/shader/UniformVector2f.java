package com.engine.shader;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector2f;

public class UniformVector2f extends Uniform {
	
	private float currentX, currentY;
	private boolean used = false;
	
	public UniformVector2f(String name) {
		super(name);
	}
	
	public void loadVector2f(float x, float y) {
		if(!used || currentX != x || currentY != y) {
			GL20.glUniform2f(super.getLocation(), x, y);
			this.currentX = x;
			this.currentY = y;
			used = true;
		}
	}
	
	public void loadVector2f(Vector2f vector) {
		loadVector2f(vector.x, vector.y);
	}

}

package com.engine.shader;

import org.lwjgl.opengl.GL20;

public class UniformFloat extends Uniform {
	
	private float current;
	private boolean used = false;
	
	public UniformFloat(String name) {
		super(name);
	}
	
	public void loadFloat(float value) {
		if(!used || current != value) {
			GL20.glUniform1f(super.getLocation(), value);
			used = true;
			current = value;
		}
	}

}

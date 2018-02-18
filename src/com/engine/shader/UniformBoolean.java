package com.engine.shader;

import org.lwjgl.opengl.GL20;

public class UniformBoolean extends Uniform{

	private boolean current;
	private boolean used = false;
	
	public UniformBoolean(String name) {
		super(name);
	}
	
	public void loadBoolean(boolean value) {
		if(!used || current != value) {
			GL20.glUniform1f(super.getLocation(), value ? 1.0f : 0.0f);
			used = true;
			current = value;
		}
	}

}

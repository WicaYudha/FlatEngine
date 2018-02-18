package com.engine.shader;

import org.lwjgl.opengl.GL20;

public class UniformSampler extends Uniform {
	
	private int current;
	private boolean used = false;
	
	public UniformSampler(String name) {
		super(name);
	}
	
	public void loadTextureUnit(int textureUnit) {
		if(!used || current != textureUnit) {
			GL20.glUniform1i(super.getLocation(), textureUnit);
			used = true;
			current = textureUnit;
		}
	}

}
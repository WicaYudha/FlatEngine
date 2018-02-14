package com.engine.shader;

public class StaticShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = ".res/shaders/static/vertex_shader.glsl";
	private static final String FRAGMENT_FILE = ".res/shaders/static/fragment_shader.glsl";
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAllAttributes() {
		
	}

	@Override
	protected void getAllUniformLocation() {
		
	}
	
	
}

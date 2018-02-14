package com.engine.shader;

import org.lwjgl.util.vector.*;

import com.engine.entities.*;
import com.engine.utils.*;

public class StaticShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = ".res/shaders/static/vertex_shader.glsl";
	private static final String FRAGMENT_FILE = ".res/shaders/static/fragment_shader.glsl";
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAllAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoordinates");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocation() {
		
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
	}
	
	
}

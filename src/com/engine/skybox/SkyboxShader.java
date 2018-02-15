package com.engine.skybox;

import org.lwjgl.util.vector.*;

import com.engine.shader.ShaderProgram;

public class SkyboxShader extends ShaderProgram {

	private static final String VERTEX_FILE = "./res/shaders/environment/skybox_vertexshader.glsl";
	private static final String FRAGMENT_FILE = "./res/shaders/environment/skybox_fragmentshader.glsl";
	
	private int location_projectionViewMatrix;
	
	public SkyboxShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
    public void loadProjectionViewMatrix(Matrix4f matrix){
        super.loadMatrixUniform(location_projectionViewMatrix, matrix);
    }

	@Override
	protected void bindAllAttributes() {
		super.bindAttribute(0, "position");
	}

	@Override
	protected void getAllUniformLocation() {
		location_projectionViewMatrix = super.getUniformLocation("projectionViewMatrix");
	}

}

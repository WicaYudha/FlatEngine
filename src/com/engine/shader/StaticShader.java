package com.engine.shader;

import org.lwjgl.util.vector.*;

import com.engine.entities.*;
import com.engine.utils.*;

public class StaticShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "./res/shaders/static/vertex_shader.glsl";
	private static final String FRAGMENT_FILE = "./res/shaders/static/fragment_shader.glsl";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_modelTexture;
	private int location_cameraPosition;
	
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
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_modelTexture = super.getUniformLocation("modelTexture");
		location_cameraPosition = super.getUniformLocation("cameraPosition");
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		super.loadMatrixUniform(location_viewMatrix, viewMatrix);
		super.loadVectorUniform(location_cameraPosition, camera.getPosition());
	}
	
	public void loadProjectionMatrix(Matrix4f projection){
		super.loadMatrixUniform(location_projectionMatrix, projection);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrixUniform(location_transformationMatrix, matrix);
	}
	
	public void connectTextureUnits(){
		super.loadIntUniform(location_modelTexture, 0);
	}
}

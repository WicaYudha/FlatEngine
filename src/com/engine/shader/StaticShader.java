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
	private int location_environmentMap;
	
	UniformMatrix transformationMatrix = new UniformMatrix("transformationMatrix");
	UniformMatrix projectionMatrix = new UniformMatrix("projectionMatrix");
	UniformMatrix viewMatrix = new UniformMatrix("viewMatrix");
	UniformVector3f cameraPosition = new UniformVector3f("cameraPosition");
	UniformSampler modelTexture = new UniformSampler("modelTexture");
	UniformSampler enviroMap = new UniformSampler("enviroMap");
	
	private static String[] inVariables = {"position", "textureCoordinates", "normal"};
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE, inVariables);
		super.storeUniformLocations(true, transformationMatrix, projectionMatrix, viewMatrix, cameraPosition, modelTexture);
	}

	
	protected void getAllUniformLocation() {		
		location_transformationMatrix = transformationMatrix.getLocation();
		location_projectionMatrix = projectionMatrix.getLocation();
		location_viewMatrix = viewMatrix.getLocation();
		location_cameraPosition = cameraPosition.getLocation();
		location_modelTexture = modelTexture.getLocation();
		location_environmentMap = enviroMap.getLocation();
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		transformationMatrix.loadMatrix(viewMatrix);
		cameraPosition.loadVector3f(camera.getPosition());
	}
	
	public void loadProjectionMatrix(Matrix4f projection){
		projectionMatrix.loadMatrix(projection);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix){
		transformationMatrix.loadMatrix(matrix);
	}
	
	public void connectTextureUnits(){
		modelTexture.loadTextureUnit(0);
		enviroMap.loadTextureUnit(1);
	}
}

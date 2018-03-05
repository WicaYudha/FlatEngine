package com.engine.shader;

import org.lwjgl.util.vector.*;

import com.engine.entities.*;
import com.engine.utils.*;

public class StaticShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "./res/shaders/static/vertex_shader.glsl";
	private static final String FRAGMENT_FILE = "./res/shaders/static/fragment_shader.glsl";
	
	private UniformMatrix transformationMatrix = new UniformMatrix("transformationMatrix");
	private UniformMatrix projectionMatrix = new UniformMatrix("projectionMatrix");
	private UniformMatrix viewMatrix = new UniformMatrix("viewMatrix");
	private UniformVector3f cameraPosition = new UniformVector3f("cameraPosition");
	private UniformSampler modelTexture = new UniformSampler("modelTexture");
	private UniformSampler enviroMap = new UniformSampler("enviroMap");
	
	private static String[] inVariables = {"position", "textureCoordinates", "normal"};
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE, inVariables);
		super.storeUniformLocations(true, transformationMatrix, projectionMatrix, viewMatrix, cameraPosition, modelTexture);
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

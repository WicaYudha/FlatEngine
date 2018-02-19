package com.engine.skybox;

import org.lwjgl.util.vector.*;

import com.engine.shader.ShaderProgram;
import com.engine.shader.UniformMatrix;

public class SkyboxShader extends ShaderProgram {

	private static final String VERTEX_FILE = "./res/shaders/environment/skybox_vertexshader.glsl";
	private static final String FRAGMENT_FILE = "./res/shaders/environment/skybox_fragmentshader.glsl";
	
	UniformMatrix projectionViewMatrix = new UniformMatrix("projectionViewMatrix");
	
	public SkyboxShader() {
		super(VERTEX_FILE, FRAGMENT_FILE, "position");
		super.storeUniformLocations(true, projectionViewMatrix);
	}
	
    public void loadProjectionViewMatrix(Matrix4f matrix){
    	projectionViewMatrix.loadMatrix(matrix);
    }
}

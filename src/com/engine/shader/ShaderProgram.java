package com.engine.shader;

import java.io.*;
import java.nio.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

public abstract class ShaderProgram {

	private int programID;
	
	public ShaderProgram(String vertexShaderFile, String fragmentShaderFile, String... inVariables) {
		int vertexShaderID = loadShaderProgram(vertexShaderFile, GL20.GL_VERTEX_SHADER);
		int fragmentShaderID = loadShaderProgram(fragmentShaderFile, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		bindAllAttributes(inVariables);
		GL20.glLinkProgram(programID);
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
	}
	
	protected void storeUniformLocations(boolean validate, Uniform... uniforms){
		for(Uniform uniform : uniforms){
			uniform.storeUniformLocation(programID);
		} 
		if(validate) {
			GL20.glValidateProgram(programID);
		}
	}
	
	private void bindAllAttributes(String[] inVariables) {
		for(int i = 0; i < inVariables.length; i++) {
			GL20.glBindAttribLocation(programID, i, inVariables[i]);
		}
	}
	
	protected void bindAttribute(int index, String attributeName) {
		GL20.glBindAttribLocation(programID, index, attributeName);
	}
	
	
	private int loadShaderProgram(String shaderFile, int type) {
		StringBuilder shader = new StringBuilder();
		try {
			BufferedReader sourceReader = new BufferedReader(new FileReader(shaderFile));
			String line;
			while((line = sourceReader.readLine()) != null) {
				shader.append(line + "\n");
			}
			sourceReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to Load Shader Source: " +shaderFile);
			System.exit(-1);
		}
		
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shader);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.err.println("Failed to Compile Shader Souce: " +shaderFile);
			System.exit(-1);
		}
		return shaderID;
	}
	
	public void start() {
		GL20.glUseProgram(programID);
	}
	
	public void stop() {
		GL20.glUseProgram(0);
	}
	
	public void cleanUp() {
		stop();
		GL20.glDeleteProgram(programID);
	}
	
}

package com.engine.shader;

import java.io.*;
import java.nio.*;

import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

public abstract class ShaderProgram {

	private int programID;
	private int fragmentShaderID;
	private int vertexShaderID;
	
	public ShaderProgram(String vertexShaderFile, String fragmentShaderFile) {
		vertexShaderID = loadShaderProgram(vertexShaderFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShaderProgram(fragmentShaderFile, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		bindAllAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		getAllUniformLocation();
	}
	
	public ShaderProgram(String vertexShaderFile, String fragmentShaderFile, String... inVariables) {
		vertexShaderID = loadShaderProgram(vertexShaderFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderID = loadShaderProgram(fragmentShaderFile, GL20.GL_FRAGMENT_SHADER);
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		bindAllAttributes(inVariables);
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		getAllUniformLocation();
	}
	
	protected abstract void bindAllAttributes();
	
	private void bindAllAttributes(String[] inVariables) {
		for(int i = 0; i < inVariables.length; i++) {
			GL20.glBindAttribLocation(programID, i, inVariables[i]);
		}
	}
	
	protected void bindAttribute(int index, String attributeName) {
		GL20.glBindAttribLocation(programID, index, attributeName);
	}

	protected abstract void getAllUniformLocation();
	
	protected int getUniformLocation(String uniformName) {
		return GL20.glGetUniformLocation(programID, uniformName);
	}
	
	
	protected void loadFloatUniform(int location, float value) {
		GL20.glUniform1f(location, value);
	}
	
	protected void loadIntUniform(int location, int value) {
		GL20.glUniform1i(location, value);
	}
	
	protected void loadVectorUniform(int location, Vector3f value) {
		GL20.glUniform3f(location, value.x, value.y, value.z);
	}
	
	protected void loadBooleanUniform(int location, boolean value) {
		float data = value ? 1 : 0;
		GL20.glUniform1f(location, data);
	}
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	protected void loadMatrixUniform(int location, Matrix4f value) {
		value.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(location, false, matrixBuffer);
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
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteProgram(fragmentShaderID);
		GL20.glDeleteProgram(programID);
		System.out.println("Shader Program Deleted");
	}
	
}

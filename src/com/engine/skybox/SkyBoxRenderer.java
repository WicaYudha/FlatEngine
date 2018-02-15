package com.engine.skybox;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

import com.engine.entities.*;
import com.engine.utils.*;

public class SkyBoxRenderer {
	
	private SkyboxShader shader;
	private Matrix4f projectionMatrix;
	private CubeMap cubeMap;

	public SkyBoxRenderer(CubeMap cubeMap, Matrix4f projectionMatrix) {
		this.projectionMatrix = projectionMatrix;
		shader = new SkyboxShader();
		//System.out.println("log");
		this.cubeMap = cubeMap;
	}

	public void render(Camera camera) {
		shader.start();
		loadProjectionViewMatrix(camera);
		bindTexture();
		bindCubeVao();
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, cubeMap.getCube().getVerticesCount());
		unbindCubeVao();
		shader.stop();
	}

	public void cleanUp() {
		shader.cleanUp();
	}

	private void bindCubeVao() {
		GL30.glBindVertexArray(cubeMap.getCube().getVaoID());
		GL20.glEnableVertexAttribArray(0);
	}

	private void unbindCubeVao() {
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

	private void bindTexture() {
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, cubeMap.getTexture());
	}

	private void loadProjectionViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		Matrix4f projectionViewMatrix = Matrix4f.mul(projectionMatrix, viewMatrix, null);
		shader.loadProjectionViewMatrix(projectionViewMatrix);
	}

}

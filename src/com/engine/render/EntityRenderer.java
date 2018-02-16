package com.engine.render;

import java.util.List;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

import com.engine.entities.*;
import com.engine.models.*;
import com.engine.shader.*;
import com.engine.skybox.*;
import com.engine.utils.*;

public class EntityRenderer {
	
	private StaticShader shader;
	private CubeMap environmentMap;
	
	public EntityRenderer(Matrix4f projectionMatrix, CubeMap environmentMap) {
		this.environmentMap = environmentMap;
		shader = new StaticShader();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.connectTextureUnits();
		shader.stop();
	}
	
	public void render(List<Entity> entities, Camera camera) {
		shader.start();
		shader.loadViewMatrix(camera);
		bindEnvonmentMap();
		for (Entity entity : entities) {
			TexturedModel model = entity.getModel();
			bindModelVao(model);
			loadModelMatrix(entity);
			bindTexture(model);
			GL11.glDrawElements(GL11.GL_TRIANGLES, model.getModel().getVerticesCount(), GL11.GL_UNSIGNED_INT, 0);
			unbindVao();
		}
		shader.stop();
	}

	public void cleanUp() {
		shader.cleanUp();
	}
	
	private void bindModelVao(TexturedModel model) {
		RawModel rawModel = model.getModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
	}

	private void unbindVao() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}

	private void bindTexture(TexturedModel model) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
	}
	
	private void bindEnvonmentMap() {
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, environmentMap.getTexture());
	}

	private void loadModelMatrix(Entity entity) {
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(entity.getPosition(), entity.getRotX(), 
				entity.getRotY(), entity.getRotZ(), entity.getScale());
		shader.loadTransformationMatrix(transformationMatrix);
	}
	

}

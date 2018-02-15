package com.engine.tester;

import java.util.*;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

import com.engine.entities.*;
import com.engine.models.*;
import com.engine.objconverter.*;
import com.engine.render.*;
import com.engine.texture.*;

public class Tester {

	public static void main(String[] args) {
		DisplayManager.createDisplay();

		Camera camera = new Camera();
		Loader loader = new Loader();
		MasterRenderer render = new MasterRenderer(loader);
		List<Entity> entities = new ArrayList<Entity>();
		entities.add(new Entity(loadModel("dragon", loader), new Vector3f(0, -1, 0), 0, 0, 0, 0.5f));

		while (!Display.isCloseRequested()) {
			camera.move();
			render.render(entities, camera);
			DisplayManager.update();
		}
		render.cleanUp();
		loader.cleanUp();
		DisplayManager.destroy();
	}

	private static TexturedModel loadModel(String fileName, Loader loader) {
		RawModel model = OBJFileLoader.loadOBJ(fileName, loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture(fileName));
		return new TexturedModel(model, texture);
	}

}

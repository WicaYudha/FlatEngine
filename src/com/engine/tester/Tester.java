package com.engine.tester;

import org.lwjgl.opengl.*;

import com.engine.entities.*;
import com.engine.models.*;
import com.engine.objconverter.OBJFileLoader;
import com.engine.render.*;
import com.engine.texture.*;

public class Tester {
	
	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Renderer render = new Renderer();
		Loader loader = new Loader();
		Camera camera = new Camera();
		
		RawModel model = loader.loadToVao(null);	
		
		while(!Display.isCloseRequested()) {
			
			render.prepare();
			render.render(model);
			DisplayManager.update();			
		}
		
		loader.cleanUp();
		DisplayManager.destroy();
	}
	
	private static TexturedModel loadModel(String fileName, Loader loader) {
		RawModel model = OBJFileLoader.loadOBJ(fileName, loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture(fileName));
		return new TexturedModel(model, texture);
	}
	
}

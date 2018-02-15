package com.engine.tester;

import org.lwjgl.opengl.Display;

import com.engine.models.RawModel;
import com.engine.render.*;

public class Tester {
	
	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Renderer render = new Renderer();
		Loader loader = new Loader();
		
		
		
		RawModel model = loader.loadToVao(null);	
		
		while(!Display.isCloseRequested()) {
			render.prepare();
			render.render(model);
			DisplayManager.update();			
		}
		
		DisplayManager.destroy();
	}
	
}

package com.engine.tester;

import org.lwjgl.opengl.Display;

import com.engine.render.*;

public class Tester {
	
	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Renderer render = new Renderer();
		Loader loader = new Loader();
		
		float[] vertices = {
				// Left bottom triangle
				-0.5f, 0.5f, 0f,
				-0.5f, -0.5f, 0f,
				0.5f, -0.5f, 0f,
				// Right top triangle
				0.5f, -0.5f, 0f,
				0.5f, 0.5f, 0f,
				-0.5f, 0.5f, 0f
		};
		
		RawModel model = loader.loadToVao(vertices);	
		
		while(!Display.isCloseRequested()) {
			render.prepare();
			render.render(model);
			DisplayManager.update();			
		}
		
		DisplayManager.destroy();
	}
	
}

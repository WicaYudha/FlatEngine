package com.engine.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	private static final int WIDTH = 1270;
	private static final int HEIGHT = 720;
	private static final int FPS_CAP = 60;
	
	public static void createDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setInitialBackground(0, 0, 0);
			Display.create(new PixelFormat().withDepthBits(24).withSamples(4));
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
	}
	
	public static void update() {
		Display.sync(FPS_CAP);
		Display.update();
	}
	
	public static void destroy() {
		Display.destroy();
	}
	
}

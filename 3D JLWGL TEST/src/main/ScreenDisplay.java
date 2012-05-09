package main;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import render.RenderShaders;

public class ScreenDisplay {
	
	public static int screenWidth = 1200, screenHeight = 800;
	
	public static DisplayMode windowedDisplayMode = new DisplayMode(screenWidth, screenHeight);
	public static DisplayMode fullscreenDisplayMode = Display.getDesktopDisplayMode();
	
	public static void initialise() {
		//Initialises display
			try {
				Display.setDisplayMode(windowedDisplayMode);
				Display.setTitle("3D Game version " + Main.version);
				Display.setVSyncEnabled(true);
				Display.create();
			}catch (LWJGLException e) {
				e.printStackTrace();
				System.exit(0);
			}
			
			new RenderShaders();
			
			Mouse.setGrabbed(true);
			Mouse.setCursorPosition(600, 400);
			
			//Initialises OpenGL
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			
			//Creates a new perspective with 30 degree angle (FOV), 640/480 aspect ratio,
			//0.001f zNear (max), 1000 zFar (max)
			gluPerspective((float) Main.fov, (float) screenWidth / (float) screenHeight, 0.001f, 100);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
		}

}

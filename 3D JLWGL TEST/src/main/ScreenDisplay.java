package main;

import static org.lwjgl.opengl.GL11.GL_COLOR_MATERIAL;
import static org.lwjgl.opengl.GL11.GL_FOG;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.lang.Thread.State;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;

import render.RenderFog;
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
		
		Mouse.setGrabbed(true);
		Mouse.setCursorPosition(screenWidth / 2, screenHeight / 2);
		
		new RenderShaders();
	}
}

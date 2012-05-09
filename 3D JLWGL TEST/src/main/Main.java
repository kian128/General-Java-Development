package main;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import input.Controller;

import java.awt.Graphics;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.util.ResourceLoader;

import javax.swing.JFrame;
import java.awt.Font;

import launcher.Launcher;

import org.lwjgl.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import orthographic.FontLoader;
import orthographic.GUIButton;
import orthographic.GUIPaused;
import orthographic.HUDCrosshair;

import render.Render3D;
import render.RenderFog;
import render.RenderLighting;
import render.RenderLightingNew;
import render.RenderShaders;
import render.TextureLoader;
import world.LoadRoomSandbox;
import world.LoadRoomMain;
import world.LoadRoomSandbox;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class Main {
	
	public static String version = "0.0.4 pre-alpha";
	
	public static volatile boolean running = true;
	
	public static Vector3f position = new Vector3f(0, 0, 0);
	public static Vector3f rotation = new Vector3f(0, 0, 0);
	public static Vector3f lightPosition = new Vector3f(0, 4, 0);	
	
	public static int fov = 68;
	
	public static final int gridSize = 10;
	public static final float tileSize = 0.20f;
	public static final float ceilingHeight = 20;
	public static final float floorHeight = -1;
	
	private static long lastFrame;
	private static long lastFPS;
	private static int fps;
	public static boolean printFPS = true;
	
	public static int polygonMode;
	public static String gameMode;
	public static boolean isPaused = false;
	
	public static int tick = 0;
	
	Render3D render = new Render3D();
	
 	public Main(String gameMode) {
		ScreenDisplay.initialise();
		
		new FontLoader();
		
		//Enables some 3D stuff (google it)
		glShadeModel(GL_SMOOTH);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glEnable(GL_ALPHA_TEST);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glDepthFunc(GL_LEQUAL); 
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 
		glEnable(GL_CULL_FACE); //culling is only rendering certain sides of objects
		glCullFace(GL_BACK); //greatly improving performance
		
		if(gameMode == "sandbox") {
			new RenderLightingNew();
		}
		if(gameMode == "main") {
			//new RenderLightingNew();
			new RenderFog(new Color(0f, 0f, 0f, 1), 20f, 100f);
		}
		getDelta();
		lastFPS = getTime();
		
		while(running) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			if(polygonMode == 0) glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
			if(polygonMode == 1) glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			if(polygonMode == 2) glPolygonMode(GL_FRONT_AND_BACK, GL_POINT);
			
			glEnable(GL_CULL_FACE);
	    	glEnable(GL_DEPTH_TEST);
	    	
			if(gameMode == "main") {
				glDisable(GL_CULL_FACE);
		    	glBindTexture(GL_TEXTURE_2D, TextureLoader.loadTexture("res/stone.png"));	
		    	glCallList(LoadRoomMain.floorDisplayList);
		    	glCallList(LoadRoomMain.ceilingDisplayList);
		    	LoadRoomMain.loadWalls();
		    	glLoadIdentity();
			}
			
			if(gameMode == "sandbox") {
		    	glBindTexture(GL_TEXTURE_2D, TextureLoader.loadTexture("res/floor.png"));		    	
		    	glCallList(LoadRoomSandbox.floorDisplayList);
		    	glCallList(LoadRoomSandbox.ceilingDisplayList);
		    	glCallList(LoadRoomSandbox.wallDisplayList);
		    	
		    	glDisable(GL_CULL_FACE);
		    	glBindTexture(GL_TEXTURE_2D, 0);
		    	glCallList(Render3D.objectDisplayList);
		    	glCallList(Render3D.renderModel(0, 2, 0, "res/models/mushroom thing.obj"));
		    	glLoadIdentity();
			}
			
			glBindTexture(GL_TEXTURE_2D, 0);
			glMatrixMode(GL_PROJECTION); 
			glPushMatrix();
			glLoadIdentity();
			glOrtho(0, ScreenDisplay.screenWidth, ScreenDisplay.screenHeight, 0, 1, -1); //creates an orthographics 2d view
			glMatrixMode(GL_MODELVIEW);
			glPushMatrix();
			glLoadIdentity();
			
			glColor4f(1, 1, 1, 1);
			if(isPaused) {
				new GUIPaused();
			}else{
				new HUDCrosshair(Display.getWidth() / 2, Display.getHeight() / 2);
				FontLoader.drawCenteredString(50, 20, "FPS: " + updateFPS());
			}
		    glColor4f(1, 1, 1, 1);
		        
	        glMatrixMode(GL_PROJECTION);
	        glPopMatrix();
	        glMatrixMode(GL_MODELVIEW); //returns to original view
	        glPopMatrix();

	        glRotatef(rotation.x, 1, 0, 0);
	        glRotatef(rotation.y, 0, 1, 0);
	        glRotatef(rotation.z, 0, 0, 1);
	        glTranslatef(position.x, position.y, position.z);
	        
	        if(gameMode == "sandbox") {
	        	if(position.x <= -9.8) position.x = -9.8f;
	        	if(position.x >= 9.8) position.x = 9.8f;
	        	if(position.z <= -9.8) position.z = -9.8f;
	        	if(position.z >= 9.8) position.z = 9.8f;
	        }
	        
	        RenderLighting.setLightPosition(lightPosition.x, lightPosition.y, lightPosition.z);
	        
        	new Controller();
	        
	        tick();
            if(printFPS) {
            	updateFPS();
            }
	            
            Display.update();
			//Wait until the frame rate is 200 fps, if vsync is enabled
			Display.sync(200);
				
			if (Display.isCloseRequested()) {
                running = false;
            }
		}
		cleanUp();
		System.exit(0);
	}
	
	public static void cleanUp() {
		glDeleteShader(RenderShaders.vertexShader);
		glDeleteShader(RenderShaders.fragmentShader);
		glDeleteProgram(RenderShaders.shaderProgram);
		glDeleteTextures(TextureLoader.loadTexture("res/floor.png"));
		glDeleteTextures(TextureLoader.loadTexture("res/stone.png"));
		glDeleteTextures(TextureLoader.loadTexture("res/grass.png"));
        glDeleteLists(LoadRoomSandbox.ceilingDisplayList, 1);
        glDeleteLists(LoadRoomSandbox.wallDisplayList, 1);
        glDeleteLists(LoadRoomSandbox.floorDisplayList, 1);
        glDeleteLists(LoadRoomMain.floorDisplayList, 1);
        glDeleteLists(LoadRoomMain.ceilingDisplayList, 1);
        glDeleteLists(Render3D.objectDisplayList, 1);
        glDeleteLists(Render3D.renderModel(0, 2, 0, "res/models/mushroom thing.obj"), 1);
		Display.destroy();
	}
	
	private static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public static int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}
	
	public String updateFPS() {
		if(getTime() - lastFPS > 1000) {
			if(printFPS) {
				//System.out.println("FPS: " + fps);
				return Integer.toString(fps);
			}
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
		return null;
	}
	
	public void tick() {
		tick++;
	}
	
	public static void main(String[] args) {
		new Launcher(0);
	}

}

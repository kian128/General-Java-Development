package main;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import gui.GuiAbout;
import gui.GuiButton;
import gui.GuiMainMenu;
import gui.GuiPauseMenu;
import gui.GuiWorldSelect;
import gui.HudCrosshair;
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
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import render.FontLoader;
import render.Render3D;
import render.RenderFog;
import render.RenderLighting;
import render.RenderShaders;
import render.TextureLoader;
import world.LoadRoomSandbox;
import world.LoadRoomMain;
import world.LoadRoomSandbox;
import world.LoadTerrain;
import world.LoadWall;
import world.LoadWorldOutside;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class Main {
	
	/** A lot of credit goes to Oskar of TheCodingUniverse (www.thecodinguniverse.com), without his amazing work in opengl I
	 * never have gotten as far as I have. 
	 */
	
	public static String version = "0.0.5 pre-alpha";
	
	public static volatile boolean running = true;
	
	public static Vector3f position = new Vector3f(0, -0.5f, 0);
	public static Vector3f rotation = new Vector3f(0, -180, 0);
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
	public static boolean isGameRunning = false;
	
	public static int tick = 0;
	
	public static Render3D render = new Render3D();
	public static TextureLoader textureLoader = new TextureLoader();
	public static RenderLighting lighting = new RenderLighting();
	public static LoadTerrain terrain = new LoadTerrain();
	
 	public Main() {
		ScreenDisplay.initialise();
		
		//Initialises OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
				
		gluPerspective((float) Main.fov, (float) ScreenDisplay.screenWidth / (float) ScreenDisplay.screenHeight, 0.001f, 200);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
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
		
		lighting.setLightPosition(5, 0, 5);
		
		getDelta();
		lastFPS = getTime();
		
		while(running) {
			OverallStates.defineStates();
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			if(polygonMode == 0) glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
			if(polygonMode == 1) glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			if(polygonMode == 2) glPolygonMode(GL_FRONT_AND_BACK, GL_POINT);
			
			glEnable(GL_CULL_FACE);
	    	glEnable(GL_DEPTH_TEST);
	    	
	    	/** GLU PERSPECTIVE DISPLAY **/
	    	switch(GameStates.state) {
	    		case GAME_DUNGEON_CRAWLER:
	    			glDisable(GL_CULL_FACE);
	    			new LoadRoomMain();
	    			glLoadIdentity();
	    			
	    			break;
	    		case GAME_MODEL_TEST:
	    			lighting.enableLighting();
					lighting.enableAmbientLighting();
	    			glBindTexture(GL_TEXTURE_2D, textureLoader.loadTexture("images/floor.png"));		    	
			    	glCallList(LoadRoomSandbox.floorDisplayList);
			    	glCallList(LoadRoomSandbox.ceilingDisplayList);
			    	glCallList(LoadRoomSandbox.wallDisplayList);
			    	
			    	glDisable(GL_CULL_FACE);
			    	glBindTexture(GL_TEXTURE_2D, 0);
			    	glCallList(Render3D.objectDisplayList);
			    	glCallList(render.renderModel(0, 2, 0, "models/mushroomthing.obj"));
			    	glLoadIdentity();
			    	
			    	if(position.x <= -9.8) position.x = -9.8f;
		        	if(position.x >= 9.8) position.x = 9.8f;
		        	if(position.z <= -9.8) position.z = -9.8f;
		        	if(position.z >= 9.8) position.z = 9.8f;
		        	
			    	break;
	    		case GAME_TERRAIN_TEST:
	    			glDisable(GL_CULL_FACE);
	    			new LoadWorldOutside();
	    			glLoadIdentity();
	    			
	    			break;
	    	}
			/** END GLU PERSPECTIVE DISPLAY **/
			
			glBindTexture(GL_TEXTURE_2D, 0);
			glMatrixMode(GL_PROJECTION); 
			glPushMatrix();
			glLoadIdentity();
			glOrtho(0, ScreenDisplay.screenWidth, ScreenDisplay.screenHeight, 0, 1, -1); //creates an orthographic 2d view
			glMatrixMode(GL_MODELVIEW);
			glPushMatrix();
			glLoadIdentity();
			lighting.disableLighting();
			
			/** ORTHOGRAPHIC DISPLAY **/
			glColor4f(1, 1, 1, 1);
			new LoadingDisplay();
			switch(GuiStates.state) {
				case HUD:
					new HudCrosshair(Display.getWidth() / 2, Display.getHeight() / 2);
					FontLoader.drawCenteredString(50, 20, "FPS: " + Main.updateFPS());
					break;
				case PAUSE:
					new GuiPauseMenu();
					break;
			}
			switch(GameStates.state) {
				case MENU_MAIN:
					new GuiMainMenu();
					break;
				case MENU_ABOUT:
					new GuiAbout();
					break;
				case MENU_WORLD_SELECT:
					new GuiWorldSelect();
					break;
			}
			glColor4f(1, 1, 1, 1);
			/** END ORTHOGRAPHIC DISPLAY **/
		    
		    glMatrixMode(GL_PROJECTION);
	        glPopMatrix();
	        glMatrixMode(GL_MODELVIEW); //returns to original view
	        glPopMatrix();

	        glRotatef(rotation.x, 1, 0, 0);
	        glRotatef(rotation.y, 0, 1, 0);
	        glRotatef(rotation.z, 0, 0, 1);
	        glTranslatef(position.x, position.y, position.z);
	        
        	new Controller();
	        
	        tick();
            if(printFPS) {
            	updateFPS();
            }
	            
            Display.update();
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
		glDeleteTextures(textureLoader.loadTexture("images/floor.png"));
		glDeleteTextures(textureLoader.loadTexture("images/stone.png"));
		glDeleteTextures(textureLoader.loadTexture("images/grass.png"));
        glDeleteLists(LoadRoomSandbox.ceilingDisplayList, 1);
        glDeleteLists(LoadRoomSandbox.wallDisplayList, 1);
        glDeleteLists(LoadRoomSandbox.floorDisplayList, 1);
        glDeleteLists(LoadRoomMain.floorDisplayList, 1);
        glDeleteLists(LoadRoomMain.ceilingDisplayList, 1);
        glDeleteLists(Render3D.objectDisplayList, 1);
        glDeleteLists(render.renderModel(0, 2, 0, "models/mushroomthing.obj"), 1);
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
	
	public static String updateFPS() {
		if(getTime() - lastFPS > 1000) {
			if(printFPS) {
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
		GuiStates.state = GuiStates.state.NULL;
		GameStates.state = GameStates.state.LOADING;
		new Main();
	}

}

package main;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import input.Input;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import render.FontLoader;
import render.RenderPlayer;
import world.MapMain;
import entity.EntityActivatable;
import entity.EntityPlayerBackground;
import entity.EntityPlayerForeground;
import gui.GuiAbout;
import gui.GuiGameComplete;
import gui.GuiLevelSelect;
import gui.GuiMainMenu;
import gui.GuiPauseMenu;
import gui.OverheadText;

public class Main {
	
	public static int width = 800, height = 600;
	public static String version = "1.0";
	public static boolean running = true;
	public static int mouseX, mouseY;
	public static int levelID = 1;
	public static int clock = 0;
	public static boolean isPaused = false;
	
	EntityPlayerForeground playerF = new EntityPlayerForeground();
	EntityPlayerBackground playerB = new EntityPlayerBackground();
	EntityActivatable entityActivatable = new EntityActivatable();
	Timing timer = new Timing();
	Warp warper = new Warp();
	
	public Main() throws Exception {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setResizable(false);
			Display.setTitle("JavaHub GameJam version: " + version);
			Display.setVSyncEnabled(true);
			Display.create();
		}catch(LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		Mouse.setGrabbed(true);
		new FontLoader();
		timer.getDelta();
		timer.lastFPS = timer.getTime();
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, 0, height, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		while(running) {
			glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT);
			
			timer.updateFPS();
			
			/** BEGIN RENDER **/
			
			FontLoader.drawCenteredString(40, height - 20, "FPS:" + timer.printedFPS);
			
			switch(GameStates.state) {
				case LOADING:
					new LoadingDisplay();
					break;
					
				case MENU_MAIN:
					new GuiMainMenu();
					break;
					
				case MENU_ABOUT:
					new GuiAbout();
					break;
					
				case MENU_LEVEL_SELECT:
					new GuiLevelSelect();
					break;
					
				case MENU_GAME_COMPLETE:
					new GuiGameComplete();
					break;
			
				case GAME_MAIN:
					FontLoader.drawCenteredString(width - 60, height - 20, "TIMER: " + clock);
					
					entityActivatable.moveTile(levelID);
					warper.warp();
					MapMain.loadLevel(levelID);
					new EntityPlayerForeground();
					new EntityPlayerBackground();
					new RenderPlayer(playerB.x, playerB.y, EntityPlayerBackground.class);
					new RenderPlayer(playerF.x, playerF.y, EntityPlayerForeground.class);
					new OverheadText(levelID);
					playerF.playerPhysics();
					playerB.playerPhysics();
					
					if(isPaused) new GuiPauseMenu();
					break;
			}
			
			/** END RENDER **/
			
			new Input();
			
			Display.update();
			Display.sync(90);
			
			if(Display.isCloseRequested()) {
				running = false;
			}
		}
		
		Display.destroy();
	}
	
	public static void main(String args[]) {
		try {
			GameStates.state = GameStates.state.LOADING;
			new Main();
		} catch (Exception e) {}
	}

}

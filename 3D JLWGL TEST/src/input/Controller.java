package input;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.lang.Thread.State;

import main.GameStates;
import main.GuiStates;
import main.LoadingDisplay;
import main.Main;
import main.ScreenDisplay;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector3f;

import render.RenderLightingNew;

public class Controller{
	
	public static int walkingSpeed = 15;
	public static double sprintMultiplier = 2;
	public static double crouchMultiplier = 0.5;
	public static double crouchCounter = 0;
	public static double bob = 0;
	public static double bobCounter = 0;
	public static double bobbingSpeed = 0.2;
	public static double bobbingDistance = 0.01;
	public static double jumpHeight = 0;
	public static boolean flying = false;
	
	public static double mouseSpeed = 1.0;
	public static int maxLookUp = 90;
	public static int maxLookDown = -90;
	
	private int fov = Main.fov;
	
	public Controller() {
		
		boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP) || Keyboard.isKeyDown(Keyboard.KEY_W) && GuiStates.state != GuiStates.state.PAUSE;
        boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN) || Keyboard.isKeyDown(Keyboard.KEY_S) && GuiStates.state != GuiStates.state.PAUSE;
        boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_A) && GuiStates.state != GuiStates.state.PAUSE;
        boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT) || Keyboard.isKeyDown(Keyboard.KEY_D) && GuiStates.state != GuiStates.state.PAUSE;
        boolean jump = Keyboard.isKeyDown(Keyboard.KEY_SPACE) && GuiStates.state != GuiStates.state.PAUSE;
        boolean fly = Keyboard.isKeyDown(Keyboard.KEY_C) && GuiStates.state != GuiStates.state.PAUSE;
        boolean sprint = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && GuiStates.state != GuiStates.state.PAUSE;
        boolean crouch = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && GuiStates.state != GuiStates.state.PAUSE;
        
        int delta = Main.getDelta();
        
        if(sprint) {
        	walkingSpeed *= sprintMultiplier;
        	bobbingSpeed *= sprintMultiplier;
        	bobbingDistance *= sprintMultiplier;
        }
        
        /*if(fly) {
        	if(flying) {
        		flying = false;
        		System.out.println("Flying disabled");
        	}else{
        		flying = true;
        		System.out.println("Flying enabled");
        	}
        }*/
        
        if(jump) {
        	if(flying) {
        		Main.position.y -= (walkingSpeed * 0.0002f) * delta;
        	}
        }
        
        if(crouch) {
        	if(flying) Main.position.y += (walkingSpeed * 0.0002f) * delta;
        		bobbingSpeed *= crouchMultiplier;
        		bobbingDistance *= crouchMultiplier;
        		walkingSpeed *= crouchMultiplier;
        	if(!flying) {
        		crouchCounter++;
        		if(crouchCounter <= 5) {
        			Main.position.y += 0.05;
        		}
        		if(crouchCounter > 5) {
        			crouchCounter = 5;
        		}
        	}
        }
        if(!crouch){
        	if(crouchCounter >= 5) {
        		Main.position.y -= 0.05;
        		crouchCounter++;
        	}
        	if(crouchCounter >= 10) {
        		crouchCounter = 0;
        	}
        }
        
        if (keyUp && keyRight && !keyLeft && !keyDown) {
            float angle = Main.rotation.y + 45;
            Vector3f newPosition = new Vector3f(Main.position);
            float oblique = (walkingSpeed * 0.0002f) * delta;
            float adjacent = oblique * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * oblique);
            newPosition.z += adjacent;
            newPosition.x -= opposite;
            Main.position.z = newPosition.z;
            Main.position.x = newPosition.x;
        }
        if (keyUp && keyLeft && !keyRight && !keyDown) {
            float angle = Main.rotation.y - 45;
            Vector3f newPosition = new Vector3f(Main.position);
            float oblique = (walkingSpeed * 0.0002f) * delta;
            float adjacent = oblique * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * oblique);
            newPosition.z += adjacent;
            newPosition.x -= opposite;
            Main.position.z = newPosition.z;
            Main.position.x = newPosition.x;
        }
        if (keyUp && !keyLeft && !keyRight && !keyDown) {
            float angle = Main.rotation.y;
            Vector3f newPosition = new Vector3f(Main.position);
            float oblique = (walkingSpeed * 0.0002f) * delta;
            float adjacent = oblique * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * oblique);
            newPosition.z += adjacent;
            newPosition.x -= opposite;
            Main.position.z = newPosition.z;
            Main.position.x = newPosition.x;
        }
        if (keyDown && keyLeft && !keyRight && !keyUp) {
            float angle = Main.rotation.y - 135;
            Vector3f newPosition = new Vector3f(Main.position);
            float oblique = (walkingSpeed * 0.0002f) * delta;
            float adjacent = oblique * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * oblique);
            newPosition.z += adjacent;
            newPosition.x -= opposite;
            Main.position.z = newPosition.z;
            Main.position.x = newPosition.x;
        }
        if (keyDown && keyRight && !keyLeft && !keyUp) {
            float angle = Main.rotation.y + 135;
            Vector3f newPosition = new Vector3f(Main.position);
            float oblique = (walkingSpeed * 0.0002f) * delta;
            float adjacent = oblique * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * oblique);
            newPosition.z += adjacent;
            newPosition.x -= opposite;
            Main.position.z = newPosition.z;
            Main.position.x = newPosition.x;
        }
        if (keyDown && !keyUp && !keyLeft && !keyRight) {
            float angle = Main.rotation.y;
            Vector3f newPosition = new Vector3f(Main.position);
            float oblique = -(walkingSpeed * 0.0002f) * delta;
            float adjacent = oblique * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * oblique);
            newPosition.z += adjacent;
            newPosition.x -= opposite;
            Main.position.z = newPosition.z;
            Main.position.x = newPosition.x;
        }
        if (keyLeft && !keyRight && !keyUp && !keyDown) {
            float angle = Main.rotation.y - 90;
            Vector3f newPosition = new Vector3f(Main.position);
            float oblique = (walkingSpeed * 0.0002f) * delta;
            float adjacent = oblique * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * oblique);
            newPosition.z += adjacent;
            newPosition.x -= opposite;
            Main.position.z = newPosition.z;
            Main.position.x = newPosition.x;
        }
        if (keyRight && !keyLeft && !keyUp && !keyDown) {
            float angle = Main.rotation.y + 90;
            Vector3f newPosition = new Vector3f(Main.position);
            float oblique = (walkingSpeed * 0.0002f) * delta;
            float adjacent = oblique * (float) Math.cos(Math.toRadians(angle));
            float opposite = (float) (Math.sin(Math.toRadians(angle)) * oblique);
            newPosition.z += adjacent;
            newPosition.x -= opposite;
            Main.position.z = newPosition.z;
            Main.position.x = newPosition.x;
        }
        
        if(keyRight || keyLeft || keyUp || keyDown) {
        	if(!flying) {
        		bobCounter += bobbingSpeed;
            	bob = Math.cos(bobCounter) * bobbingDistance; //0.01 = vertical bobbing distance
            	Main.position.y -= bob;
        	}
        }
        
        if(sprint) {
        	walkingSpeed /= sprintMultiplier;
        	bobbingSpeed /= sprintMultiplier;
        	bobbingDistance /= sprintMultiplier;
        }
        
        if(crouch) {
        	bobbingSpeed /= crouchMultiplier;
        	bobbingDistance /= crouchMultiplier;
        	walkingSpeed /= crouchMultiplier;
        }
        
        if (Mouse.isGrabbed() && LoadingDisplay.isLoaded) {
            float mouseDX = (float) (Mouse.getDX() * mouseSpeed * 0.16f);
            float mouseDY = (float) (Mouse.getDY() * mouseSpeed * 0.16f);
            if (Main.rotation.y + mouseDX >= 360) {
                Main.rotation.y = Main.rotation.y + mouseDX - 360;
            } else if (Main.rotation.y + mouseDX < 0) {
                Main.rotation.y = 360 - Main.rotation.y + mouseDX;
            } else {
                Main.rotation.y += mouseDX;
            }
            if (Main.rotation.x - mouseDY >= maxLookDown && Main.rotation.x - mouseDY <= maxLookUp) {
                Main.rotation.x += -mouseDY;
            } else if (Main.rotation.x - mouseDY < maxLookDown) {
                Main.rotation.x = maxLookDown;
            } else if (Main.rotation.x - mouseDY > maxLookUp) {
                Main.rotation.x = maxLookUp;
            }
        }
        
        while(Keyboard.next()) {
        	if(Keyboard.isKeyDown(Keyboard.KEY_C)) {
        		if(flying) { 
        			flying = false;
        			Main.position.y = -0.5f;
        			System.out.println("FLYING DISABLED");
        		}else{
        			flying = true;
        			System.out.println("FLYING ENABLED");
        		}
        	}
        	if(Keyboard.isKeyDown(Keyboard.KEY_F11)) {
        		 try {
        			 if (!Display.isFullscreen()) {
        				 Display.setResizable(false);
                         Display.setDisplayModeAndFullscreen(ScreenDisplay.fullscreenDisplayMode);
                         glViewport(0, 0, ScreenDisplay.fullscreenDisplayMode.getWidth(), ScreenDisplay.fullscreenDisplayMode.getHeight());
                         glMatrixMode(GL_PROJECTION);
                         glLoadIdentity();
                         gluPerspective(fov, (float) ScreenDisplay.fullscreenDisplayMode.getWidth() / (float) ScreenDisplay.fullscreenDisplayMode.getHeight(), 0.001f, 100);
                         glMatrixMode(GL_MODELVIEW);
                         glLoadIdentity();
                     } else {
                    	 Display.setResizable(false);
                         Display.setDisplayMode(ScreenDisplay.windowedDisplayMode);
                         glViewport(0, 0, ScreenDisplay.windowedDisplayMode.getWidth(), ScreenDisplay.windowedDisplayMode.getHeight());
                         glMatrixMode(GL_PROJECTION);
                         glLoadIdentity();
                         gluPerspective(fov, (float) ScreenDisplay.windowedDisplayMode.getWidth() / (float) ScreenDisplay.windowedDisplayMode.getHeight(), 0.001f, 100);
                         glMatrixMode(GL_MODELVIEW);
                         glLoadIdentity();
                     }
                 } catch (LWJGLException ex) {}
        	}
        	if(Keyboard.isKeyDown(Keyboard.KEY_F)) {
        		Main.polygonMode++;
        		if(Main.polygonMode > 2) Main.polygonMode = 0;
        	}
        	if(Keyboard.isKeyDown(Keyboard.KEY_G)) {
        		//Main.lightPosition = new Vector3f(-Main.position.x, -Main.position.y, -Main.position.z);
        		RenderLightingNew.lightPosition.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
        	}
        	if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
        		switch(GuiStates.state) {
        			case PAUSE:
        				Mouse.setGrabbed(true);
        				GuiStates.state = GuiStates.state.HUD;
        			break;
        			case HUD:
        				Mouse.setGrabbed(false);
        				Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
        				GuiStates.state = GuiStates.state.PAUSE;
        			break;
        		}
        	}
        }
	}

}

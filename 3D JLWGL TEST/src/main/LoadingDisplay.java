package main;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import render.FontLoader;

public class LoadingDisplay {
	
	private static int loadingTick = 0;
	public static boolean isLoaded = false;

	public LoadingDisplay() {
		if(!isLoaded) {
			loadingTick();
			glCallList(drawLoadingBar());
		}
	}
	
	public static int drawLoadingBar() {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			glColor4f(0, 0, 0, 1);
			glVertex2f(0, 0);
			glVertex2f(0, Display.getHeight());
			glVertex2f(Display.getWidth(), Display.getHeight());
			glVertex2f(Display.getWidth(), 0);
		glEnd();
		glBegin(GL_QUADS);
			glColor4f(0, 1, 0, 1);
			glVertex2f(Display.getWidth() / 2 - 200, Display.getHeight() / 2 - 2);
			glVertex2f(Display.getWidth() / 2 - 200, Display.getHeight() / 2 + 2);
			glVertex2f(Display.getWidth() / 2 - 200 + loadingTick()*2, Display.getHeight() / 2 + 2);
			glVertex2f(Display.getWidth() / 2 - 200 + loadingTick()*2, Display.getHeight() / 2 - 2);
		glEnd();
		glEndList();
		return displayList;
	}
	
	public static int loadingTick() {
		loadingTick += 1;
		if(loadingTick == 200 && !isLoaded) {
			isLoaded = true;
			GameStates.state = GameStates.state.MENU_MAIN;
			Mouse.setGrabbed(false);
		}
		return loadingTick;
	}
	
}

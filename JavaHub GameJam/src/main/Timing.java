package main;

import org.lwjgl.Sys;

import render.FontLoader;

public class Timing {
	
	private long lastFrame;
	private int fps;
	long lastFPS;
	public static int printedFPS;
	
	public long getTime() {
		return System.nanoTime() / 1000000;
	}
	
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		
		return delta;
	}
	
	public void updateFPS() {
		if(getTime() - lastFPS > 1000) {
			printedFPS = fps;
			fps = 0;
			lastFPS += 1000;
			
			if(GameStates.state == GameStates.state.GAME_MAIN) {
				Main.clock++;
			}
		}
		fps++;
	}

}

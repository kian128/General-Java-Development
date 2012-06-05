package gui;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.util.glu.GLU.gluPerspective;
import main.GameStates;
import main.GuiStates;
import main.Main;
import main.ScreenDisplay;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import render.RenderFog;

public class GuiWorldSelect {
	
	public GuiWorldSelect() {
		new GuiButton(0, Display.getWidth() / 2, Display.getHeight() / 2 - 40, 180, 30, "DUNGEON CRAWLER", this.getClass());
		new GuiButton(1, Display.getWidth() / 2, Display.getHeight() / 2 + 00, 180, 30, "BLENDER MODEL/LIGHTING TEST", this.getClass());
		new GuiButton(2, Display.getWidth() / 2, Display.getHeight() / 2 + 40, 180, 30, "TERRAIN/HEIGHTMAP TEST", this.getClass());
	}
	
	public void actionPerformed(int id) {
		if(id == 0) {
			GuiStates.state = GuiStates.state.HUD;
			GameStates.state = GameStates.state.GAME_DUNGEON_CRAWLER;
			new RenderFog(Color.black, 1, 20);
			Mouse.setGrabbed(true);
		}
		if(id == 1) {
			GameStates.state = GameStates.state.GAME_MODEL_TEST;
			GuiStates.state = GuiStates.state.HUD;
			Mouse.setGrabbed(true);
		}
		if(id == 2) {
			GameStates.state = GameStates.state.GAME_TERRAIN_TEST;
			GuiStates.state = GuiStates.state.HUD;
			new RenderFog(Color.cyan, 100, 200);
			Mouse.setGrabbed(true);
		}
	}

}

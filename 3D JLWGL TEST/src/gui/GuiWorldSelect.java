package gui;

import main.GameStates;
import main.GuiStates;
import main.ScreenDisplay;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

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
			Mouse.setGrabbed(true);
		}
	}

}

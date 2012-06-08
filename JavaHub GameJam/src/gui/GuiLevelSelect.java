package gui;

import main.GameStates;
import main.Main;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class GuiLevelSelect {
	
	public GuiLevelSelect() {
		new GuiButton(0, Display.getWidth() / 2, Display.getHeight() / 2 - 200, 120, 30, "RETURN", this.getClass());
		new GuiButton(1, Display.getWidth() / 2 - 80, Display.getHeight() / 2 + 40, 40, 30, "1", this.getClass());
		new GuiButton(2, Display.getWidth() / 2 - 00, Display.getHeight() / 2 + 40, 40, 30, "2", this.getClass());
		new GuiButton(3, Display.getWidth() / 2 + 80, Display.getHeight() / 2 + 40, 40, 30, "3", this.getClass());
		new GuiButton(4, Display.getWidth() / 2 - 80, Display.getHeight() / 2 - 00, 40, 30, "4", this.getClass());
		new GuiButton(5, Display.getWidth() / 2 - 00, Display.getHeight() / 2 - 00, 40, 30, "5", this.getClass());
		new GuiButton(6, Display.getWidth() / 2 + 80, Display.getHeight() / 2 - 00, 40, 30, "6", this.getClass());
	}
	
	public void actionPerformed(int id) {
		if(id == 0) {
			GameStates.state = GameStates.state.MENU_MAIN;
		}
		if(id > 0) {
			Main.levelID = id;
			GameStates.state = GameStates.state.GAME_MAIN;
			Mouse.setGrabbed(true);
		}
	}
}
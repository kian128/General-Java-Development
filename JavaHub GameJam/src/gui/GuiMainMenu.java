package gui;

import main.GameStates;
import main.Main;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class GuiMainMenu {
	
	public GuiMainMenu() {
		new GuiButton(0, Display.getWidth() / 2, Display.getHeight() / 2 + 40, 120, 30, "PLAY", this.getClass());
		new GuiButton(1, Display.getWidth() / 2, Display.getHeight() / 2 + 0, 120, 30, "LEVEL SELECT", this.getClass());
		new GuiButton(2, Display.getWidth() / 2, Display.getHeight() / 2 - 40, 120, 30, "ABOUT", this.getClass());
		new GuiButton(3, Display.getWidth() / 2, Display.getHeight() / 2 - 80, 120, 30, "EXIT", this.getClass());
	}
	
	public void actionPerformed(int id) {
		if(id == 0) {
			GameStates.state = GameStates.state.GAME_MAIN;
			Mouse.setGrabbed(true);
		}
		if(id == 1) {
			GameStates.state = GameStates.state.MENU_LEVEL_SELECT;
		}
		if(id == 2) {
			GameStates.state = GameStates.state.MENU_ABOUT;
		}
		if(id == 3) {
			System.exit(0);
		}
	}
}

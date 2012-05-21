package gui;

import main.GameStates;
import main.Main;

import org.lwjgl.opengl.Display;

import render.FontLoader;

public class GuiAbout {
	
	public GuiAbout() {
		new GuiButton(0, Display.getWidth() / 2, Display.getHeight() / 2 + 120, 120, 30, "RETURN", this.getClass());
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 - 40, "Author: Kian Bennett");
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 + 40, "Version: " + Main.version);
	}
	
	public void actionPerformed(int id) {
		if(id == 0) {
			GameStates.state = GameStates.state.MENU_MAIN;
		}
	}

}

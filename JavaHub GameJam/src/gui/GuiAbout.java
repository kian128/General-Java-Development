package gui;

import main.GameStates;
import main.Main;

import org.lwjgl.opengl.Display;

import render.FontLoader;

public class GuiAbout {
	
	public GuiAbout() {
		
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 + 120, "In 2087, scientists had expanded experimental research far across the universe,");
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 + 100, "unravelling the mysteries of Black Holes, Quantum Foam, String theory,");
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 + 80, "and Wormholes. Needless to say the corporate world wanted in on this, and a");
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 + 60, "synthetic, domesticated, sustainable Wormhole was put into development.");
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 + 20, "And so the testing began.");		
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 - 40, "Author: Kian Bennett");
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 - 80, "Version: " + Main.version);
		new GuiButton(0, Display.getWidth() / 2, Display.getHeight() / 2 - 120, 120, 30, "RETURN", this.getClass());
	}
	
	public void actionPerformed(int id) {
		if(id == 0) {
			GameStates.state = GameStates.state.MENU_MAIN;
		}
	}

}

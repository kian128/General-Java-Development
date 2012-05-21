package gui;

import main.GameStates;
import main.GuiStates;
import main.Main;
import main.ScreenDisplay;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class GuiMainMenu {
	
	public GuiMainMenu() {
		new GuiButton(0, Display.getWidth() / 2, Display.getHeight() / 2 - 40, 120, 30, "PLAY", this.getClass());
		new GuiButton(2, Display.getWidth() / 2, Display.getHeight() / 2 + 0, 120, 30, "ABOUT", this.getClass());
		new GuiButton(3, Display.getWidth() / 2, Display.getHeight() / 2 + 40, 120, 30, "EXIT", this.getClass());
	}
	
	public void actionPerformed(int id) {
		if(id == 0) {
			GameStates.state = GameStates.state.MENU_WORLD_SELECT;
		}
		if(id == 2) {
			GameStates.state = GameStates.state.MENU_ABOUT;
		}
		if(id == 3) {
			Main.cleanUp();
			System.exit(0);
		}
	}

}

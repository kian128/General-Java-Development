package gui;

import main.GameStates;
import main.Main;

import org.lwjgl.opengl.Display;

import render.FontLoader;

public class GuiGameComplete {
	
	public GuiGameComplete() {
		
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 + 80, "Congratulations! I hope you enjoyed, however briefly,");
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 + 60, "my entry to the first JavaHub Game Jam");
		
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 + 20, "For now there are only 6 levels due to time");
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 + 00, "contraints, and I may or finish it (any updates");
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 - 20, "will be uploaded to my website, www.kianbennett.co.uk)");
		
		FontLoader.drawCenteredString(Display.getWidth() / 2, Display.getHeight() / 2 - 60, "Thanks for playing!");
		new GuiButton(0, Display.getWidth() / 2, Display.getHeight() / 2 - 120, 120, 30, "RETURN", this.getClass());
	}
	
	public void actionPerformed(int id) {
		if(id == 0) {
			GameStates.state = GameStates.state.MENU_MAIN;
		}
	}

}

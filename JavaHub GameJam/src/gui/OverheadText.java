package gui;

import main.Main;
import render.FontLoader;

public class OverheadText {
	
	public OverheadText(int levelID) {
		if(levelID == 1) {
			FontLoader.drawCenteredString(Main.width / 2, Main.height - 100, "Move each cube with the direction arrows and WASD");
			FontLoader.drawCenteredString(Main.width / 2, Main.height - 130, "Reach the gate at the end to complete the area");
		}
		if(levelID == 2) {
			FontLoader.drawCenteredString(Main.width / 2, Main.height - 100, "Use 'UP' and 'W' to launch each cube into the air");
		}
		if(levelID == 3) {
			FontLoader.drawCenteredString(Main.width / 2, Main.height - 100, "Cubes cannot pass through their corresponding tiles");
			FontLoader.drawCenteredString(Main.width / 2, Main.height - 130, "Use 'Q' and 'E' to warp through the Synthetic Wormhole");
		}
		if(levelID == 4) {
			FontLoader.drawCenteredString(Main.width / 2, Main.height - 100, "Pressure pads can be used to activate certain tiles");
		}
	}

}

package orthographic;

import org.lwjgl.opengl.Display;

public class GUIPaused {
	
	public GUIPaused() {
		GUIButton guiButton = new GUIButton();
		guiButton.drawButton(Display.getWidth() / 2, Display.getHeight() / 2 - 60, 120, 30, "RESUME");
		guiButton.drawButton(Display.getWidth() / 2, Display.getHeight() / 2 + 60, 120, 30, "EXIT");
	}

}

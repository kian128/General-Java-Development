package gui;

import main.GameStates;
import main.GuiStates;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import render.RenderFog;

public class GuiPauseMenu {
	
	public GuiPauseMenu() {
		new GuiButton(0, Display.getWidth() / 2, Display.getHeight() / 2 - 40, 200, 40, "RESUME", this.getClass());
		new GuiButton(1, Display.getWidth() / 2, Display.getHeight() / 2 + 40, 200, 40, "EXIT", this.getClass());
	}
	
	public void actionPerformed(int id) {
		if(id == 0) {
			GuiStates.state = GuiStates.state.HUD;
			Mouse.setGrabbed(true);
		}
		if(id == 1) {
			new RenderFog(Color.black, 0, 1);
			GameStates.state = GameStates.state.MENU_MAIN;
			GuiStates.state = GuiStates.state.NULL;
			Mouse.setGrabbed(false);
		}
	}
}

package gui;

import main.GameStates;
import main.Main;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import entity.EntityActivatable;

import static org.lwjgl.opengl.GL11.*;

public class GuiPauseMenu {
	
	EntityActivatable entityActivatable = new EntityActivatable();
	
	public GuiPauseMenu() {
		new GuiButton(0, Display.getWidth() / 2, Display.getHeight() / 2 + 40, 120, 30, "RESUME", this.getClass());
		new GuiButton(1, Display.getWidth() / 2, Display.getHeight() / 2 - 00, 120, 30, "RESTART", this.getClass());
		new GuiButton(2, Display.getWidth() / 2, Display.getHeight() / 2 - 40, 120, 30, "EXIT", this.getClass());
		
	}
	
	public void actionPerformed(int id) {
		if(id == 0) {
			Mouse.setGrabbed(true);
			Main.isPaused = false;
		}
		if(id == 1) {
			entityActivatable.resetPlayerPositions();
			Main.clock = 0;
			Main.isPaused = false;
		}
		if(id == 2) {
			Mouse.setGrabbed(false);
			Main.isPaused = false;
			GameStates.state = GameStates.state.MENU_MAIN;
			entityActivatable.resetPlayerPositions();
			entityActivatable.moveableTileIsMoving = false;

		}
	}
}

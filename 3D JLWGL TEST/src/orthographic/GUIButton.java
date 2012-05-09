package orthographic;

import static org.lwjgl.opengl.GL11.*;

import java.lang.reflect.Method;

import launcher.Launcher;
import main.Main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

public class GUIButton {
	
	public static void drawButton(int x, int y, int width, int height, String s) {
		glDisable(GL_DEPTH_TEST);
		glBegin(GL_QUADS);
			glColor4f(1f, 0.0f, 0.0f, 1f);
			glVertex2f(x - width/2, y - height/2);
			glVertex2f(x - width/2, y + height/2);
			glVertex2f(x + width/2, y + height/2);
			glVertex2f(x + width/2, y - height/2);
			glColor4f(1f, 1f, 1f, 1f);
		glEnd();
		FontLoader.drawCenteredString(x, y, s);
		glEnable(GL_DEPTH_TEST);
		if(Mouse.isButtonDown(0) && Mouse.getX() > x - width/2 && Mouse.getX() < x + width/2 && Mouse.getY() > y - height/2 && Mouse.getY() < y + height/2) {
			while(Mouse.next()) {
				if(s == "EXIT") {
					Main.isPaused = false;
					Mouse.setGrabbed(true);
        			Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
				}
				if(s == "RESUME") {
					System.exit(0);
				}
			}
		}
	}

}

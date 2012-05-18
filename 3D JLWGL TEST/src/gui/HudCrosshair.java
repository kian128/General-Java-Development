package gui;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;

public class HudCrosshair {
	
	public HudCrosshair(int x, int y) {
		glCallList(draw(x, y));
	}
	
	public static int draw(int x, int y) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glColor4f(0, 0, 0, 1);
		glBegin(GL_LINES);
			glVertex2f(x - 10, y);
			glVertex2f(x + 10, y);
		glEnd();
		glBegin(GL_LINES);
			glVertex2f(x, y - 10);
			glVertex2f(x, y + 10);
		glEnd();
		glEndList();
		glColor4f(1, 1, 1, 1);
		return displayList;
	}
}

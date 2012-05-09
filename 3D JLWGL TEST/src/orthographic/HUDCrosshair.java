package orthographic;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;

public class HUDCrosshair {
	
	public HUDCrosshair(int x, int y) {
		glColor4f(0, 0, 0, 0.5f);
		glBegin(GL_LINES);
			glVertex2f(x - 10, y);
			glVertex2f(x + 10, y);
		glEnd();
		glBegin(GL_LINES);
			glVertex2f(x, y - 10);
			glVertex2f(x, y + 10);
		glEnd();
	}

}

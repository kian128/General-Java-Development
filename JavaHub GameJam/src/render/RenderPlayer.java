package render;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;

import entity.EntityPlayerBackground;
import entity.EntityPlayerForeground;

public class RenderPlayer {
	
	EntityPlayerForeground playerF = new EntityPlayerForeground();
	EntityPlayerBackground playerB = new EntityPlayerBackground();
	
	public RenderPlayer(int x, int y, Class entity) {
		glCallList(linkDisplayList());
		glCallList(playerDisplayList(x, y, entity));
	}
	
	private int playerDisplayList(int x, int y, Class entity) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			if(entity == EntityPlayerForeground.class) {
				glColor3f(1, 1, 1);
			}
			if(entity == EntityPlayerBackground.class) {
				glColor3f(0.5f, 0.5f, 0.5f);
			}
			glVertex2f(x, y);
			glVertex2f(x, y + playerF.height);
			glVertex2f(x + playerF.width, y + playerF.height);
			glVertex2f(x + playerF.width, y);
			glColor3f(1, 1, 1);
		glEnd();
		glEndList();
		return displayList;
	}
	
	private int linkDisplayList() {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_LINES);
			glVertex2f(playerF.x + playerF.width / 2, playerF.y + playerF.height / 2);
			glVertex2f(playerB.x + playerB.width / 2, playerB.y + playerB.height / 2);
		glEnd();
		glEndList();
		return displayList;
	}
}

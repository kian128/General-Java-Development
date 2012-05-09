package world;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glVertex3f;
import main.Main;

public class LoadWall {
	
	public static int loadWallStandardSouth(int x, int y, int z, int width, int height) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex3f(x + width, y, z);
			glTexCoord2f(0, 1);
			glVertex3f(x + width, y + height, z);
			glTexCoord2f(1, 1);
			glVertex3f(x + width, y + height, z + width);
			glTexCoord2f(1, 0);
			glVertex3f(x + width, y, z + width);
		glEnd();
		glEndList();
		return displayList;
	}
	
	public static int loadWallStandardNorth(int x, int y, int z, int width, int height) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex3f(x, y, z);
			glTexCoord2f(0, 1);
			glVertex3f(x, y + height, z);
			glTexCoord2f(1, 1);
			glVertex3f(x, y + height, z + width);
			glTexCoord2f(1, 0);
			glVertex3f(x, y, z + width);
		glEnd();
		glEndList();
		return displayList;
	}
	
	public static int loadWallStandardWest(int x, int y, int z, int width, int height) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex3f(x, y, z);
			glTexCoord2f(0, 1);
			glVertex3f(x, y + height, z);
			glTexCoord2f(1, 1);
			glVertex3f(x + width, y + height, z);
			glTexCoord2f(1, 0);
			glVertex3f(x + width, y, z);
		glEnd();
		glEndList();
		return displayList;
	}
	
	public static int loadWallStandardEast(int x, int y, int z, int width, int height) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex3f(x, y, z + width);
			glTexCoord2f(0, 1);
			glVertex3f(x, y + height, z + width);
			glTexCoord2f(1, 1);
			glVertex3f(x + width, y + height, z + width);
			glTexCoord2f(1, 0);
			glVertex3f(x + width, y, z + width);
		glEnd();
		glEndList();
		return displayList;
	}
}

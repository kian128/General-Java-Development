package world;

import static org.lwjgl.opengl.GL11.*;
import input.Controller;
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
			if(-Main.position.x <= x + width + 0.2 && -Main.position.x >= x + width && -Main.position.z >= z && -Main.position.z <= z + width && -Main.position.y >= y && -Main.position.y <= y + height + 1) {
				Main.position.x = -(x + width + 0.2f);
			}
			if(-Main.position.x >= x + width - 0.2 && -Main.position.x <= x + width && -Main.position.z >= z && -Main.position.z <= z + width && -Main.position.y >= y && -Main.position.y <= y + height + 1) {
				Main.position.x = -(x + width - 0.2f);
			}
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
			if(-Main.position.x <= x + 0.2 && -Main.position.x >= x && -Main.position.z >= z && -Main.position.z <= z + width && -Main.position.y >= y && -Main.position.y <= y + height + 1) {
				Main.position.x = -(x + 0.2f);
			}
			if(-Main.position.x >= x - 0.2 && -Main.position.x <= x && -Main.position.z >= z && -Main.position.z <= z + width && -Main.position.y >= y && -Main.position.y <= y + height + 1) {
				Main.position.x = -(x - 0.2f);
			}
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
			if(-Main.position.z <= z + 0.2 && -Main.position.z >= z && -Main.position.x >= x && -Main.position.x <= x + width && -Main.position.y >= y && -Main.position.y <= y + height + 1) {
				Main.position.z = -(z + 0.2f);
			}
			if(-Main.position.z >= z - 0.2 && -Main.position.z <= z && -Main.position.x >= x && -Main.position.x <= x + width && -Main.position.y >= y && -Main.position.y <= y + height + 1) {
				Main.position.z = -(z - 0.2f);
			}
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
			if(-Main.position.z <= z + width + 0.2 && -Main.position.z >= z + width && -Main.position.x >= x && -Main.position.x <= x + width && -Main.position.y >= y && -Main.position.y <= y + height + 1) {
				Main.position.z = -(z + width + 0.2f);
			}
			if(-Main.position.z >= z + width - 0.2 && -Main.position.z <= z + width && -Main.position.x >= x && -Main.position.x <= x + width && -Main.position.y >= y && -Main.position.y <= y + height + 1) {
				Main.position.z = -(z + width - 0.2f);
			}
		glEnd();
		glEndList();
		return displayList;
	}
	
	public static int loadFloor(int x, int y, int z, int width, int length, float tilesize) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex3f(x, y, z);
			glTexCoord2f(width * 5 * tilesize, 0);
			glVertex3f(x + length, y, z);
			glTexCoord2f(width * 5 * tilesize, width * 5 * tilesize);
			glVertex3f(x + length, y, z + width);
			glTexCoord2f(0, width * 5 * tilesize);
			glVertex3f(x, y, z + width);
			if(-Main.position.y <= y - 0.2 && -Main.position.y >= y && -Main.position.x >= x && -Main.position.x <= x + length && -Main.position.z >= z && -Main.position.z <= z + width) {
				Main.position.y = -(y - 0.2f);
			}
		glEnd();
		glEndList();
		return displayList;
	}
}

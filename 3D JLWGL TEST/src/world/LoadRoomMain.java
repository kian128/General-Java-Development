package world;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import main.Main;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class LoadRoomMain {

	public static float ceilingHeightFinal = 1;
	public static float floorHeightFinal = -1;
	public static int gridSizeFinal = 100;
	public static float tileSizeFinal = 0.2f;

	public static int floorDisplayList = loadFloor(gridSizeFinal, tileSizeFinal, floorHeightFinal);
	public static int ceilingDisplayList = loadCeiling(gridSizeFinal, tileSizeFinal, ceilingHeightFinal);

	public static int loadFloor(int gridSize, float tileSize, float floorHeight) {
		int floorDisplayList = glGenLists(1);
		glNewList(floorDisplayList, GL_COMPILE);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex3f(-gridSize, floorHeight, -gridSize);
		glTexCoord2f(0, gridSize * 10 * tileSize);
		glVertex3f(-gridSize, floorHeight, gridSize);
		glTexCoord2f(gridSize * 10 * tileSize, gridSize * 10 * tileSize);
		glVertex3f(gridSize, floorHeight, gridSize);
		glTexCoord2f(gridSize * 10 * tileSize, 0);
		glVertex3f(gridSize, floorHeight, -gridSize);
		glEnd();
		glEndList();
		return floorDisplayList;
	}

	public static int loadCeiling(int gridSize, float tileSize,
			float ceilingHeight) {
		int ceilingDisplayList = glGenLists(1);
		glNewList(ceilingDisplayList, GL_COMPILE);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex3f(-gridSize, ceilingHeight, -gridSize);
		glTexCoord2f(gridSize * 10 * tileSize, 0);
		glVertex3f(gridSize, ceilingHeight, -gridSize);
		glTexCoord2f(gridSize * 10 * tileSize, gridSize * 10 * tileSize);
		glVertex3f(gridSize, ceilingHeight, gridSize);
		glTexCoord2f(0, gridSize * 10 * tileSize);
		glVertex3f(-gridSize, ceilingHeight, gridSize);
		glEnd();
		glEndList();
		return ceilingDisplayList;
	}

	public static void loadWalls() {
		int[][] map = {
				{5, 1, 1, 1, 1, 1, 1, 6},
				{4, 0, 0, 0, 0, 0, 0, 3},
				{4, 0, 5, 1, 1, 6, 0, 3},
				{4, 0, 4, 0, 0, 3, 0, 3},
				{4, 0, 4, 0, 0, 3, 0, 3},
				{4, 0, 4, 0, 0, 3, 0, 3},
				{4, 0, 4, 0, 0, 3, 0, 3},
				{4, 0, 4, 0, 0, 3, 0, 3},
				{4, 0, 4, 0, 0, 3, 0, 3},
				{4, 0, 8, 2, 2, 7, 0, 3},
				{4, 0, 0, 0, 0, 0, 0, 3},
				{8, 2, 2, 2, 2, 2, 2, 7},
		};
		for (int x = 0; x < map.length; x++) {
		       for (int z = 0; z < map[x].length; z++) {
		           if(map[x][z] == 1) glCallList(LoadWall.loadWallStandardNorth(x * 2, -1, z * 2, 2, 2));
		           if(map[x][z] == 2) glCallList(LoadWall.loadWallStandardSouth(x * 2, -1, z * 2, 2, 2));
		           if(map[x][z] == 3) glCallList(LoadWall.loadWallStandardEast(x * 2, -1, z * 2, 2, 2));
		           if(map[x][z] == 4) glCallList(LoadWall.loadWallStandardWest(x * 2, -1, z * 2, 2, 2));
		           if(map[x][z] == 5) {
		        	   glCallList(LoadWall.loadWallStandardNorth(x * 2, -1, z * 2, 2, 2));
		               glCallList(LoadWall.loadWallStandardWest(x * 2, -1, z * 2, 2, 2));
		           }
		           if(map[x][z] == 6) { 
		        	   glCallList(LoadWall.loadWallStandardNorth(x * 2, -1, z * 2, 2, 2));
		           	   glCallList(LoadWall.loadWallStandardEast(x * 2, -1, z * 2, 2, 2));
		           }
		           if(map[x][z] == 7) {
		        	   glCallList(LoadWall.loadWallStandardEast(x * 2, -1, z * 2, 2, 2));
		           	   glCallList(LoadWall.loadWallStandardSouth(x * 2, -1, z * 2, 2, 2));
		           }
		           if(map[x][z] == 8) {
		        	   glCallList(LoadWall.loadWallStandardSouth(x * 2, -1, z * 2, 2, 2));
		           	   glCallList(LoadWall.loadWallStandardWest(x * 2, -1, z * 2, 2, 2));
		           }
		           if(Main.position.x == x && Main.position.z == z) {
		        	   System.out.println("COLLISION DETECTED");
		           }
		       }
		   }
	}
}

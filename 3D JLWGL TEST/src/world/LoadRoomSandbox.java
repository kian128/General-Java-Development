package world;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class LoadRoomSandbox {
	
	public static float ceilingHeightFinal = 10;
	public static float floorHeightFinal = -1;
	public static int gridSizeFinal = 10;
	public static float tileSizeFinal = 0.2f;
	
	public static int ceilingDisplayList = loadCeiling(gridSizeFinal, tileSizeFinal, ceilingHeightFinal);
	public static int floorDisplayList = loadFloor(gridSizeFinal, tileSizeFinal, floorHeightFinal);
	public static int wallDisplayList = loadWalls(gridSizeFinal, tileSizeFinal, ceilingHeightFinal, floorHeightFinal);
	
	public static int loadCeiling(int gridSize, float tileSize, float ceilingHeight) {
		int ceilingDisplayList = glGenLists(1);
        glNewList(ceilingDisplayList, GL_COMPILE);
        glBegin(GL_QUADS);
        	glTexCoord2f(0, 0);
        	glVertex3f(-gridSize, ceilingHeight, -gridSize);
        	glTexCoord2f(gridSize * gridSize * tileSize, 0);
        	glVertex3f(gridSize, ceilingHeight, -gridSize);
        	glTexCoord2f(gridSize * gridSize * tileSize, gridSize * gridSize * tileSize);
        	glVertex3f(gridSize, ceilingHeight, gridSize);
        	glTexCoord2f(0, gridSize * gridSize * tileSize);
        	glVertex3f(-gridSize, ceilingHeight, gridSize);
        glEnd();
        glEndList();
        
        return ceilingDisplayList;
	}
	
	public static int loadFloor(int gridSize, float tileSize, float floorHeight) {
		int floorDisplayList = glGenLists(1);
        glNewList(floorDisplayList, GL_COMPILE);
        glBegin(GL_QUADS);
        	glTexCoord2f(0, 0);
        	glVertex3f(-gridSize, floorHeight, -gridSize);
        	glTexCoord2f(0, gridSize * gridSize * tileSize);
        	glVertex3f(-gridSize, floorHeight, gridSize);
        	glTexCoord2f(gridSize * gridSize * tileSize, gridSize * gridSize * tileSize);
        	glVertex3f(gridSize, floorHeight, gridSize);
        	glTexCoord2f(gridSize * gridSize * tileSize, 0);
        	glVertex3f(gridSize, floorHeight, -gridSize);
        glEnd();
        glEndList();
        return floorDisplayList;
	}
	
	public static int loadWalls(int gridSize, float tileSize, float ceilingHeight, float floorHeight) {
		int wallDisplayList = glGenLists(1);
        glNewList(wallDisplayList, GL_COMPILE);

		glBegin(GL_QUADS);
			/**North Wall**/
        	glTexCoord2f(0, 0);
        	glVertex3f(-gridSize, floorHeight, -gridSize);
        	glTexCoord2f(0, gridSize * gridSize * tileSize);
        	glVertex3f(gridSize, floorHeight, -gridSize);
        	glTexCoord2f(gridSize * ceilingHeight * tileSize, gridSize * gridSize * tileSize);
        	glVertex3f(gridSize, ceilingHeight, -gridSize);
        	glTexCoord2f(gridSize * ceilingHeight * tileSize, 0);
        	glVertex3f(-gridSize, ceilingHeight, -gridSize);
        	
        	/**South Wall**/
        	glTexCoord2f(0, 0);
        	glVertex3f(-gridSize, floorHeight, +gridSize);
        	glTexCoord2f(gridSize * ceilingHeight * tileSize, 0);
        	glVertex3f(-gridSize, ceilingHeight, +gridSize);
        	glTexCoord2f(gridSize * ceilingHeight * tileSize, gridSize * gridSize * tileSize);
        	glVertex3f(+gridSize, ceilingHeight, +gridSize);
        	glTexCoord2f(0, gridSize * gridSize * tileSize);
        	glVertex3f(+gridSize, floorHeight, +gridSize);
        	
        	/**East Wall**/
        	glTexCoord2f(0, 0);
        	glVertex3f(+gridSize, floorHeight, -gridSize);
        	glTexCoord2f(gridSize * gridSize * tileSize, 0);
        	glVertex3f(+gridSize, floorHeight, +gridSize);
        	glTexCoord2f(gridSize * gridSize * tileSize, gridSize * ceilingHeight * tileSize);
        	glVertex3f(+gridSize, ceilingHeight, +gridSize);
        	glTexCoord2f(0, gridSize * ceilingHeight * tileSize);
        	glVertex3f(+gridSize, ceilingHeight, -gridSize);
        	
        	/**West Wall**/
        	glTexCoord2f(0, 0);
        	glVertex3f(-gridSize, floorHeight, -gridSize);
        	glTexCoord2f(gridSize * ceilingHeight * tileSize, 0);
        	glVertex3f(-gridSize, ceilingHeight, -gridSize);
        	glTexCoord2f(gridSize * ceilingHeight * tileSize, gridSize * gridSize * tileSize);
        	glVertex3f(-gridSize, ceilingHeight, +gridSize);
        	glTexCoord2f(0, gridSize * gridSize * tileSize);
        	glVertex3f(-gridSize, floorHeight, +gridSize);
        glEnd();
        
        /*glUseProgram(GLShaders.shaderProgram);
    		glBegin(GL_TRIANGLES);
    			glVertex2f(-0.5f, -0.5f);
    			glVertex2f(+0.5f, -0.5f);
    			glVertex2f(0f, 0f);
    		glEnd();
    	glUseProgram(0);*/
        glEndList();
        return wallDisplayList;
	}
}

package render;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.util.vector.Vector3f;

public class Render3D {
	
	public static int objectDisplayList;

	public void renderCube(float posX, float posZ) {
		objectDisplayList = glGenLists(2);
        glNewList(objectDisplayList, GL_COMPILE);
        
        glBegin(GL_QUADS);
        	glColor4f(1, 0, 0, 1);
        	glVertex3f(-1f + posX, 0f, -1f + posZ);
        	glColor4f(1, 0, 0, 1);
        	glVertex3f(1f + posX, 0f, -1f + posZ);
        	glColor4f(0, 1, 0, 1);
        	glVertex3f(1f + posX, -1f, -1f + posZ);
        	glColor4f(0, 1, 0, 1);
        	glVertex3f(-1f + posX, -1f, -1f + posZ);
        	
        	glColor4f(1, 0, 0, 1);
        	glVertex3f(-1f + posX, 0f, 1f + posZ);
        	glColor4f(1, 0, 0, 1);
        	glVertex3f(1f + posX, 0f, 1f + posZ);
        	glColor4f(0, 1, 0, 1);
        	glVertex3f(1f + posX, -1f, 1f + posZ);
        	glColor4f(0, 1, 0, 1);
        	glVertex3f(-1f + posX, -1f, 1f + posZ);
        	
        	glColor4f(1, 0, 0, 1);
        	glVertex3f(-1f + posX, 0f, -1f + posZ);
        	glColor4f(1, 0, 0, 1);
        	glVertex3f(-1f + posX, 0f, 1f + posZ);
        	glColor4f(0, 1, 0, 1);
        	glVertex3f(-1f + posX, -1f, 1f + posZ);
        	glColor4f(0, 1, 0, 1);
        	glVertex3f(-1f + posX, -1f, -1f + posZ);
        	
        	glColor4f(1, 0, 0, 1);
        	glVertex3f(1f + posX, 0f, -1f + posZ);
        	glColor4f(1, 0, 0, 1);
        	glVertex3f(1f + posX, 0f, 1f + posZ);
        	glColor4f(0, 1, 0, 1);
        	glVertex3f(1f + posX, -1f, 1f + posZ);
        	glColor4f(0, 1, 0, 1);
        	glVertex3f(1f + posX, -1f, -1f + posZ);
        glEnd();
        
        glColor4f(1, 1, 1, 1);
        
        glEndList();
	}
	
	public static int renderModel(int x, int y, int z, String fileName) {
		int modelDisplayList = glGenLists(1);
        glNewList(modelDisplayList, GL_COMPILE);
        {
            RenderModel m = null;
            try {
                m = OBJLoader.loadModel(new File(fileName));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            
            glColor4f(0.1f, 0.25f, 0.35f, 1);
            
            glBegin(GL_TRIANGLES);
            for (Face face : m.faces) {
                Vector3f n1 = m.normals.get((int) face.normal.x - 1);
                glNormal3f(n1.x + x, n1.y + y, n1.z + z);
                Vector3f v1 = m.vertices.get((int) face.vertex.x - 1);
                glVertex3f(v1.x + x, v1.y + y, v1.z + z);
                Vector3f n2 = m.normals.get((int) face.normal.y - 1);
                glNormal3f(n2.x + x, n2.y + y, n2.z + z);
                Vector3f v2 = m.vertices.get((int) face.vertex.y - 1);
                glVertex3f(v2.x + x, v2.y + y, v2.z + z);
                Vector3f n3 = m.normals.get((int) face.normal.z - 1);
                glNormal3f(n3.x + x, n3.y + y, n3.z + z);
                Vector3f v3 = m.vertices.get((int) face.vertex.z - 1);
                glVertex3f(v3.x + x, v3.y + y, v3.z + z);
            }
            glEnd();
            
            glColor4f(1, 1, 1, 1);
        }
        glEndList();
        
        return modelDisplayList;
	}
	
}

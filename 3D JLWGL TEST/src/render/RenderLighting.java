package render;

import static org.lwjgl.opengl.GL11.*;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

public class RenderLighting {
	
	private float lightIntensity = 2;
	
	public void enableLighting() {
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glEnable(GL_LIGHT1);
		glEnable(GL_NORMALIZE);
		glShadeModel(GL_SMOOTH);
	}
	
	public void enableAmbientLighting() {
		float ambientColor[] = {1f, 1f, 1f, 1.0f};
		glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(ambientColor));
	}
	
	public void setLightPosition(float x, float y, float z) {
		float lightColor[] = {lightIntensity, lightIntensity, lightIntensity, 1.0f};
		float lightPos[] = {x, y, z, 1.0f};
		glLight(GL_LIGHT0, GL_DIFFUSE, asFloatBuffer(lightColor));
		glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(lightPos));
	}
	
	private static FloatBuffer asFloatBuffer(float[] values) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}
	
	public void disableLighting() {
		glDisable(GL_LIGHTING);
		glDisable(GL_LIGHT0);
		glDisable(GL_LIGHT1);
		glDisable(GL_NORMALIZE);
	}
}

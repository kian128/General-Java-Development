package render;

import static org.lwjgl.opengl.GL11.*;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

public class RenderLighting {
	
	public RenderLighting() {
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0); //8 diff lights you can use
		glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[]{0.05f, 0.05f, 0.05f, 1f})); //changes intensity of the colors of light
		glLight(GL_LIGHT0, GL_DIFFUSE, asFloatBuffer(new float[]{2.5f, 2.5f, 2.5f, 1f}));
	}
	
	public static FloatBuffer asFloatBuffer(float[] values) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}
	
	public static void setLightPosition(float x, float y, float z) {
		glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(new float[]{x, y, z, 1f}));
	}

}

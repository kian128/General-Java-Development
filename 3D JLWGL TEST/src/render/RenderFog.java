package render;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.Color;

public class RenderFog {
	
	public RenderFog(Color fogColor, float fogNear, float fogFar) {
		glEnable(GL_FOG);
		
		FloatBuffer fogColours = BufferUtils.createFloatBuffer(4);
		fogColours.put(new float[] {fogColor.r, fogColor.g, fogColor.b, fogColor.a});
		glClearColor(fogColor.r, fogColor.g, fogColor.b, fogColor.a);
		fogColours.flip();
		glFog(GL_FOG_COLOR, fogColours);
		glFogi(GL_FOG_MODE, GL_LINEAR);
		glHint(GL_FOG_HINT, GL_NICEST);
		glFogf(GL_FOG_START, fogNear);
		glFogf(GL_FOG_END, fogFar);
		glFogf(GL_FOG_DENSITY, 0.005f);
	}

}

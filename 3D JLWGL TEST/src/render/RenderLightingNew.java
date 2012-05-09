package render;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

public class RenderLightingNew {
	
	public static FloatBuffer matSpecular;
	public static FloatBuffer lightPosition;
	public static FloatBuffer whiteLight;
	public static FloatBuffer lModelAmbient;
	
	public RenderLightingNew() {
		initLightArrays();
		
		glShadeModel(GL_SMOOTH);
		glMaterial(GL_FRONT, GL_SPECULAR, matSpecular);
		glMaterialf(GL_FRONT, GL_SHININESS, 1.0f);
		
		glLight(GL_LIGHT0, GL_POSITION, lightPosition);
		glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);
		glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);
		glLightModel(GL_LIGHT_MODEL_AMBIENT, lModelAmbient);
		
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		
		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);
	}
	
	public void initLightArrays() {
		matSpecular = BufferUtils.createFloatBuffer(4);
		matSpecular.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
		
		lightPosition = BufferUtils.createFloatBuffer(4);
		lightPosition.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
		
		whiteLight = BufferUtils.createFloatBuffer(4);
		whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
		
		lModelAmbient = BufferUtils.createFloatBuffer(4);
		whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();
	}

}

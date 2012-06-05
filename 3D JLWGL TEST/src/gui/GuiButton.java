package gui;

import static org.lwjgl.opengl.GL11.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import launcher.Launcher;
import main.GameStates;
import main.GuiStates;
import main.Main;
import main.ScreenDisplay;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import render.FontLoader;
import render.RenderFog;
import render.TextureLoader;

public class GuiButton {
	
	public static int x, y, width, height;
	TextureLoader textureLoader = new TextureLoader();
	
	public GuiButton(int id, int x, int y, int width, int height, String s, Class parent) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		glDisable(GL_BLEND);
		glBindTexture(GL_TEXTURE_2D, textureLoader.loadTexture("images/button.png"));		  
		glCallList(draw(this.x, this.y, this.width, this.height));
		glBindTexture(GL_TEXTURE_2D, 0);		  
		glEnable(GL_BLEND);
		FontLoader.drawCenteredString(this.x, this.y, s);
		hit(id, parent);
	}
	
	public int draw(int x, int y, int width, int height) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(x - width/2, y + height/2);
			glTexCoord2f(0, 1);
			glVertex2f(x - width/2, y - height/2);
			glTexCoord2f(1, 1);
			glVertex2f(x + width/2, y - height/2);
			glTexCoord2f(1, 0);
			glVertex2f(x + width/2, y + height/2);
		glEnd();
		glEndList();
		return displayList;
	}
	
	public void hit(int id, Class parent) {
		if(Mouse.isButtonDown(0) && Mouse.getX() >= x - width/2 && Mouse.getX() <= x + width / 2 && Mouse.getY() <= Display.getHeight() - y + height / 2 && Mouse.getY() >= Display.getHeight() - y - height / 2) {
			while(Mouse.next()) {
				try {
					Class[] methodParameters = new Class[]{int.class};
					Object[] params = new Object[]{new Integer(id)};
					Method actionPerformed = parent.getDeclaredMethod("actionPerformed", methodParameters);
					actionPerformed.invoke(parent.newInstance(), params);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

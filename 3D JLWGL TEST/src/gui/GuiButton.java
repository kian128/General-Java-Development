package gui;

import static org.lwjgl.opengl.GL11.*;

import java.lang.reflect.Method;

import launcher.Launcher;
import main.Main;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import render.FontLoader;

public class GuiButton {
	
	public static int x, y, width, height;
	
	public GuiButton(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		glCallList(draw(this.x, this.y, this.width, this.height));
		hover();
	}
	
	public int draw(int x, int y, int width, int height) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			glColor4f(1, 1, 1, 1);
			glVertex2f(x - width/2, y - height/2);
			glVertex2f(x, y + height);
			glVertex2f(x + width, y + height);
			glVertex2f(x + width, y);
		glEnd();
		glEndList();
		return displayList;
	}
	
	public void hover() {
		while(Mouse.next()) {
			if(Mouse.isButtonDown(0) && Mouse.getX() >= x && Mouse.getX() <= x + width && Mouse.getY() <= Display.getHeight() - y && Mouse.getY() >= Display.getHeight() - y - height) {
				System.exit(0);
			}
		}
	}
	
}

package render;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import main.Main;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.ShadowEffect;

import static org.lwjgl.opengl.GL11.*;

public class FontLoader {
	
	public static UnicodeFont uFont;
	
	public FontLoader() {
		java.awt.Font awtFont = new java.awt.Font("Trebuchet MS", java.awt.Font.PLAIN, 18);
		uFont = new UnicodeFont(awtFont);
		uFont.getEffects().add(new ColorEffect(java.awt.Color.white));
		uFont.addAsciiGlyphs();
		try {
			uFont.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static void drawCenteredString(int x, int y, String s) {
		int stringWidth = uFont.getWidth(s);
		int stringHeight = uFont.getHeight(s);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Main.width, Main.height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		uFont.drawString(x - stringWidth / 2, Main.height - y - stringHeight / 2 - 3, s);
		
		glBindTexture(GL_TEXTURE_2D, 0);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Main.width, 0, Main.height, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
}
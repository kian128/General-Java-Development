package render;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.ShadowEffect;

public class FontLoader {
	
	public static UnicodeFont uFont;
	
	public FontLoader() {
		java.awt.Font awtFont = new java.awt.Font("Trebuchet MS", java.awt.Font.PLAIN, 24);
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
		uFont.drawString(x - stringWidth / 2, y - stringHeight / 2 - 3, s);
	}
}
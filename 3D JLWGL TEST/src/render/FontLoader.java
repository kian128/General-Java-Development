package render;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;

public class FontLoader {
	
	private String fontPath;
	public static UnicodeFont uFont;
	
	public FontLoader() {
		try {
			fontPath = "res/8bitoperator_jve.ttf";
			uFont = new UnicodeFont(fontPath, 24, false, false);
			uFont.addAsciiGlyphs();
			uFont.addGlyphs(400, 600);
			uFont.getEffects().add(new ColorEffect(java.awt.Color.white));
			uFont.loadGlyphs();
		}catch(SlickException e) {
			System.err.print(e);
		}
	}
	
	public static void drawCenteredString(int x, int y, String s) {
		int stringWidth = uFont.getWidth(s);
		int stringHeight = uFont.getHeight(s);
		uFont.drawString(x - stringWidth / 2, y - stringHeight / 2 - 3, s);
	}
}
package world;

import java.awt.image.BufferedImage;
import static org.lwjgl.opengl.GL11.*;
import javax.imageio.ImageIO;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Image;

import render.FontLoader;

public class LoadTerrain {
	
	public BufferedImage getBufferedImage() {
		InputStream ios = null;
		FontLoader fontLoader = null;
		BufferedImage buffer = null;
		try {
			ios = this.getClass().getResourceAsStream("heightmap.png");
			buffer = ImageIO.read(ios);
		}catch(IOException e) {
			System.err.println(e);
		}
		return buffer;
	}
	
	public int getColor(int x, int y, String color) {
		int rgb = getBufferedImage().getRGB(x, y);
		int red = (rgb & 0x00ff0000) >> 16;
		int green = (rgb & 0x0000ff00) >> 8;
		int blue = (rgb & 0x000000ff);
		if(color == "red") return red;
		if(color == "green") return green;
		if(color == "blue") return blue;
		return 0;
	}
	
	public void loadTerrain() {
		for(int x = 0; x < getBufferedImage().getWidth(); x++) {
			glBegin(GL_TRIANGLE_STRIP);
			for(int z = 0; z < getBufferedImage().getHeight(); z++) {
	            glVertex3f(x, getColor(x, z, "red"), z);
	            glVertex3f(x, getColor(x, z, "red"), z + 1);
			}	
			glEnd();
		}
	}
}

package world;

import java.awt.image.BufferedImage;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Image;

public class LoadTerrain {
	
	/**TODO: COMPLETE FILE **/
	
	private int w, l; //width, length
	private float[][] heightmap;
	private Vector3f[][] normals;
	private boolean hasComputedNormals;
	
	public LoadTerrain(int w, int l) {
		this.w = w;
		this.l = l;
		
		heightmap = new float[this.l][];
		for(int i = 0; i < l; i++) {
			heightmap[i] = new float[w];
		}
		
		normals = new Vector3f[this.l][];
		for(int i = 0; i < l; i++) {
			normals[i] = new Vector3f[w];
		}
		
		hasComputedNormals = false;
	}
	
	public int width() {
		return w;
	}
	
	public int length() {
		return l;
	}
	
	public void setHeight(int x, int z, float y) {
		heightmap[z][x] = y;
		hasComputedNormals = false;
	}
	
	public float getHeight(int x, int z) {
		return heightmap[z][x];
	}
	
	public void computeNormals() {
		if(hasComputedNormals) {
			return;
		}
	}
	
	public Vector3f getNormal(int x, int z) {
		if(!hasComputedNormals) {
			computeNormals();
		}
		return normals[z][x];
	}
}

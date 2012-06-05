package world;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glCallList;
import render.TextureLoader;

public class LoadWorldOutside {
	
	static TextureLoader textureLoader = new TextureLoader();
	
	private int[][] mapInfrastructure = MapWorldOutside.mapInfrastructure;
	private int[][] mapFloor = MapWorldOutside.mapFloor;
	private int[][][] array = { 
		{
			{0, 0, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},
		{
			{0, 0, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		},
		{
			{0, 0, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		}
	};
	
	public LoadWorldOutside() {
		glBindTexture(GL_TEXTURE_2D, textureLoader.loadTexture("images/grass.png"));
		//glCallList(LoadWall.loadFloor(-100, -1, -100, 200, 200, 0.2f));
		glBindTexture(GL_TEXTURE_2D, textureLoader.loadTexture("images/stone.png"));
		//loadMap(mapInfrastructure);
		//loadMap(mapFloor);
		arrayTest();
	}
	
	public void loadMap(int[][] array) {
		for (int x = 0; x < array.length; x++) {
		       for (int z = 0; z < array[x].length; z++) {
		    	   int offset = array[x].length / 2;
		           if(array[x][z] == 1) {
		        	   if(array == mapInfrastructure) {
		        		   glCallList(LoadWall.loadWallStandardNorth(x * 2 - offset, -1, z * 2 - offset, 2, 2));
		        	   }
		        	   if(array == mapFloor) {
		        		   //glCallList(LoadWall.loadFloor(x * 2 - offset, -1, z * 2 - offset, 2, 2, 0.2f));
		        		   glCallList(LoadWall.loadFloor(x * 2 - offset, 1, z * 2 - offset, 2, 2, 0.2f));
		        	   }
		           }
		           if(array[x][z] == 2) {
		        	   if(array == mapInfrastructure) {
		        		   glCallList(LoadWall.loadWallStandardSouth(x * 2 - offset, -1, z * 2 - offset, 2, 2));
		        	   }
		           }
		           if(array[x][z] == 3) {
		        	   if(array == mapInfrastructure) {
		        		   glCallList(LoadWall.loadWallStandardEast(x * 2 - offset, -1, z * 2 - offset, 2, 2));
		        	   }
		           }
		           if(array[x][z] == 4) {
		        	   if(array == mapInfrastructure) {
		        		   glCallList(LoadWall.loadWallStandardWest(x * 2 - offset, -1, z * 2 - offset, 2, 2));
		        	   }
		           }
		           if(array[x][z] == 5) {
		        	   if(array == mapInfrastructure) {
		        		   glCallList(LoadWall.loadWallStandardNorth(x * 2 - offset, -1, z * 2 - offset, 2, 2));
		        		   glCallList(LoadWall.loadWallStandardWest(x * 2 - offset, -1, z * 2 - offset, 2, 2));
		        	   }
		               
		           }
		           if(array[x][z] == 6) { 
		        	   if(array == mapInfrastructure) {
		        		   glCallList(LoadWall.loadWallStandardNorth(x * 2 - offset, -1, z * 2 - offset, 2, 2));
		        		   glCallList(LoadWall.loadWallStandardEast(x * 2 - offset, -1, z * 2 - offset, 2, 2));
		        	   }
		           }
		           if(array[x][z] == 7) {
		        	   if(array == mapInfrastructure) {
		        		   glCallList(LoadWall.loadWallStandardEast(x * 2 - offset, -1, z * 2 - offset, 2, 2));
			           	   glCallList(LoadWall.loadWallStandardSouth(x * 2 - offset, -1, z * 2 - offset, 2, 2));
		        	   }
		           }
		           if(array[x][z] == 8) {
		        	   if(array == mapInfrastructure) {
		        		   glCallList(LoadWall.loadWallStandardSouth(x * 2 - offset, -1, z * 2 - offset, 2, 2));
		        		   glCallList(LoadWall.loadWallStandardWest(x * 2 - offset, -1, z * 2 - offset, 2, 2));
		        	   }
		           }
		       }
		   }
	}
	
	public void loadHouse() {
		
	}
	
	public void arrayTest() {
		for(int x = 0; x < array.length; x++) {
			for(int z = 0; z < array[x].length; z++) {
				for(int y = 0; y < array[x][z].length; y++) {
					glCallList(LoadWall.loadFloor(x, y, z, 2, 2, 0.2f));
				}
			}
		}
	}
	
	public void loadTerrain() {
		
	}
}

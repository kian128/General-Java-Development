package entity;

import main.Main;
import render.RenderTile;
import render.TextureLoader;

import static org.lwjgl.opengl.GL11.*;

public class EntityActivatable {
	
	EntityTileUniversal tileU = new EntityTileUniversal();
	EntityTileForeground tileF = new EntityTileForeground();
	EntityTileBackground tileB = new EntityTileBackground();
	EntityPlayerForeground playerF = new EntityPlayerForeground();
	EntityPlayerBackground playerB = new EntityPlayerBackground();
	TextureLoader textureLoader = new TextureLoader();
	
	public static int moveableTileX, moveableTileY;

	public static int moveableTileOriginalX, moveableTileOriginalY;
	
	public static boolean moveableTileIsMoving;
	
	public static int entityMovingSpeed = 5;
	
	public EntityActivatable() {}
	
	public EntityActivatable(String style, int x, int y, int moveableTileX, int moveableTileY) {
		if(moveableTileX != x * tileU.tileWidth && moveableTileY != Main.height - tileU.tileHeight - y * tileU.tileHeight) {
			this.moveableTileX = x * tileU.tileWidth;
			this.moveableTileY = Main.height - tileU.tileHeight - y * tileU.tileHeight;
			moveableTileOriginalX = x * tileU.tileWidth;
			moveableTileOriginalY = Main.height - tileU.tileHeight - y * tileU.tileHeight;
		}
		renderTileActivatable("universal", moveableTileX, moveableTileY);
		System.out.println(moveableTileIsMoving);
		if(moveableTileY == moveableTileOriginalY + tileU.tileHeight) {
			moveableTileIsMoving = false;
		}
		if(moveableTileY > moveableTileOriginalY + tileU.tileHeight) {
			moveableTileY = moveableTileOriginalY + tileU.tileHeight;
		}
		if(moveableTileX == moveableTileOriginalX + tileU.tileWidth) {
			moveableTileIsMoving = false;
		}
		if(moveableTileX > moveableTileOriginalX + tileU.tileWidth) {
			moveableTileX = moveableTileOriginalX + tileU.tileWidth;
		}
	}
	
	public void activatePressurePad(int activatableID, int levelID) {
		if(levelID == 4 || levelID == 5) {
			if(moveableTileY < moveableTileOriginalY + tileU.tileHeight) {
				moveableTileIsMoving = true;
			}
		}
		if(levelID == 6) {
			if(moveableTileX < moveableTileOriginalX + tileU.tileWidth) {
				moveableTileIsMoving = true;
			}
		}
	}
	
	public void moveTile(int levelID) {
		if(levelID == 4 || levelID == 5) {
			if(moveableTileY > moveableTileOriginalY && !moveableTileIsMoving) {
				moveableTileY -= entityMovingSpeed;
			}
			if(moveableTileIsMoving && moveableTileY < moveableTileOriginalY + tileU.tileHeight) {
				moveableTileY += entityMovingSpeed;
			}
		}
		if(levelID == 6) {
			if(moveableTileX > moveableTileOriginalX && !moveableTileIsMoving) {
				moveableTileX -= entityMovingSpeed;
			}
			if(moveableTileIsMoving && moveableTileX < moveableTileOriginalX + tileU.tileWidth) {
				moveableTileX += entityMovingSpeed;
			}
		}
	}
	
	public void renderTileActivatable(String style, int x, int y) {
		if(style == "foreground") {
			glCallList(RenderTile.tileDisplayListForeground(x, y, tileF.tileWidth, tileF.tileHeight));
		}
		if(style == "background") {
			glCallList(RenderTile.tileDisplayListBackground(x, y, tileF.tileWidth, tileF.tileHeight));
		}
		if(style == "universal") {
			glBindTexture(GL_TEXTURE_2D, textureLoader.loadTexture("images/tileUniversal.png"));	
			glCallList(RenderTile.tileDisplayListUniversal(x, y, tileU.tileWidth, tileU.tileHeight));
			glBindTexture(GL_TEXTURE_2D, 0);
			RenderTile.detectCollision(playerB.x, playerB.y, playerB.width, playerB.height, playerB.xSpeed, playerB.ySpeed, x, y, tileB.tileWidth, tileB.tileHeight, EntityPlayerBackground.class, 0);
			RenderTile.detectCollision(playerF.x, playerF.y, playerF.width, playerF.height, playerF.xSpeed, playerF.ySpeed, x, y, tileF.tileWidth, tileF.tileHeight, EntityPlayerForeground.class, 0);
		}
	}
	
	public void resetPlayerPositions() {
		playerF.x = 100;
		playerF.y = 200;
		playerB.x = 50;
		playerB.y = 200;
		playerF.xSpeed = 0;
		playerF.ySpeed = 0;
		playerB.xSpeed = 0;
		playerB.ySpeed = 0;
	}
}

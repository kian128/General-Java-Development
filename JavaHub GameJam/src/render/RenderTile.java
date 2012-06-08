package render;

import static org.lwjgl.opengl.GL11.*;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;

import org.lwjgl.input.Mouse;

import main.Main;
import main.Warp;

import world.MapMain;
import entity.EntityActivatable;
import entity.EntityPlayerBackground;
import entity.EntityPlayerForeground;
import entity.EntityPressurePad;
import entity.EntityTileBackground;
import entity.EntityTileFinishingPost;
import entity.EntityTileForeground;
import entity.EntityTileUniversal;

public class RenderTile {
	
	static EntityTileForeground tileF = new EntityTileForeground();
	static EntityTileBackground tileB = new EntityTileBackground();
	static EntityTileUniversal tileU = new EntityTileUniversal();
	static EntityPlayerForeground playerF = new EntityPlayerForeground();
	static EntityPlayerBackground playerB = new EntityPlayerBackground();
	TextureLoader textureLoader = new TextureLoader();
	static EntityActivatable activatable = new EntityActivatable();
	
	public static int finishingPostFrontRed = 1, finishingPostFrontGreen = 0;
	public static int finishingPostBackRed = 1, finishingPostBackGreen = 0;
	
	public RenderTile() {}
	
	public RenderTile(int x, int y, String style, int activatableID) {
		if(style == "foreground") {
			glCallList(tileDisplayListForeground(x, y, tileF.tileWidth, tileF.tileHeight));
			detectCollision(playerF.x, playerF.y, playerF.width, playerF.height, playerF.xSpeed, playerF.ySpeed, x, y, tileF.tileWidth, tileF.tileHeight, EntityPlayerForeground.class, 0);
		}
		if(style == "background") {
			glCallList(tileDisplayListBackground(x, y, tileB.tileWidth, tileB.tileHeight));
			detectCollision(playerB.x, playerB.y, playerB.width, playerB.height, playerB.xSpeed, playerB.ySpeed, x, y, tileB.tileWidth, tileB.tileHeight, EntityPlayerBackground.class, 0);
		}
		if(style == "universal") {
			glBindTexture(GL_TEXTURE_2D, textureLoader.loadTexture("images/tileUniversal.png"));	
			glCallList(tileDisplayListUniversal(x, y, tileU.tileWidth, tileU.tileHeight));
			glBindTexture(GL_TEXTURE_2D, 0);	
			detectCollision(playerB.x, playerB.y, playerB.width, playerB.height, playerB.xSpeed, playerB.ySpeed, x, y, tileB.tileWidth, tileB.tileHeight, EntityPlayerBackground.class, 0);
			detectCollision(playerF.x, playerF.y, playerF.width, playerF.height, playerF.xSpeed, playerF.ySpeed, x, y, tileF.tileWidth, tileF.tileHeight, EntityPlayerForeground.class, 0);
		}
		if(style == "finishingpost") {
			glCallList(tileDisplayListActivatable(x + tileU.tileWidth / 10, y, tileU.tileWidth / 10, tileU.tileHeight, finishingPostBackRed, finishingPostBackGreen));
			glCallList(tileDisplayListActivatable(x + (tileU.tileWidth / 10) * 8, y, tileU.tileWidth / 10, tileU.tileHeight, finishingPostFrontRed, finishingPostFrontGreen));
			detectCollision(playerB.x, playerB.y, playerB.width, playerB.height, playerB.xSpeed, playerB.ySpeed, x, y, tileB.tileWidth, tileB.tileHeight, EntityTileFinishingPost.class, 0);
			detectCollision(playerF.x, playerF.y, playerF.width, playerF.height, playerF.xSpeed, playerF.ySpeed, x, y, tileF.tileWidth, tileF.tileHeight, EntityTileFinishingPost.class, 0);
		}
		if(style == "pressurepad") {
			detectCollision(playerF.x, playerF.y, playerF.width, playerF.height, playerF.xSpeed, playerF.ySpeed, x, y, tileF.tileWidth, tileF.tileHeight, EntityPressurePad.class, activatableID);
		}
	}
	 
	public static int tileDisplayListForeground(int x, int y, int width, int height) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			glColor3f(1, 1, 1);
			glVertex2f(x, y);
			glVertex2f(x, y + height);
			glVertex2f(x + width, y + height);
			glVertex2f(x + width, y);
			glColor4f(1, 1, 1, 1);
		glEnd();
		glEndList();
		return displayList;
	}
	
	public static int tileDisplayListBackground(int x, int y, int width, int height) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			glColor3f(0.5f, 0.5f, 0.5f);
			glVertex2f(x, y);
			glVertex2f(x, y + height);
			glVertex2f(x + width, y + height);
			glVertex2f(x + width, y);
			glColor4f(1, 1, 1, 1);
		glEnd();
		glEndList();
		return displayList;
	}
	
	public static int tileDisplayListUniversal(int x, int y, int width, int height) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(x, y);
			glTexCoord2f(0, 1);
			glVertex2f(x, y + height);
			glTexCoord2f(1, 1);
			glVertex2f(x + width, y + height);
			glTexCoord2f(1, 0);
			glVertex2f(x + width, y);
		glEnd();
		glEndList();
		return displayList;
	}
	
	public static int tileDisplayListActivatable(int x, int y, int width, int height, int red, int green) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
			glColor3f(red, green, 0);
			glVertex2f(x, y);
			glVertex2f(x, y + height);
			glVertex2f(x + width, y + height);
			glVertex2f(x + width, y);
			glColor3f(1, 1, 1);
		glEnd();
		glEndList();
		return displayList;
	}
	
	public static void detectCollision(int entityX, int entityY, int entityWidth, int entityHeight, int entityXSpeed, int entityYSpeed, int tileX, int tileY, int tileWidth, int tileHeight, Class entity, int activatableID) {
		if(entityX > tileX - entityWidth && entityX < tileX + tileWidth && entityY > tileY - entityHeight && entityY < tileY + tileHeight) {
			if(entityX < tileX - entityWidth + entityXSpeed && entityY > tileY - entityHeight + 1 && entityY < tileY + tileHeight - 1) {
				if(entity == EntityPlayerForeground.class) {
					playerF.xSpeed = 0;
					playerF.x = tileX - entityWidth;
				}
				if(entity == EntityPlayerBackground.class) {
					playerB.xSpeed = 0;
					playerB.x = tileX - entityWidth;
				}
			}
			if(entityX > tileX + tileWidth + entityXSpeed && entityY > tileY - entityHeight + 1 && entityY < tileY + tileHeight - 1) {
				if(entity == EntityPlayerForeground.class) {
					playerF.xSpeed = 0;
					playerF.x = tileX + tileWidth;
				}
				if(entity == EntityPlayerBackground.class) {
					playerB.xSpeed = 0;
					playerB.x = tileX + tileWidth;
				}
			}
			if(entityY < tileY - entityHeight + entityYSpeed && entityX > tileX - entityWidth + 1 && entityX < tileX + tileWidth - 1) {
				if(entity == EntityPlayerForeground.class) {
					playerF.ySpeed = 0;
					playerF.y = tileY - entityHeight;
				}
				if(entity == EntityPlayerBackground.class) {
					playerB.ySpeed = 0;
					playerB.y = tileY - entityHeight;
				}
			}
			if(entityY > tileY + tileHeight + entityYSpeed && entityX > tileX - entityWidth + 1 && entityX < tileX + tileWidth - 1) {
				if(entity == EntityPlayerForeground.class) {
					playerF.ySpeed = 0;
					playerF.y = tileY + tileHeight;
					playerF.jumpCounter = 0;
				}
				if(entity == EntityPlayerBackground.class) {
					playerB.ySpeed = 0;
					playerB.y = tileY + tileHeight;
					playerB.jumpCounter = 0;
				}
			}
		}
		
		if(playerF.x > tileX - entityWidth && playerF.x < tileX + tileWidth && playerF.y > tileY - entityHeight && playerF.y < tileY + tileHeight && entity == EntityTileFinishingPost.class) {
			finishingPostFrontRed = 0;
			finishingPostFrontGreen = 1;
		}else if(entity == EntityTileFinishingPost.class){
			finishingPostFrontRed = 1;
			finishingPostFrontGreen = 0;
		}
		if(playerB.x > tileX - entityWidth && playerB.x < tileX + tileWidth && playerB.y > tileY - entityHeight && playerB.y < tileY + tileHeight && entity == EntityTileFinishingPost.class) {
			finishingPostBackRed = 0;
			finishingPostBackGreen = 1;
		}else if(entity == EntityTileFinishingPost.class){
			finishingPostBackRed = 1;
			finishingPostBackGreen = 0;
		}
		
		if((playerB.x > tileX + tileWidth / 10 - entityWidth && playerB.x < tileX + tileWidth - tileWidth / 10 && playerB.y > tileY - entityHeight && playerB.y < tileY + tileHeight / 20 && entity == EntityPressurePad.class) || 
				(playerF.x > tileX + tileWidth / 10 - entityWidth && playerF.x < tileX + tileWidth - tileWidth / 10 && playerF.y > tileY - entityHeight && playerF.y < tileY + tileHeight / 20 && entity == EntityPressurePad.class)) {
			glCallList(tileDisplayListActivatable(tileX + tileWidth / 10, tileY, (tileWidth * 8) / 10, tileHeight / 20, 0, 1));
		}else if(entity == EntityPressurePad.class){
			glCallList(tileDisplayListActivatable(tileX + tileWidth / 10, tileY, (tileWidth * 8) / 10, tileHeight / 20, 1, 0));
		}
		
		if(playerB.x > tileX + tileWidth / 10 - entityWidth && playerB.x < tileX + tileWidth - tileWidth / 10 && playerB.y > tileY - entityHeight && playerB.y < tileY + tileHeight / 20 && entity == EntityPressurePad.class) {
			playerB.y = tileY + tileHeight / 20;
			activatable.activatePressurePad(activatableID, Main.levelID);
			playerB.jumpCounter = 0;
		}
		if(playerF.x > tileX + tileWidth / 10 - entityWidth && playerF.x < tileX + tileWidth - tileWidth / 10 && playerF.y > tileY - entityHeight && playerF.y < tileY + tileHeight / 20 && entity == EntityPressurePad.class) {
			playerF.y = tileY + tileHeight / 20;
			activatable.activatePressurePad(activatableID, Main.levelID);
			playerF.jumpCounter = 0;
		}
	}
}

package main;

import entity.EntityPlayerBackground;
import entity.EntityPlayerForeground;

public class Warp {
	
	public static boolean isWarping;
	public static int startX, startY, finishX, finishY;
	public static int distanceX, distanceY;
	public static Class startEntity;
	public static int warpCounter = 0;
	
	EntityPlayerForeground playerF = new EntityPlayerForeground();
	EntityPlayerBackground playerB = new EntityPlayerBackground();
	
	public void beginWarping(int startX, int startY, int finishX, int finishY, Class startEntity) {
		this.startX = startX;
		this.startY = startY;
		this.finishX = finishX;
		this.finishY = finishY;
		this.startEntity = startEntity;
		this.distanceX = finishX - startX;
		this.distanceY = finishY - startY;
		isWarping = true;
	}
	
	public void warp() {
		if(isWarping == true) {
			if(startEntity == playerF.getClass()) {
				playerF.x += distanceX / 5;
				playerF.y += distanceY / 5;
				if(distanceY > 2 || distanceY < -2) {
					playerF.y += 2;
				}
				if(distanceY > 200 || distanceY < -200) {
					playerF.y += 4;
				}
				warpCounter++;
			}
			if(startEntity == playerB.getClass()) {
				playerB.x += distanceX / 5;
				playerB.y += distanceY / 5;
				if(distanceY > 2 || distanceY < -2) {
					playerB.y += 2;
				}
				if(distanceY > 200 || distanceY < -200) {
					playerB.y += 4;
				}
				warpCounter++;
			}
			if(warpCounter == 5) {
				isWarping = false;
				warpCounter = 0;
			}
		}
	}

}

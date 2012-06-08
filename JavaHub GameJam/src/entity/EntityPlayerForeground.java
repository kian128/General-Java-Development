package entity;

import main.Main;
import main.Timing;

public class EntityPlayerForeground {
	
	public static int x = 100, y = 200;
	public static int width = 20, height = 20;
	public static int xSpeed = 0, ySpeed = -1;
	public static int jumpCounter = 0;
	
	public void playerPhysics() {
		if(ySpeed > -19) {
			ySpeed--;
		}
		
		x += ((xSpeed / 2) * 60) / Timing.printedFPS;
		y += ((ySpeed / 2) * 60) / Timing.printedFPS;
		
		if(x < 0) {
			x = 0;
			xSpeed = 0;
		}if(x > Main.width - width) {
			x = Main.width - width;
			xSpeed = 0;
		}if(y < 0) {
			y = 0;
			ySpeed = 0;
			jumpCounter = 0;
		}if(y > Main.height - width) {
			y = Main.height - width;
			ySpeed = 0;
		}
	}
}

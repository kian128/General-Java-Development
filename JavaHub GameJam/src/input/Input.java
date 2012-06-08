package input;

import main.GameStates;
import main.Main;
import main.Warp;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.mapped.Pointer;

import entity.EntityPlayerBackground;
import entity.EntityPlayerForeground;

public class Input {
	
	EntityPlayerForeground playerF = new EntityPlayerForeground();
	EntityPlayerBackground playerB = new EntityPlayerBackground();
	Warp warper = new Warp();
	private boolean isJumping = false;
	
	public Input() {
		boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
		boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
		boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP);
		
		boolean keyA = Keyboard.isKeyDown(Keyboard.KEY_A);
		boolean keyD = Keyboard.isKeyDown(Keyboard.KEY_D);
		boolean keyW = Keyboard.isKeyDown(Keyboard.KEY_W);
		
		if(!Main.isPaused && GameStates.state == GameStates.state.GAME_MAIN) {
			if(keyLeft && playerF.xSpeed > -19) {
				playerF.xSpeed--;
			}else if(playerF.xSpeed < 0) {
				playerF.xSpeed++;
			}
			if(keyRight && playerF.xSpeed < 19) {
				playerF.xSpeed++;
			}else if(playerF.xSpeed > 0) {
				playerF.xSpeed--;
			}
			if(keyA && playerB.xSpeed > -19) {
				playerB.xSpeed--;
			}else if(playerB.xSpeed < 0) {
				playerB.xSpeed++;
			}	
			if(keyD && playerB.xSpeed < 19) {
				playerB.xSpeed++;
			}else if(playerB.xSpeed > 0) {
				playerB.xSpeed--;
			}
		
			if(Keyboard.isKeyDown(Keyboard.KEY_UP) && playerF.jumpCounter == 0) {
				playerF.ySpeed = 22;
				playerF.jumpCounter = 1;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_W) && playerB.jumpCounter == 0) {
				playerB.ySpeed = 22;
				playerB.jumpCounter = 1;
			}	
		}
		
		while(Keyboard.next()) {
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				if(GameStates.state == GameStates.state.GAME_MAIN){
					Mouse.setGrabbed(false);
					Main.isPaused = !Main.isPaused;
				}
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_Q) && !Main.isPaused && GameStates.state == GameStates.state.GAME_MAIN) {
				warper.beginWarping(playerF.x, playerF.y, playerB.x, playerB.y, playerF.getClass());
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_E) && !Main.isPaused && GameStates.state == GameStates.state.GAME_MAIN) {
				warper.beginWarping(playerB.x, playerB.y, playerF.x, playerF.y, playerB.getClass());
			}
		}
		
		while(Mouse.next()) {
			if(Mouse.isButtonDown(0) && GameStates.state == GameStates.state.GAME_MAIN && !Main.isPaused) {
				Mouse.setGrabbed(true);
			}
		}
	}
}

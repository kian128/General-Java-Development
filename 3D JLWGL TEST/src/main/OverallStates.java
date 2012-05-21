package main;

import main.OverallStates.State;

public class OverallStates {
	
	public static enum State {
		IN_GAME, PAUSED, IN_MENU;
	}
	
	public static State state;
	
	public static void defineStates() {
		if(GameStates.state == GameStates.state.GAME_DUNGEON_CRAWLER || GameStates.state == GameStates.state.GAME_MODEL_TEST || GameStates.state == GameStates.state.GAME_TERRAIN_TEST) {
			state = state.IN_GAME;
		}
		if(GameStates.state == GameStates.state.MENU_ABOUT || GameStates.state == GameStates.state.MENU_ABOUT || GameStates.state == GameStates.state.MENU_WORLD_SELECT) {
			state = state.IN_MENU;
		}
		if(GuiStates.state == GuiStates.state.PAUSE) {
			state = state.PAUSED;
		}
	}

}

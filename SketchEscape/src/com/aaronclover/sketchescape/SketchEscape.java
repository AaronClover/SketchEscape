package com.aaronclover.sketchescape;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class SketchEscape extends Game {
	protected GameScreen game;
	protected MainMenu mainMenu;
	protected PauseMenu pauseMenu;
	protected GameOverMenu gameOverMenu;
	protected Screen howtoScreen;
	
	public enum ScreenState { mainmenu, howto, game, gameover, pause};
	public static ScreenState screenState = ScreenState.mainmenu;

	@Override
	public void create() {
		mainMenu = new MainMenu(this);
		howtoScreen = new HowToScreen(this);
		game = new GameScreen(this);
		pauseMenu = new PauseMenu(this);
		gameOverMenu = new GameOverMenu(this);
		setScreen(mainMenu);
	}

	public Screen getGameScreen() {
		screenState = ScreenState.game;
		return game;
	}

	public Screen getPauseMenu() {
		screenState = ScreenState.pause;
		return pauseMenu;
	}

	public Screen getMainMenu() {
		screenState = ScreenState.mainmenu;
		return mainMenu;
	}
	
	public Screen getGameOverMenu(int score) {
		screenState = ScreenState.gameover;
		gameOverMenu.setScore(score);
		return gameOverMenu;
	}

	public Screen getHowToScreen() {
		screenState = ScreenState.howto;
		return howtoScreen;
	}
	
	public void showAd() {
		setScreen(game);
		screenState = ScreenState.game;
	}
	
}
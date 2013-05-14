package com.aaronclover.sketchescape;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class SketchEscape extends Game {
	protected GameScreen game;
	private MainMenu mainMenu;
	private PauseMenu pauseMenu;
	private GameOverMenu gameOverMenu;

	@Override
	public void create() {
		mainMenu = new MainMenu(this);
		game = new GameScreen(this);
		pauseMenu = new PauseMenu(this);
		gameOverMenu = new GameOverMenu(this);
		setScreen(mainMenu);
	}

	public Screen getGameScreen() {
		return game;
	}

	public Screen getSplashScreen() {
		return mainMenu;
	}

	public Screen getPauseMenu() {
		return pauseMenu;
	}

	public Screen getMainMenu() {
		return mainMenu;
	}
	
	public Screen getGameOverMenu() {
		return gameOverMenu;
	}
}
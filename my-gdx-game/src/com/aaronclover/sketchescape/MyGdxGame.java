package com.aaronclover.sketchescape;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends Game {
	protected GameScreen game;
	private MainMenu mainMenu;
	private PauseMenu pauseMenu;
	

	@Override
	public void create() {
		mainMenu = new MainMenu(this);
		game = new GameScreen(this);
		pauseMenu = new PauseMenu(this);
		setScreen(mainMenu);
		
	}
	
	public Screen getGameScreen() {
		return game;
	} 
	
	public Screen getSplashScreen() {
		return mainMenu;
	}

	public Screen getPauseMenu(TextureRegion frame) {
		pauseMenu.setFrame(frame);
		return pauseMenu;
	}

	public Screen getMainMenu() {
		// TODO Auto-generated method stub
		return mainMenu;
	}
	
}
package com.me.mygdxgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends Game {
	private GameScreen game;
	private SplashScreen splash;
	private PauseMenu pauseMenu;
	

	@Override
	public void create() {
		splash = new SplashScreen(this);
		game = new GameScreen(this);
		pauseMenu = new PauseMenu(this);
		setScreen(splash);
		
	}
	
	public Screen getGameScreen() {
		return game;
	} 
	
	public Screen getSplashScreen() {
		return splash;
	}

	public Screen getPauseMenu(TextureRegion frame) {
		pauseMenu.setFrame(frame);
		return pauseMenu;
	}
	
}
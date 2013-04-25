package com.me.mygdxgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class MyGdxGame extends Game {
	private GameScreen game;
	private SplashScreen splash;
	

	@Override
	public void create() {
		splash = new SplashScreen(this);
		game = new GameScreen();
		setScreen(splash);
		
	}
	
	public Screen getGameScreen() {
		return game;
	} 
	
}
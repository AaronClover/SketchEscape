package com.aaronclover.sketchescape;


import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.google.ads.AdView;

public class SketchEscape extends Game {
	protected GameScreen game;
	protected MainMenu mainMenu;
	protected PauseMenu pauseMenu;
	protected GameOverMenu gameOverMenu;
	protected Screen howtoScreen;
	
	public enum ScreenState { mainmenu, howto, game, gameover, pause};
	public static ScreenState screenState = ScreenState.mainmenu;
	private IActivityRequestHandler myRequestHandler;

	public SketchEscape(IActivityRequestHandler handler) {
	        myRequestHandler = handler;
	    }
	
	@Override
	public void create() {
		mainMenu = new MainMenu(this);
		howtoScreen = new HowToScreen(this);
		game = new GameScreen(this);
		pauseMenu = new PauseMenu(this);
		gameOverMenu = new GameOverMenu(this, myRequestHandler);
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
	
	/**
	 * scale image
	 * 
	 * @param sbi image to scale
	 * @param imageType type of image
	 * @param dWidth width of destination image
	 * @param dHeight height of destination image
	 * @param fWidth x-factor for transformation / scaling
	 * @param fHeight y-factor for transformation / scaling
	 * @return scaled image
	 */
	public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
	    BufferedImage dbi = null;
	    if(sbi != null) {
	        dbi = new BufferedImage(dWidth, dHeight, imageType);
	        Graphics2D g = dbi.createGraphics();
	        AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
	        g.drawRenderedImage(sbi, at);
	    }
	    return dbi;
	}
	
}
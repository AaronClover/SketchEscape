package com.aaronclover.sketchescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainMenu extends MyScreen {
	private SpriteBatch spriteBatch;
	private Texture splash;
	private SketchEscape game;
	private Texture howtoButton;
	private Texture playButton;
	final int PLAY_BUTTON_WIDTH = 300;
	final int PLAY_BUTTON_HEIGHT = 100;
	private Rectangle playBox, howtoBox;

	public MainMenu(SketchEscape g) {
		game = g;
		spriteBatch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, RESW, RESH);
		splash = new Texture(Gdx.files.internal("data/whitepaper.png"));
		playButton = new Texture(Gdx.files.internal("data/play.png"));
		playBox = new Rectangle(camera.position.x - PLAY_BUTTON_WIDTH / 2,
				camera.position.y - PLAY_BUTTON_HEIGHT / 2, PLAY_BUTTON_WIDTH,
				PLAY_BUTTON_HEIGHT);
		howtoButton = new Texture(Gdx.files.internal("data/howtoplay.png"));
		howtoBox = new Rectangle(camera.position.x - PLAY_BUTTON_WIDTH / 2,
				camera.position.y - PLAY_BUTTON_HEIGHT / 2 - 150, PLAY_BUTTON_WIDTH,
				PLAY_BUTTON_HEIGHT);
	}

	@Override
	public void render(float delta) {
		camera.update();

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		spriteBatch.draw(splash, 0, 0);
		//spriteBatch.draw(playButton, playBox.x, playBox.y);
		spriteBatch.draw(playButton, playBox.x, playBox.y, playBox.width, playBox.height);
		spriteBatch.draw(howtoButton, howtoBox.x, howtoBox.y, howtoBox.width, howtoBox.height);

		if (MuteHandler.isMuted())
			spriteBatch.draw(muted, muteBox.x, muteBox.y);
		else
			spriteBatch.draw(unmuted, muteBox.x, muteBox.y);

		spriteBatch.end();


		if (Gdx.input.justTouched()) {
			int touchX = getTouchX();
			int touchY = RESH - getTouchY();
			
			if ((touchX >= playBox.x && touchX <= playBox.x + playBox.width) && (touchY >= playBox.y && touchY <= playBox.y + playBox.height)) {
				game.setScreen(game.getGameScreen());
			}
			if ((touchX >= howtoBox.x && touchX <= howtoBox.x + howtoBox.width) && (touchY >= howtoBox.y && touchY <= howtoBox.y + howtoBox.height)) {
				game.setScreen(game.getHowToScreen());
			}
			if (touchX >= muteBox.x && touchY >= muteBox.y) {
				MuteHandler.toggle();
			}
		}
	}

	@Override
	public void show() {
		super.show();
		game.game.create();
		System.gc();
	}
	
	
}

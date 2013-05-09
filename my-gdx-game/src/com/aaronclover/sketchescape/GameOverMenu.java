package com.aaronclover.sketchescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.aaronclover.sketchescape.GameScreen.GameState;

public class GameOverMenu extends MyScreen {
	private Texture pauseImage;
	TextureRegion frame;
	private Texture playButton;
	final int PLAY_BUTTON_WIDTH = 200;
	final int PLAY_BUTTON_HEIGHT = 100;
	private Rectangle playBox;

	public GameOverMenu(MyGdxGame g) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, RESW, RESH);
		spriteBatch = new SpriteBatch();
		game = g;
		pauseImage = new Texture(Gdx.files.internal("data/pause menu.png"));
		
		playButton = new Texture(Gdx.files.internal("data/play.png"));
		playBox = new Rectangle(camera.position.x - PLAY_BUTTON_WIDTH / 2,
				camera.position.y - PLAY_BUTTON_HEIGHT / 2, PLAY_BUTTON_WIDTH,
				PLAY_BUTTON_HEIGHT);

	}

	@Override
	public void render(float delta) {
		camera.update();

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		spriteBatch.draw(frame, 0, 0, RESW, RESH);
		spriteBatch.draw(pauseImage, 0, 0);
		spriteBatch.draw(playButton, playBox.x, playBox.y);
		spriteBatch.end();

		if (Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			game.setScreen(game.getGameScreen());
		}
		if (Gdx.input.isTouched()) {
			int touchX = Gdx.input.getX() * RESW / Gdx.graphics.getWidth();
			int touchY = Gdx.input.getY() * RESH / Gdx.graphics.getHeight();
			if (touchX >= playBox.x) {
				if (touchX <= playBox.x + playBox.width) {
					if (touchY >= playBox.y) {
						if (touchY <= RESH - playBox.y + playBox.height) {
							game.setScreen(game.getGameScreen());
						}
					}
				}
			}
		}
		}

	public void setFrame(TextureRegion f) {
		frame = f;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
			game.setScreen(game.getGameScreen());
		}
		return false;
	}
	
	@Override
	public void show() {
		super.show();
		game.game.create();
		System.gc();
	}
}
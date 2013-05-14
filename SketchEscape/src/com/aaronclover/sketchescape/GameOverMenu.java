package com.aaronclover.sketchescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class GameOverMenu extends MyScreen {
	TextureRegion frame;
	private Texture playButton;
	private Texture paper;
	final int PLAY_BUTTON_WIDTH = 300;
	final int PLAY_BUTTON_HEIGHT = 100;
	private Rectangle playBox;
	private int endingScore;

	public GameOverMenu(SketchEscape g) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, RESW, RESH);
		spriteBatch = new SpriteBatch();
		game = g;
		paper = new Texture(Gdx.files.internal("data/whitepaper.png"));
		playButton = new Texture(Gdx.files.internal("data/new game.png"));
		playBox = new Rectangle(camera.position.x - PLAY_BUTTON_WIDTH / 2,
				camera.position.y - PLAY_BUTTON_HEIGHT / 2, PLAY_BUTTON_WIDTH,
				PLAY_BUTTON_HEIGHT);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		spriteBatch.draw(paper, 0, 0);
		spriteBatch.draw(playButton, playBox.x, playBox.y);
		font.draw(spriteBatch, "Score: " + String.valueOf(endingScore),
				camera.position.x -50 - String.valueOf(endingScore).length() * 10,
				50);
		spriteBatch.end();

		if (Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			game.setScreen(game.getGameScreen());
		}
		if (Gdx.input.isTouched()) {
			int touchX = getTouchX();
			int touchY = getTouchY();
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

	public void setScore(int score) {
		endingScore = score;
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

package com.aaronclover.sketchescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.aaronclover.sketchescape.GameScreen.GameState;

public class PauseMenu extends MyScreen {
	private Texture pauseImage;
	TextureRegion frame;

	public PauseMenu(SketchEscape g) {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, RESW, RESH);
		spriteBatch = new SpriteBatch();
		game = g;
		pauseImage = new Texture(Gdx.files.internal("data/pause menu.png"));
		pReleased = false;

	}

	@Override
	public void render(float delta) {
		camera.update();

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		spriteBatch.draw(frame, 0, 0, RESW, RESH);
		spriteBatch.draw(pauseImage, 0, 0);
		spriteBatch.end();

		if (Gdx.input.isKeyPressed(Keys.P)) {
			if (pReleased == true) {
				pReleased = false;
				game.setScreen(game.getGameScreen());
			}
		} else {
			if (Gdx.input.isTouched()) {
				int touchedX = Gdx.input.getX() * RESW
						/ Gdx.graphics.getWidth();
				int touchedY = Gdx.input.getY() * RESH
						/ Gdx.graphics.getHeight();
				if ((touchedX >= 0 && touchedX <= 50)
						&& (touchedY >= 0 && touchedY <= 50)) {
					if (pReleased == true) {
						pReleased = false;
						game.setScreen(game.getGameScreen());
					}
				}
			} else {
				pReleased = true;
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
}

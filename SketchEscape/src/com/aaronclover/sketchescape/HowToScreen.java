package com.aaronclover.sketchescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HowToScreen extends MyScreen{
	
	private Texture image;

	public HowToScreen(SketchEscape g) {
		game = g;
		spriteBatch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, RESW, RESH);
		image = new Texture(Gdx.files.internal("data/howto.png"));
	}

	@Override
	public void render(float delta) {
	
		camera.update();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		spriteBatch.draw(image, 0, 0);
		spriteBatch.end();

		if (Gdx.input.justTouched()) {
				game.setScreen(game.getMainMenu());
			}
	}
	
}

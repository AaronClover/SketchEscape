package com.me.mygdxgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PauseMenu extends MyScreen{
	private Texture pauseImage;
	
	public PauseMenu(MyGdxGame g) {
		//super(g);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, RESW, RESH);
		spriteBatch = new SpriteBatch();
		game = g;
		pauseImage = new Texture(Gdx.files.internal("data/whitepaper.png"));
	}

	@Override
	public void render(float delta) {
		//Gdx.gl.glClearColor(0, 0, 0, 0.5f);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		//spriteBatch.draw(pauseImage, 0, 0);
		spriteBatch.end();
		
		if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			game.setScreen(game.getGameScreen());
		}
		
	}

}

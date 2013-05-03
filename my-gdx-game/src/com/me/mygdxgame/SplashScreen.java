package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class SplashScreen extends MyScreen{
	private Texture splash;
	private MyGdxGame game;
	
	SplashScreen(MyGdxGame g) {
		super(g);
		splash = new Texture(Gdx.files.internal("data/whitepaper.png"));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		spriteBatch.draw(splash, 0, 0);
		spriteBatch.end();
		
		if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			game.setScreen(game.getGameScreen());
		}
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	

}

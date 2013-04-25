package com.me.mygdxgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Splash implements Screen{
	private SpriteBatch spriteBatch;
	private Texture splash;
	private MyGdxGame game;
	
	public Splash(MyGdxGame g) {
		game = g;
		spriteBatch = new SpriteBatch();
		splash = new Texture(Gdx.files.internal("data/whitepaper.png"));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		spriteBatch.draw(splash, 0, 0);
		spriteBatch.end();
		
		if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			//myGame.setScreen(new GameScreen());
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	

}

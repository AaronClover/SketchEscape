package com.aaronclover.sketchescape;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.aaronclover.sketchescape.SketchEscape.ScreenState;

public class MyScreen implements Screen, InputProcessor {
	protected static SketchEscape game;
	protected SpriteBatch spriteBatch;
	MainMenu splash;
	
	protected boolean pReleased;
	protected boolean rightSideReleased;


	// Fonts
	static protected BitmapFont font;

	// This will determine what we see on the screen and also pans the view
	protected OrthographicCamera camera;

	// Used to render sprites
	protected SpriteBatch batch;

	// Used to determine where mouse/finger is pressed
	protected Vector3 touchPos = new Vector3();
	/*
	 * In game objects
	 */
	protected ArrayList<Obstacle> obstacles;
	protected Runner runner;
	/*
	 * End in game objects
	 */

	// Used as a timer for spawning obstacles
	protected long lastSpawnTime;
	protected float lastSpawnPos;
	protected float spawnPositionY;
	protected int spawnPositionRandom;

	// Current game timer
	protected float timer;

	// Current game score
	static protected int score;

	// Resolution Width
	public final int RESW = 800;
	// Resolution Height
	public final int RESH = 480;
	
	private final int TOUCH_SCALEX = RESW / Gdx.graphics.getWidth();
	private final int TOUCH_SCALEY = RESH / Gdx.graphics.getHeight();

	// protected boolean played;

	// Position of the floor
	protected final float FLOOR_HEIGHT = 60;
	protected Texture muted;
	protected Texture unmuted;
	protected Rectangle muteBox;
	
	
	MyScreen() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, RESW, RESH);
		//Mute Button
		muted = new Texture(Gdx.files.internal("data/muted.png"));
		unmuted = new Texture(Gdx.files.internal("data/unmuted.png"));
		muteBox = new Rectangle(camera.position.x + RESW / 2 - 60, RESH - 60, 50, 50);
	}

	/*
	 * The asset manager requires you to type more code, but allows you to
	 * prevent crashes caused by trying to use an asset before it's fully
	 * loaded.
	 */
	protected AssetManager manager = new AssetManager();

	/*
	 * MyScreen(MyGdxGame g) { camera = new OrthographicCamera();
	 * camera.setToOrtho(false, RESW, RESH); spriteBatch = new SpriteBatch();
	 * game = g; }
	 */
	
	public int getTouchX() {
		return Gdx.input.getX() * RESW / Gdx.graphics.getWidth();
	}
	
	public int getTouchY() {
		return Gdx.input.getY() * RESH / Gdx.graphics.getHeight();
	}
	
	public int getTouchX(int i) {
		return Gdx.input.getX(i) * RESW / Gdx.graphics.getWidth();
	}
	
	public int getTouchY(int i) {
		return Gdx.input.getY(i) * RESH / Gdx.graphics.getHeight();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);

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

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK) {
			switch (game.screenState) {
			case mainmenu:
				System.exit(0);
				break;
			
			case gameover:
				game.setScreen(game.getMainMenu());
				break;
				
			case howto:
				game.setScreen(game.getMainMenu());
				break;
			
			case game:
				game.setScreen(game.getPauseMenu());
				break;
				
			case pause:
				game.setScreen(game.getGameScreen());
				break;
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}

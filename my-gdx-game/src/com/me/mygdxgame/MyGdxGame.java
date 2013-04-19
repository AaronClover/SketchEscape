package com.me.mygdxgame;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class MyGdxGame implements ApplicationListener {
/*
 * Assets
 * 
 * Assets are things such as Images, music
 * 
 * All assets are stored in the Android project folder under assets
 */
	
	//Textures
	private Texture obstacle;
	private Texture floor;
	
	//Music
	private Music music1;
	private Music music2;
	
	//Sound
	
	
/*
 * End Assets
 */
	
	//Fonts
	private BitmapFont font;
	
	
	//This will determine what we see on the screen and also pans the view
	private OrthographicCamera camera;
	
	
	//Used to render sprites
	private SpriteBatch batch;
	
	// Used to determine where mouse/finger is pressed
	private Vector3 touchPos = new Vector3();
	
/*
 * In game objects	
 */
	private Array<Rectangle> obstacles;
	private Runner runner;
/*
 * End in game objects
 */
	
	//Used as a timer for spawning obstacles
	private long lastSpawnTime;
	
	//Current game timer
	private float timer;
	
	//Current game score
	private int score;
	
	
	
	//Resolution Width
	public final int RESW = 800;
	//Resolution Height
	public final int RESH = 480; 
	
	//private boolean played;
	
	// Position of the floor
	private final int FLOOR_POS = 30;
	
	
	
	/*
	 * The asset manager requires you to type more code, but 
	 * allows you to prevent crashes caused by trying to use an asset
	 * before it's fully loaded.
	 */
	AssetManager manager = new AssetManager();
	
	//The create class is called when the application is launched. It's not exactly a main class
	@Override
	public void create() {

		
		obstacle = new Texture(Gdx.files.internal("data/waterdrop.png"));
		
		//dropSound = Gdx.audio.newSound(Gdx.files.internal("data/waterdrop.wav"));
		//rainMusic = Gdx.audio.newMusic(Gdx.files.internal("data/music/01-TRACK 2.wav"));
		//music2 = Gdx.audio.newMusic(Gdx.files.internal("data/music/2.mp3"));
		manager.load("data/music/1.mp3", Music.class);
		manager.load("data/music/2.mp3", Music.class);
		manager.load("data/floor.png", Texture.class);
		manager.update();
		manager.finishLoading();
		music1 =  manager.get("data/music/1.mp3", Music.class);
		music2 = manager.get("data/music/2.mp3", Music.class);
		floor = manager.get("data/floor.png", Texture.class);
		
		
		
		music2.setLooping(true);
		

		music1.setLooping(true);
		//rainMusic.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, RESW, RESH);

		batch = new SpriteBatch();
		runner = new Runner(camera, FLOOR_POS);

		

		obstacles = new Array<Rectangle>();
		spawnRaindrop();
		
		score = 0;
		timer = 0;
		
		//font = new BitmapFont(Gdx.files.internal("Calibri.fnt"),Gdx.files.internal("Calibri.png"),false);
		font = new BitmapFont();
		
		//played = false;

	}

	@Override
	public void render() {
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.position.add(1, 0 , 0);
		camera.update();
		
		//if (timer >= (float)15/2 && played == false) {
			//music2.play();
			//played = true;
		//}
		
		timer += (float)1/60;
		runner.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		runner.draw(batch);
		for (Rectangle raindrop: obstacles) {
			batch.draw(obstacle, raindrop.x, raindrop.y);
		}
		font.draw(batch, String.valueOf(timer), camera.position.x, 420);
		batch.draw(floor, camera.position.x-RESW/2, FLOOR_POS-15);
		batch.end();

		if (Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			runner.jump();
		} else {
			runner.release();
		}
		
		if (Gdx.input.isKeyPressed(Keys.P)) {
		}

		if (Gdx.input.isTouched()) {
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
		}

		if (TimeUtils.nanoTime() - lastSpawnTime > 1000000000) {
			spawnRaindrop();
		}

		Iterator<Rectangle> iter = obstacles.iterator();
		while (iter.hasNext()) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (raindrop.y + 48 < 0) {
				iter.remove();
			}
			if (raindrop.overlaps(runner.hitbox)) {
				//dropSound.play();
				iter.remove();
				score+= 1;
			}
		}

	}


	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(camera.position.x, camera.position.x + RESW - 34);
		raindrop.y = RESH;
		raindrop.width = 34;
		raindrop.height = 48;
		obstacles.add(raindrop);
		lastSpawnTime = TimeUtils.nanoTime();
	}
	
	@Override
	public void dispose() {
		obstacle.dispose();
		runner.dispose();
		music1.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}

package com.aaronclover.sketchescape;

//Fixed touch controls
//Changed pause and game over screens to not show most recent frame due to library bug with non tegra android devices
//New button images
//Changed score display
//Added score to game over screen
//Changed paper image
import java.awt.Font;
import java.util.ArrayList;

import com.aaronclover.sketchescape.Runner.State;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen extends MyScreen {

	private static final int FINGERS_SUPPORTED = 3;
	protected static float runSpeed;
	private Texture pauseButton;
	private float pauseButtonHeight;
	private FrameBuffer pauseFrame;
	private TextureRegion pauseFrameRegion;
	private ScreenUtils screenUtils = new ScreenUtils();
	private Texture floor;
	private Texture background;
	protected float floorPosX[];
	protected float backgroundPosX[];

	protected enum GameState {
		running, paused, dead
	};

	GameState gameState;
	private long waitCounter;
	private float increment;

	public GameScreen(SketchEscape g) {
		spriteBatch = new SpriteBatch();
		game = g;
		create();
	}

	public void create() {
		gameState = GameState.running;
		runSpeed = 7f;
		increment = TimeUtils.nanoTime();

		manager.load("data/floor.png", Texture.class);
		manager.load("data/floor.png", Texture.class);
		manager.load("data/whitepaper.png", Texture.class);
		manager.load("data/pause.png", Texture.class);
		manager.update();
		manager.finishLoading();

		floor = manager.get("data/floor.png", Texture.class);
		background = manager.get("data/whitepaper.png", Texture.class);
		pauseButton = manager.get("data/pause.png", Texture.class);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, RESW, RESH);

		batch = new SpriteBatch();
		runner = new Runner(camera, FLOOR_HEIGHT);

		obstacles = new ArrayList<Obstacle>();
		lastSpawnPos = (int) camera.position.x + RESW;

		score = 0;
		timer = 0;
		font = new BitmapFont();

		font.setColor(Color.BLACK);
		font.setScale(2);

		floorPosX = new float[] { camera.position.x - RESW / 2,
				camera.position.x + RESW / 2 };

		backgroundPosX = new float[] { camera.position.x - RESW / 2,
				camera.position.x + RESW / 2 };

		pauseButtonHeight = RESH - 60;

		// Initialize PauseFrame
		pauseFrame = new FrameBuffer(Pixmap.Format.RGB888, RESW, RESH, false);
		pauseFrameRegion = new TextureRegion(pauseFrame.getColorBufferTexture());
		pauseFrameRegion.flip(false, true);

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void render(float delta) {
		switch (gameState) {

		case running:
			runningRender(delta);
			break;
		case paused:

			gameState = GameState.running;
			game.setScreen(game.getPauseMenu());

			break;
		case dead:
			if (TimeUtils.nanoTime() - waitCounter < 1000000000) {
				deadRender(delta);
			} else {
				game.setScreen(game.getGameOverMenu(score));
			}
			break;

		}

	}

	private void deadRender(float delta) {

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(background, backgroundPosX[0], 0);
		batch.draw(background, backgroundPosX[1], 0);
		batch.draw(floor, floorPosX[0], FLOOR_HEIGHT - 15);
		batch.draw(floor, floorPosX[1], FLOOR_HEIGHT - 15);
		runner.draw(batch);
		// Updates score
		score = ((int) (camera.position.x - RESW / 2) / 100);
		// Displays score
		font.draw(batch, String.valueOf(score), camera.position.x + RESW / 2
				- 100 - String.valueOf(score).length() * 7, RESH - 50);
		// Draws all objects in Array List
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).draw(batch);
		}

		batch.end();
	}

	public void runningRender(float delta) {
		
		
		// Moves player
		if (runSpeed < 10) {
			if (TimeUtils.nanoTime() - increment >= 15000000000f) {
				runSpeed += 1;
				increment = TimeUtils.nanoTime();
			}
		}

		camera.position.add(runSpeed, 0, 0);


		if (Gdx.app.getType() == ApplicationType.Android) {
			touchInput();
		} else {
			keyboardInput();
		}

		// Checks floor positioning
		if (floorPosX[0] <= camera.position.x - RESW * 1.5) {
			floorPosX[0] = floorPosX[1] + RESW;
		}
		if (floorPosX[1] <= camera.position.x - RESW * 1.5) {
			floorPosX[1] = floorPosX[0] + RESW;
		}

		if (backgroundPosX[0] <= camera.position.x - RESW * 1.5) {
			backgroundPosX[0] = backgroundPosX[1] + RESW;
		}
		if (backgroundPosX[1] <= camera.position.x - RESW * 1.5) {
			backgroundPosX[1] = backgroundPosX[0] + RESW;
		}

		// Updates score
		score = ((int) (camera.position.x - RESW / 2) / 100);

		// Rendering...everything!!!
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(background, backgroundPosX[0], 0);
		batch.draw(background, backgroundPosX[1], 0);
		batch.draw(floor, floorPosX[0], FLOOR_HEIGHT - 15);
		batch.draw(floor, floorPosX[1], FLOOR_HEIGHT - 15);
		batch.draw(pauseButton, camera.position.x - RESW / 2 + 10,
				pauseButtonHeight);
		runner.draw(batch);

		// Draws Score
		font.draw(batch, String.valueOf(score), camera.position.x + RESW / 2
				- 100 - String.valueOf(score).length() * 7, RESH - 50);

		// Draws all objects in Array List
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).draw(batch);
		}

		batch.end();
		camera.update();
		// End of drawing

		// generate random selection for obstacle to be on floor or mid height.
		spawnPositionRandom = MathUtils.random(1, 2);
		if (spawnPositionRandom == 1) {
			spawnPositionY = FLOOR_HEIGHT + 50;
		} else {
			spawnPositionY = FLOOR_HEIGHT;
		}

		if (obstacles.size() < 10) {
			obstacles.add(new Obstacle(camera, lastSpawnPos + 256,
					spawnPositionY));
			lastSpawnPos = obstacles.get(obstacles.size() - 1).hitbox.x;
		}

		for (int i = 0; i < obstacles.size(); i++) {
			// Debug
			// obstacles.get(i).drawHitbox();
			if (obstacles.get(i).hitbox.x < camera.position.x - RESW) {
				obstacles.remove(i);
			}
			
			
		//Checks if runner collides with an obstacle on the x axis
			if (runner.hitbox.x + runner.hitbox.width > obstacles.get(i).hitbox.x
					&& runner.hitbox.x + runner.hitbox.width < obstacles.get(i).hitbox.x + obstacles.get(i).hitbox.width) {
				//Checks if runner is in obstacle on y axis
				if ((runner.hitbox.y < obstacles.get(i).hitbox.y - 5
						+ Obstacle.SPRITE_HEIGHT) && (runner.hitbox.y + runner.hitbox.height > obstacles.get(i).hitbox.y)) {
					endGame();
				}
				// Checks if runner is landing above box
				else if (runner.hitbox.y <= obstacles.get(i).hitbox.y + Obstacle.SPRITE_HEIGHT 
						&& runner.hitbox.y + runner.hitbox.height > obstacles.get(i).hitbox.y + Obstacle.SPRITE_HEIGHT) {
					runner.land(obstacles.get(i).hitbox.y
							+ Obstacle.SPRITE_HEIGHT);
				}
				else {
					//runner.fall();
				}
			}

		}

		

		// Collision detection
		if (runner.hitbox.y <= runner.floorHeight) {
			runner.land(runner.floorHeight);
		}
		runner.drawHitbox();
		
		runner.update();

	}

	private void keyboardInput() {
		if (Gdx.app.getType() == ApplicationType.Desktop) {
			if (Gdx.input.isKeyPressed(Keys.SPACE)) {
				runner.jump();
			} else {
				runner.jumpRelease();
			}
			if (Gdx.input.isKeyPressed(Keys.DOWN)) {
				runner.duck();
			} else {
				runner.duckRelease();
			}
			// Controls pausing
			if (Gdx.input.isKeyPressed(Keys.P)) {
				if (pReleased == true) {
					pReleased = false;
					pause();
				}
			} else {
				if (Gdx.input.isTouched()) {
					int touchedX = Gdx.input.getX();
					int touchedY = Gdx.input.getY();

					if ((touchedX >= 0 && touchedX <= 50)
							&& (touchedY >= 0 && touchedY <= 50)) {
						if (pReleased == true) {
							pReleased = false;
							pause();
						}
					}
				} else {
					pReleased = true;
				}

			}

		}

	}

	private void touchInput() {
		boolean rightTouched = false;
		boolean pauseTouched = false;
		for (int i = 0; i < FINGERS_SUPPORTED; i++) {
			if (Gdx.input.isTouched(i)) {
				int touchedX = getTouchX(i);
				int touchedY = getTouchY(i);
				if ((touchedX >= 0 && touchedX <= 60)
						&& (touchedY >= 0 && touchedY <= 60)) {
					if (pReleased == true) {
						pauseTouched = true;
						pause();
					}
				}
				// If user touches left side of the screen then Runner Ducks
				if (touchedX < 400) {
					runner.duck();
				}
				// user touches right side of the screen then Jump.
				if (touchedX >= 400) {
					runner.jump();
					rightTouched = true;
				}
			}
		}
		if (rightTouched == false) {
			runner.jumpRelease();
		}
		if (pauseTouched == false) {
			pReleased = true;
		} else {
			pReleased = false;
		}
	}

	private void endGame() {
		runner.state = State.dead;
		runner.animationIndex = 0;
		gameState = GameState.dead;
		waitCounter = TimeUtils.nanoTime();
	}

	@Override
	public void dispose() {
		for (int i = 0; i < obstacles.size(); i++) {
			obstacles.get(i).dispose();
		}
		runner.dispose();
		batch.dispose();
	}

	@Override
	public void pause() {
		super.pause();
		System.out.println("You Paused");
		gameState = GameState.paused;
	}
}
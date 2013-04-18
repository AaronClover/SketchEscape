package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;

public class Runner {
	private Texture runnerImage;
	protected Rectangle hitbox;
	private float RESW;
	private float RESH;
	private float camH;
	private float camW;
	final int SPRITE_WIDTH = 96;
	final int SPRITE_HEIGHT = 96;
	final int SCALE = 1;
	final int WIDTH = SPRITE_WIDTH * SCALE;
	final int HEIGHT = SPRITE_HEIGHT * SCALE;
	private OrthographicCamera camera;
	private int floorHeight;
	private int posX = -300;
	private boolean jumped;
	private float speedY;
	private final float gravity = -1;
	private float jumpSpeed;
	protected boolean released;

	Runner(OrthographicCamera pcamera, int pheight) {
		RESW = pcamera.viewportHeight;
		RESH = pcamera.viewportHeight;
		camH = RESH / 2;
		camW = RESW / 2;
		camera = pcamera;
		floorHeight = pheight;
		create();
	}

	public void create() {
		speedY = 0;
		runnerImage = new Texture(Gdx.files.internal("data/red_bucket.png"));
		hitbox = new Rectangle(camera.position.x + posX, floorHeight,
				SPRITE_WIDTH, SPRITE_HEIGHT);
	}

	public void draw(SpriteBatch batch) {

		batch.draw(runnerImage, hitbox.x, hitbox.y);
	}

	public void floorCheck() {
		if (speedY > 0) {
			jumped = true;
			jumpSpeed *= 0.70;
		} else {
			if (hitbox.y <= floorHeight) {
				jumped = false;
				jumpSpeed = 6;
				hitbox.y = floorHeight;
				speedY = 0;
			}
		}

	}

	public void dispose() {
		runnerImage.dispose();
	}

	public void update() {
		speedY += gravity;
		hitbox.y += speedY;
		hitbox.x = camera.position.x + posX;
		floorCheck();
	}

	public void jump() {
		if (jumped == false && released == true) {
			speedY += jumpSpeed;
			System.out.println("Jump!\n");
		}
		if (jumped == true && released == false) {
			speedY += jumpSpeed;
		}
		released = false;
	}

	public void release() {
		if (jumped == false) {
			released = true;
		}

	}

}

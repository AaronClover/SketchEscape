package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Obstacle {
	//Assets
	private Texture image;
	//End Assets
	
	protected Rectangle hitbox;
	private float RESW;
	private float RESH;
	final static int SPRITE_WIDTH = 48;
	final int SPRITE_HEIGHT = 48;
	final static int SCALE = 5; //Reduces hitbox size, scaling from center
	private OrthographicCamera camera;
	protected float height;
	protected boolean released;
	//Debug 
	ShapeRenderer shapeRenderer = new ShapeRenderer();

	Obstacle(OrthographicCamera pcamera, float x, float y) {
		RESW = pcamera.viewportHeight;
		RESH = pcamera.viewportHeight;
		camera = pcamera;
		height = y;
		create(x, y);
	}

	public void create(float x, float y) {
		image = new Texture(Gdx.files.internal("data/obstacle.png"));
		hitbox = new Rectangle(x, y, SPRITE_WIDTH-SCALE,
				SPRITE_HEIGHT-SCALE);
	}

	public void draw(SpriteBatch batch) {

		batch.draw(image, hitbox.x-SCALE/2, hitbox.y-SCALE/2);
	}
	public void drawHitbox() {
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Rectangle);
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		shapeRenderer.end();
	}

	public void dispose() {
		image.dispose();
	}

	public boolean isOffScreen() {
		if (hitbox.x < camera.position.x - RESW) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public float getTop() {
		return height + SPRITE_HEIGHT;
	}



}

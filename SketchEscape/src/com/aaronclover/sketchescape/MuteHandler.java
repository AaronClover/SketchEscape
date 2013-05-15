package com.aaronclover.sketchescape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MuteHandler {
	
	public static boolean mute = false;
	public static Texture muted;
	public static Texture unmuted;
	
	public static void toggle() {
		if (mute == false)
			mute = true;
		else
			mute = false;
	}
	
	public static boolean isMuted() {
		return mute;
	}
	
	public void init() {
		muted = new Texture(Gdx.files.internal("data/muted.png"));
		unmuted = new Texture(Gdx.files.internal("data/unmuted.png"));
	}
	
	public static Texture getMutedTexture() {
		return muted;
	}
	
	public static Texture getUnmutedTexture() {
		return unmuted;
	}
	
	

}

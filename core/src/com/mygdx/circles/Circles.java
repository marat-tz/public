package com.mygdx.circles;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Random;

public class Circles extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	public final int SCREEN_WIDTH = 800;
	public final int SCREEN_HEIGHT = 480;

	public void create() {

		this.setScreen(new MainMenuScreen(this));

		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font

	}

	public void render() {

		super.render(); // important!
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}
}

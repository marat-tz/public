package com.mygdx.circles;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Circles extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	public final static int WIDTH = 800;
	public final static int HEIGHT = 480;
	private MainMenuScreen mainMenuScreen;

	public void create() {

		mainMenuScreen = new MainMenuScreen(this);
		setScreen(mainMenuScreen);
		//setScreen(testScreenFirst);

		//batch = new SpriteBatch();
		//font = new BitmapFont(); // use libGDX's default Arial font

	}

	public void dispose() {
		//batch.dispose();
		//font.dispose();
	}
}

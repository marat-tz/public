package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.circles.Circles;

public class MainMenuScreen implements Screen {

    final Circles game;
    private Stage stage;
    private Button buttonStart;
    private Button buttonQuit;
    OrthographicCamera camera;
    Viewport viewport;
    ShapeRenderer newGame;
    ShapeRenderer quit;


    public MainMenuScreen(final Circles game) {
        this.game = game;

        stage = new Stage(new FillViewport(game.SCREEN_WIDTH, game.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        /*Window window = new Window("Window", mySkin);
        window.setSize(800, 480);
        stage.addActor(window);*/

        Table rootTable = new Table(mySkin);
        rootTable.background("blue");
        rootTable.setSize(800, 480);
        stage.addActor(rootTable);

        Label title = new Label("ARKANOID", mySkin);
        title.setSize(150, 50);
        title.setPosition(Gdx.graphics.getWidth() / 2 - 130, Gdx.graphics.getHeight() / 2 + 100);
        title.setFontScale(2.5f);
        stage.addActor(title);

        buttonStart = new TextButton("Start Game", mySkin);
        buttonStart.setSize(200, 30);
        buttonStart.setPosition(Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2);
        buttonStart.setTransform(true);
        buttonStart.scaleBy(0.5f);
        stage.addActor(buttonStart);

        buttonQuit = new TextButton("Quit", mySkin);
        buttonQuit.setSize(200, 30);
        buttonQuit.setPosition(Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2 - 70);
        buttonQuit.setTransform(true);
        buttonQuit.scaleBy(0.5f);
        stage.addActor(buttonQuit);
    }

    @Override
    public void render(float delta) {
        delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        if (buttonStart.isChecked()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }

        if (buttonQuit.isChecked()) {
            game.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        // use true here to center the camera
        // that's what you probably want in case of a UI
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }
}

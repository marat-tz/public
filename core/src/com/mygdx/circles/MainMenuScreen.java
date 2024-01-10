package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.mygdx.circles.Circles.HEIGHT;
import static com.mygdx.circles.Circles.WIDTH;

public class MainMenuScreen implements Screen {

    final Circles game;
    private Stage stage;
    private Button buttonStart;
    private Button buttonQuit;

    private ShapeRenderer shape;

    private Viewport viewport;

    private Ball ball;
    private Paddle paddle;


    public MainMenuScreen(Circles game) {
        this.game = game;

        //stage = new Stage(new StretchViewport(WIDTH, HEIGHT));
        viewport = new FitViewport(WIDTH, HEIGHT);
        stage = new Stage(viewport);
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

        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);

        stage.act(delta);
        stage.draw();

        if (buttonStart.isChecked()) {
            game.setScreen(new GameTestScreen(game));
            dispose();
        }

        if (buttonQuit.isChecked()) {
            dispose();
            game.setScreen(new GameScreen(game));
            //game.dispose();
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
        Gdx.input.setInputProcessor(null);
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

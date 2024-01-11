package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.mygdx.circles.Circles.HEIGHT;
import static com.mygdx.circles.Circles.WIDTH;

public class GameTestScreen implements Screen {

    private Circles game;
    private Stage stage;
    private GameMechanics gameMechanics;
    private Viewport viewport;

    public GameTestScreen(Circles game) {
        //camera = new PerspectiveCamera();
        viewport = new FitViewport(WIDTH, HEIGHT);
        stage = new Stage(viewport);

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.None);

        gameMechanics = new GameMechanics(game);
        stage.addActor(gameMechanics);
        stage.setKeyboardFocus(gameMechanics);

        /*Table rootTable = new Table();
        //rootTable.background("blue");
        rootTable.setSize(WIDTH, HEIGHT);
        rootTable.add(gameMechanics);
        rootTable.setDebug(true);
        stage.addActor(rootTable);

        stage.setKeyboardFocus(rootTable);*/
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //Gdx.gl.glClearColor(66.0f/255.0f,66.0f/255.0f,231.0f/255.0f,1);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FillViewport;

public class WinScreen implements Screen {
    final Circles game;
    Stage stage;
    Button buttonRestart;
    Button buttonMenu;

    public WinScreen(final Circles game) {
        this.game = game;

        stage = new Stage(new FillViewport(game.WIDTH, game.HEIGHT));
        Gdx.input.setInputProcessor(stage);

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Table rootTable = new Table(mySkin);
        rootTable.background("blue");
        rootTable.setSize(800, 480);
        stage.addActor(rootTable);

        Label title = new Label("You WIN!", mySkin);
        title.setSize(150, 50);
        title.setPosition(Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2 + 100);
        title.setFontScale(2.5f);
        stage.addActor(title);

        Label titleBottom = new Label("Master!", mySkin);
        titleBottom.setSize(150, 50);
        titleBottom.setPosition(Gdx.graphics.getWidth() / 2 - 25, Gdx.graphics.getHeight() / 2 + 60);
        titleBottom.setFontScale(0.8f);
        stage.addActor(titleBottom);

        buttonRestart = new TextButton("Restart", mySkin);
        buttonRestart.setSize(200, 30);
        buttonRestart.setPosition(Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2);
        buttonRestart.setTransform(true);
        buttonRestart.scaleBy(0.5f);
        stage.addActor(buttonRestart);

        buttonMenu = new TextButton("Main Menu", mySkin);
        buttonMenu.setSize(200, 30);
        buttonMenu.setPosition(Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2 - 70);
        buttonMenu.setTransform(true);
        buttonMenu.scaleBy(0.5f);
        stage.addActor(buttonMenu);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);

        stage.act(delta);
        stage.draw();

        if (buttonRestart.isChecked()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }

        if (buttonMenu.isChecked()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {
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

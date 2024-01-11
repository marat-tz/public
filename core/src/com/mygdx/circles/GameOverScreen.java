package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.mygdx.circles.Circles.HEIGHT;
import static com.mygdx.circles.Circles.WIDTH;

public class GameOverScreen implements Screen {

    final Circles game;
    private Stage stage;
    private Button buttonRestart;
    private Button buttonMenu;

    private Viewport viewport;

    public GameOverScreen(final Circles game) {
        this.game = game;

        viewport = new FitViewport(WIDTH, HEIGHT);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Label title = new Label("Game Over", mySkin);
        title.setSize(150, 50);
        title.setPosition(WIDTH / 2 - 150, HEIGHT / 2 + 100);
        title.setFontScale(2.5f);
        //stage.addActor(title);

        Label titleBottom = new Label("LOL Noob", mySkin);
        titleBottom.setSize(150, 50);
        titleBottom.setPosition(WIDTH / 2 - 25, HEIGHT / 2 + 60);
        titleBottom.setFontScale(0.8f);
        //stage.addActor(titleBottom);

        buttonRestart = new TextButton("Restart", mySkin);
        buttonRestart.setSize(200, 30);
        buttonRestart.setPosition(WIDTH / 2 - 125, HEIGHT / 2);
        buttonRestart.setTransform(true);
        buttonRestart.scaleBy(0.5f);
        //stage.addActor(buttonRestart);

        buttonMenu = new TextButton("Main Menu", mySkin);
        buttonMenu.setSize(200, 30);
        buttonMenu.setPosition(WIDTH / 2 - 125, Gdx.graphics.getHeight() / 2 - 70);
        buttonMenu.setTransform(true);
        buttonMenu.scaleBy(0.5f);
        //stage.addActor(buttonMenu);

        Table rootTable = new Table(mySkin);
        rootTable.background("blue");
        rootTable.setSize(WIDTH, HEIGHT);
        rootTable.add(title).fill();
        rootTable.row();
        rootTable.add(titleBottom).padBottom(40);
        rootTable.row();
        rootTable.add(buttonRestart).padBottom(30).padRight(55);
        rootTable.row();
        rootTable.add(buttonMenu).padRight(75);
        //rootTable.setDebug(true);
        stage.addActor(rootTable);
    }

    @Override
    public void render(float delta) {

        delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);

        stage.act(delta);
        stage.draw();

        if (buttonRestart.isChecked()) {
            game.setScreen(new GameTestScreen(game));
            dispose();
        }

        if (buttonMenu.isChecked()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
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
        stage.dispose();
    }

}

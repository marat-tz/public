package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.circles.Circles;

public class MainMenuScreen implements Screen {

    final Circles game;
    private Stage stage;
    OrthographicCamera camera;
    Viewport viewport;
    ShapeRenderer newGame;
    ShapeRenderer quit;


    public MainMenuScreen(final Circles game) {
        this.game = game;

        stage = new Stage(new FitViewport(800, 480));
        Gdx.input.setInputProcessor(stage);

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Window window = new Window("Window", mySkin);
        window.setSize(800, 480);
        stage.addActor(window);

        Button buttonStart = new TextButton("Start Game", mySkin);
        buttonStart.setSize(200, 30);
        buttonStart.setPosition(Gdx.graphics.getWidth() / 2 - 125, Gdx.graphics.getHeight() / 2);
        buttonStart.setTransform(true);
        buttonStart.scaleBy(0.5f);
        stage.addActor(buttonStart);

        //camera = new OrthographicCamera();
        //camera.setToOrtho(false, 800, 480);

        newGame = new ShapeRenderer();
        quit = new ShapeRenderer();

    }

    @Override
    public void render(float delta) {
        delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        //camera.update();
        //game.batch.setProjectionMatrix(camera.combined);

        //game.batch.begin();
        //game.font.draw(game.batch, "Play", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        //game.font.draw(game.batch, "Quit", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 - 50);
        //game.batch.end();

        /*newGame.begin(ShapeRenderer.ShapeType.Line);

        if (Gdx.input.getX() > Gdx.graphics.getWidth()/2 - 36
                && Gdx.input.getX() < Gdx.graphics.getWidth()/2 + 64
                && (Gdx.input.getY() * -1) + Gdx.graphics.getHeight() > Gdx.graphics.getHeight()/2 - 20
                && (Gdx.input.getY() * -1) + Gdx.graphics.getHeight() < Gdx.graphics.getHeight()/2 + 8) {
            newGame.setColor(0, 1, 0, 1);

            if (Gdx.input.isTouched()) {
                game.setScreen(new GameScreen(game));
                dispose();
            }

        } else {
            newGame.setColor(1, 1, 1, 1);
        }

        newGame.rect(Gdx.graphics.getWidth()/2 - 36, Gdx.graphics.getHeight()/2 - 20, 100, 28);
        newGame.end();

        quit.begin(ShapeRenderer.ShapeType.Line);
        //y = (Gdx.input.getY() * -1) + Gdx.graphics.getHeight();
        if (Gdx.input.getX() > Gdx.graphics.getWidth()/2 - 36
                && Gdx.input.getX() < Gdx.graphics.getWidth()/2 + 64
                && (Gdx.input.getY() * -1) + Gdx.graphics.getHeight() > Gdx.graphics.getHeight()/2 - 70
                && (Gdx.input.getY() * -1) + Gdx.graphics.getHeight() < Gdx.graphics.getHeight()/2 - 42) {
            quit.setColor(0, 1, 0, 1);

            if (Gdx.input.isTouched()) {
                game.dispose();
            }

        } else {
            quit.setColor(1, 1, 1, 1);
        }

        quit.rect(Gdx.graphics.getWidth()/2 - 36, Gdx.graphics.getHeight()/2 - 70, 100, 28);

        quit.end();*/

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

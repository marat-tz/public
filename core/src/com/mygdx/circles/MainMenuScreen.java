package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.mygdx.circles.Circles.HEIGHT;
import static com.mygdx.circles.Circles.WIDTH;

public class MainMenuScreen extends BaseScreen {

    public final static float SCALE = 1f;
    public final static float INV_SCALE = 1.f/SCALE;
    public final static float VP_WIDTH = 800 * INV_SCALE;
    public final static float VP_HEIGHT = 480 * INV_SCALE;

    protected OrthographicCamera gameCamera;
    protected ExtendViewport gameViewport;

    final Circles game;
    private Stage stage;
    private Button buttonStart;
    private Button buttonQuit;
    private Viewport viewport;

    private TextField gScreenCoordsX;
    private TextField gScreenCoordsY;
    private ShapeRenderer shape;


    public MainMenuScreen(Circles game) {
        this.game = game;

        gameCamera = new OrthographicCamera();
        gameViewport = new ExtendViewport(VP_WIDTH, VP_HEIGHT, gameCamera);
        stage = new Stage(gameViewport, batch);

        Gdx.input.setInputProcessor(stage);

        shape = new ShapeRenderer();

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        Gdx.input.setCursorCatched(false);

        Label title = new Label("ARKANOID", mySkin);
        title.setSize(150, 50);
        title.setFontScale(2.5f);

        Label version = new Label("v0.0.3", mySkin);
        version.setSize(50, 30);
        version.setFontScale(0.5f);

        buttonStart = new TextButton("Start Game", mySkin);
        buttonStart.setSize(200, 30);
        buttonStart.setPosition(WIDTH / 2 - 125, HEIGHT / 2);
        buttonStart.setTransform(true);
        buttonStart.scaleBy(0.5f);
        //stage.addActor(buttonStart);

        buttonQuit = new TextButton("Quit", mySkin);
        buttonQuit.setSize(200, 30);
        buttonQuit.setPosition(WIDTH / 2 - 125, HEIGHT / 2 - 70);
        buttonQuit.setTransform(true);
        buttonQuit.scaleBy(0.5f);
        //stage.addActor(buttonQuit);

        Ball ball = new Ball(0,0,1,1,100);

        Table rootTable = new Table(mySkin);
        rootTable.background("blue");
        rootTable.setSize(VP_WIDTH, VP_HEIGHT);
        rootTable.add(title).padBottom(50).fill();
        rootTable.row();
        rootTable.add(buttonStart).padBottom(30).padRight(87);
        rootTable.row();
        rootTable.add(buttonQuit).padRight(45);
        rootTable.row();
        rootTable.add(gScreenCoordsX).padRight(45);
        rootTable.row();
        rootTable.add(gScreenCoordsY).padRight(45);
        rootTable.row();
        rootTable.add(version).left().bottom();
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

        //renderer.circle(gameCamera.position.x - gameCamera.viewportWidth/2,gameCamera.position.y - gameCamera.viewportHeight/2,150);

        //gScreenCoordsX.setText("X: " + Gdx.input.getX());
        //gScreenCoordsY.setText("Y: " + Gdx.input.getY());

        if (buttonStart.isChecked()) {
            game.setScreen(new GameTestScreen(game));
            dispose();
        }

        if (buttonQuit.isChecked()) {
            //game.setScreen(new GameOverScreen(game));
            dispose();
        }
    }

    @Override public void resize (int width, int height) {
        gameViewport.update(width, height, true);
        gameCamera.position.x += -(gameCamera.viewportWidth - VP_WIDTH)/2;
        gameCamera.position.y += -(gameCamera.viewportHeight - VP_HEIGHT)/2;
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
        stage.dispose();
    }
}

package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class GameTestScreen extends BaseScreen {

    public final static float SCALE = 1f;
    public final static float INV_SCALE = 1.f/SCALE;
    public final static float VP_WIDTH = 800 * INV_SCALE;
    public final static float VP_HEIGHT = 480 * INV_SCALE;

    protected OrthographicCamera gameCamera;
    protected ExtendViewport gameViewport;

    protected OrthographicCamera guiCamera;
    protected ExtendViewport guiViewport;

    private Circles game;
    private Stage gameStage;
    private Stage guiStage;

    private TextField scoreText;
    private TextField speedText;

    private ArrayList<Block> blocks = new ArrayList<>();

    private Ball ball;
    private Paddle paddle;

    private Score score;

    public GameTestScreen(Circles game) {
        this.game = game;

        gameCamera = new OrthographicCamera();
        gameViewport = new ExtendViewport(VP_WIDTH, VP_HEIGHT, gameCamera);
        gameStage = new Stage(gameViewport, batch);

        guiCamera = new OrthographicCamera();
        guiViewport = new ExtendViewport(VP_WIDTH, VP_HEIGHT, gameCamera);
        guiStage = new Stage(guiViewport, batch);

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        Gdx.input.setCursorCatched(true);

        scoreText = new TextField("Score:", mySkin, "nobg");
        scoreText.setSize(150, 50);
        scoreText.setPosition(5, HEIGHT - 35);
        scoreText.scaleBy(0.5f);

        speedText = new TextField("Speed:", mySkin, "nobg");
        speedText.setSize(300, 50);
        speedText.setPosition(5, HEIGHT - 65);
        speedText.scaleBy(0.5f);

        ball = new Ball(Gdx.graphics.getWidth()/2, 40, 2.5, 2.5, 10);
        paddle = new Paddle(0, 15, 180, 20);
        score = new Score();

        //add blocks to screen
        int blockWidth = 62;
        int blockHeight = 20;

        for(int y = HEIGHT/2 - 30; y < HEIGHT - 70; y += blockHeight + 10) {
            for(int x = 10; x < WIDTH - 10; x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }

        Table rootTable = new Table();
        rootTable.setSkin(mySkin);
        rootTable.background("blue");
        rootTable.setSize(VP_WIDTH, VP_HEIGHT);
        //rootTable.setDebug(true);
        gameStage.addActor(rootTable);

        Table guiTable = new Table();
        guiTable.setSize(VP_WIDTH, VP_HEIGHT);
        guiTable.add(scoreText).expandX().padRight(630).padBottom(5);
        guiTable.row();
        guiTable.add(speedText).expandX().padRight(600).width(180).padBottom(420);
        //guiTable.setDebug(true);
        guiStage.addActor(guiTable);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(66.0f/255.0f,66.0f/255.0f,231.0f/255.0f,1);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameStage.act(delta);
        gameStage.draw();

        guiStage.act(delta);
        guiStage.draw();

        scoreText.setText("Score:" + score.getScore());
        speedText.setText("Speed:" + Math.abs(ball.xSpeed));

        // куроср уезжает относительно платформы, если она упирается в край
        if (Gdx.input.getX() > WIDTH - paddle.width/2) {
            Gdx.input.setCursorPosition(WIDTH - paddle.width/2, Gdx.input.getY());
        } else if (Gdx.input.getX() < paddle.width/2) {
            Gdx.input.setCursorPosition(paddle.width/2, Gdx.input.getY());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
        }

        renderer.setProjectionMatrix(gameCamera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        ball.draw(renderer);
        if(ball.update()) {
            if(ball.checkCollision(paddle) && paddle.width > 60) {
                paddle.width = paddle.width - paddle.width / blocks.size();
            }
            //ball.draw(shape);
        } else {
            game.setScreen(new GameOverScreen(game));
            dispose();
        }

        paddle.draw(renderer);
        paddle.update();

        for (Block block : blocks) {
            block.draw(renderer);
            if(ball.checkCollision(block)) {
                block.destroyed = true;
                ball.xSpeed += ball.xSpeed / (blocks.size() + 65);
                ball.ySpeed += ball.ySpeed / (blocks.size() + 65);
            }
        }

        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (b.destroyed) {
                blocks.remove(b);
                score.scoreIncrease();
                // we need to decrement i when a ball gets removed, otherwise we skip a ball
                i--;
                if(blocks.isEmpty()) {
                    game.setScreen(new WinScreen(game));
                    dispose();
                }
            }
        }

        renderer.end();

    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
        gameCamera.position.x += -(gameCamera.viewportWidth - VP_WIDTH)/2;
        gameCamera.position.y += -(gameCamera.viewportHeight - VP_HEIGHT)/2;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        //Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        gameStage.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE) {
            game.dispose();
        }
        return false;
    }
}

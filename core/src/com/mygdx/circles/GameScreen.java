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

import java.util.ArrayList;

import static com.mygdx.circles.Circles.HEIGHT;
import static com.mygdx.circles.Circles.WIDTH;

public class GameScreen implements Screen {
    final Circles game;
    private Stage stage;

    private Viewport viewport;
    private ShapeRenderer shape;
    private TextField scoreText;
    private TextField speedText;
    private Ball ball;
    private Paddle paddle;
    private Score score;
    private Button testButton;
    private ArrayList<Block> blocks = new ArrayList<>();


    public GameScreen(final Circles game) {
        this.game = game;

        viewport = new FitViewport(WIDTH, HEIGHT);
        stage = new Stage(viewport);

        Gdx.input.setInputProcessor(stage);

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        //Skin mySkin = new Skin(Gdx.files.internal("comic/skin/comic-ui.json"));

        /*Window window = new Window("Window", mySkin);
        window.setSize(800, 480);
        stage.addActor(window);*/

        Table rootTable = new Table(mySkin);
        rootTable.background("blue");
        rootTable.setSize(800, 480);
        stage.addActor(rootTable);

        scoreText = new TextField("Score:", mySkin, "nobg");
        scoreText.setSize(150, 50);
        scoreText.setPosition(5, Gdx.graphics.getHeight() - 35);
        scoreText.scaleBy(0.5f);
        stage.addActor(scoreText);

        speedText = new TextField("Speed:", mySkin, "nobg");
        speedText.setSize(200, 50);
        speedText.setPosition(5, Gdx.graphics.getHeight() - 65);
        speedText.scaleBy(0.5f);
        stage.addActor(speedText);

        // без этой кнопки графика отображается криво. возможно баг стиля
        testButton = new TextButton(" ", mySkin);
        testButton.setSize(0, 0);
        testButton.setPosition(-10, -10);
        testButton.setTransform(true);
        testButton.scaleBy(0.1f);
        stage.addActor(testButton);

        shape = new ShapeRenderer();
        ball = new Ball(Gdx.graphics.getWidth()/2, 40, 2.5, 2.5, 10);
        paddle = new Paddle(0, 15, 180, 20);
        score = new Score();

        //add blocks to screen
        int blockWidth = 62;
        int blockHeight = 20;

        for(int y = Gdx.graphics.getHeight()/2 - 30; y < Gdx.graphics.getHeight() - 70; y += blockHeight + 10) {
            for(int x = 10; x < Gdx.graphics.getWidth() - 10; x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }

        TestActor actor = new TestActor(50,50,100,100);
        stage.addActor(actor);
        stage.setKeyboardFocus(actor);

        for (Block block : blocks) {
            stage.addActor(block);
        }

        /*Block block = new Block(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 150, 150);
        stage.addActor(block);*/
    }

    @Override
    public void render(float delta) {

        delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);

        stage.act(delta); //Gdx.graphics.getDeltaTime()
        stage.draw();

        scoreText.setText("Score:" + score.getScore());
        speedText.setText("Speed:" + ball.xSpeed);

        shape.begin(ShapeRenderer.ShapeType.Filled);

        if(ball.update()) {
            if(ball.checkCollision(paddle) && paddle.width > 60) {
                paddle.width = paddle.width - paddle.width / blocks.size();
            }
            //ball.draw(shape);
        } else {
            game.setScreen(new GameOverScreen(game));
            dispose();
        }

        paddle.update();
        //paddle.draw(shape);

        for (Block block : blocks) {
            if(ball.checkCollision(block)) {
                block.destroyed = true;
                ball.xSpeed += ball.xSpeed / (blocks.size() + 65);
                ball.ySpeed += ball.ySpeed / (blocks.size() + 65);
            }
        }

        shape.end();

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

    }

    @Override
    public void resize(int width, int height) {
        // use true here to center the camera
        // that's what you probably want in case of a UI
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {

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

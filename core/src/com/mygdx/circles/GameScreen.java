package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Screen {
    final Circles game;
    private Stage stage;
    //Color color = new Color(165, 165, 255, 1);

    OrthographicCamera camera;
    ShapeRenderer shape;

    TextField scoreText;
    TextField speedText;
    Ball ball;
    Paddle paddle;
    Score score;
    ArrayList<Block> blocks = new ArrayList<>();
    Random r = new Random();


    public GameScreen(final Circles game) {
        this.game = game;

        stage = new Stage(new FillViewport(game.SCREEN_WIDTH, game.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        /*Window window = new Window("Window", mySkin);
        window.setSize(800, 480);
        stage.addActor(window);*/

        Table rootTable = new Table(mySkin);
        rootTable.background("blue");
        rootTable.setSize(game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
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
    }
    
    @Override
    public void render(float delta) {

        ScreenUtils.clear(1, 0, 0, 1, true);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        scoreText.setText("Score:" + score.getScore());
        speedText.setText("Speed:" + ball.xSpeed);

        //game.font.draw(game.batch, "Score: " + score.getScore(), 0, Gdx.graphics.getHeight());
        //game.font.draw(game.batch, "Speed: " + ball.xSpeed, 0, Gdx.graphics.getHeight()-15);

        shape.begin(ShapeRenderer.ShapeType.Filled);

        if(ball.update()) {
            if(ball.checkCollision(paddle) && paddle.width > 60) {
                paddle.width = paddle.width - paddle.width / blocks.size();
            }
            ball.draw(shape);
        } else {
            game.setScreen(new GameOverScreen(game));
            dispose();
        }

        paddle.update();
        paddle.draw(shape);

        for (Block block : blocks) {
            block.draw(shape);
            if(ball.checkCollision(block)) {
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
        shape.end();
    }

    @Override
    public void resize(int width, int height) {
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

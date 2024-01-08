package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen implements Screen {
    final Circles game;

    OrthographicCamera camera;
    ShapeRenderer shape;
    Ball ball;
    Paddle paddle;
    Score score;
    ArrayList<Block> blocks = new ArrayList<>();
    Random r = new Random();


    public GameScreen(final Circles game) {
        this.game = game;

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        shape = new ShapeRenderer();
        ball = new Ball(Gdx.graphics.getWidth()/2, 40, 2.5, 2.5, 10);
        paddle = new Paddle(0, 15, 180, 10);
        score = new Score();

        //add blocks to screen
        int blockWidth = 62;
        int blockHeight = 20;
        for(int y = Gdx.graphics.getHeight()/2 - 30; y < Gdx.graphics.getHeight() - 30; y += blockHeight + 10) {
            for(int x = 10; x < Gdx.graphics.getWidth() - 10; x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.draw(game.batch, "Score: " + score.getScore(), 0, Gdx.graphics.getHeight());
        game.font.draw(game.batch, "Speed: " + ball.xSpeed, 0, Gdx.graphics.getHeight()-15);
        game.batch.end();

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

package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import java.util.ArrayList;

import static com.mygdx.circles.Circles.HEIGHT;
import static com.mygdx.circles.Circles.WIDTH;

public class GameMechanics extends Group {
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Block> blocks = new ArrayList<>();
    private Circles game;
    private TextField scoreText;
    private TextField speedText;
    private Score score;
    private Table rootTable;
    private Button button;
    private BackgroundActor backgroundActor;

    public GameMechanics(Circles game) {
        this.game = game;

        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        //setBounds(0, 0, WIDTH, HEIGHT);

        int blockWidth = 62;
        int blockHeight = 20;

        backgroundActor = new BackgroundActor(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        score = new Score();
        paddle = new Paddle(Gdx.graphics.getWidth() / 2, 10, 180, 20);
        ball = new Ball(paddle.x, 45, 3, 3, 15);

        scoreText = new TextField("Score:", mySkin, "nobg");
        scoreText.setSize(150, 50);
        scoreText.setPosition(5, Gdx.graphics.getHeight() - 35);
        scoreText.scaleBy(0.5f);

        speedText = new TextField("Speed:", mySkin, "nobg");
        speedText.setSize(200, 50);
        speedText.setPosition(5, Gdx.graphics.getHeight() - 65);
        speedText.scaleBy(0.5f);

        for(int y = Circles.HEIGHT/2 - 30; y < Circles.HEIGHT - 70; y += blockHeight + 10) {
            for(int x = 10; x < WIDTH - 10; x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }

        //this.add(scoreText).expandX().left();
        //this.row();
        //this.add(speedText).expandX().left().padBottom(440);
        //this.row();
        //this.setFillParent(true);
        //this.setDebug(true);

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        addActor(backgroundActor);

        for (Block block : blocks) {
            //addActor(block);
        }

        //addActor(scoreText);
        //addActor(speedText);

        //addActor(paddle);
        paddle.update();

        //addActor(ball);
        if(ball.update()) {
            if(ball.checkCollision(paddle) && paddle.width > 90) {
                paddle.width = paddle.width - paddle.width / blocks.size();
            }
            //ball.draw(shape);
        } else {
            // здесь надо сделать переход на экран гейм овер
            game.setScreen(new GameOverScreen(game));
        }

        for (Block block : blocks) {
            if(ball.checkCollision(block)) {
                block.destroyed = true;
                ball.xSpeed += ball.xSpeed / (blocks.size() + 65);
                ball.ySpeed += ball.ySpeed / (blocks.size() + 65);
            }
        }

        scoreText.setText("Score:" + score.getScore());
        speedText.setText("Speed:" + ball.xSpeed);

        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (b.destroyed) {
                blocks.remove(b);
                //removeActor(b);
                score.scoreIncrease();
                // we need to decrement i when a ball gets removed, otherwise we skip a ball
                i--;
                if(blocks.isEmpty()) {
                    game.setScreen(new WinScreen(game));
                    //dispose();
                }
            }
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(Color.GRAY);
        super.draw(batch, parentAlpha);
    }




}

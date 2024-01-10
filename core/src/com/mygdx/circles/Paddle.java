package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Paddle extends Actor {
    int x, y, width, height;

    private ShapeRenderer shape;

    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        shape = new ShapeRenderer();
        setBounds(getX(), getY(), getWidth(), getHeight());

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        shape.setColor(165/255.0f,165/255.0f,1.0f,1.0f);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.rect(this.x, this.y, this.width, this.height);
        shape.end();
    }

    public void update() {
        x = Gdx.input.getX() - width / 2; // this returns the x position of the cursor in pixels
        //y = (Gdx.input.getY() * -1) + Gdx.graphics.getHeight();

        if (x < 0) {
            x = 0;
        }

        if (x > Circles.WIDTH - width) {
            x = Circles.WIDTH - width;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //sprite.draw(batch);
        batch.setColor(Color.GRAY);
        super.draw(batch, parentAlpha);
    }
}

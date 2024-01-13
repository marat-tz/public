package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Paddle {
    int x, y, width, height;


    public Paddle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

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

    public void draw(ShapeRenderer shape) {
        shape.setColor(165/255.0f,165/255.0f,1.0f,1.0f);
        shape.rect(this.x, this.y, this.width, this.height);
    }
}

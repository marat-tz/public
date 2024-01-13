package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.awt.*;

public class Block {
    int x, y, width, height;
    boolean destroyed;


    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(165 / 255.0f, 165 / 255.0f, 1.0f, 1.0f);
        shape.rect(this.x, this.y, this.width, this.height);

    }
}

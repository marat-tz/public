package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.awt.*;

public class Block extends Actor {
    int x, y, width, height;
    boolean destroyed;

    private ShapeRenderer shape;

    public Block(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        shape = new ShapeRenderer();
        setBounds(getX(), getY(), getWidth(), getHeight());
        setTouchable(Touchable.disabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //sprite.draw(batch);
        batch.setColor(Color.GRAY);
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        shape.setColor(165/255.0f,165/255.0f,1.0f,1.0f);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.rect(this.x, this.y, this.width, this.height);
        shape.end();
    }
}

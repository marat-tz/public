package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class BackgroundActor extends Table {

    float x, y, width, height;

    //Sprite sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
    ShapeRenderer shape = new ShapeRenderer();

    public BackgroundActor(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    protected void positionChanged() {
        //sprite.setPosition(getX(), getY());
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(Color.GRAY);
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        shape.setColor(66.0f/255.0f,66.0f/255.0f,1.0f,1);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.rect(this.x, this.y, this.width, this.height);
        shape.end();
    }
}

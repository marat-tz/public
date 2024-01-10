package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class TestActor extends Actor {

    float x, y, width, height;

    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
    ShapeRenderer shape = new ShapeRenderer();

    public TestActor(float x, float y, float width, float height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setBounds(getX(), getY(), getWidth(), getHeight());
        setTouchable(Touchable.enabled);

        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.RIGHT) {
                    MoveByAction mba = new MoveByAction();
                    mba.setAmount(1.0f, 0f);
                    mba.setDuration(1.0f); //время, которое актор будет двигаться

                    TestActor.this.addAction(mba);
                }
                return true;
            }
        });
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(), getY());
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        shape.setColor(1,1,1,1);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.rect(this.x, this.y, this.width, this.height);
        shape.end();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}

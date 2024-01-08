package com.mygdx.circles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class Ball {
    int x;
    int y;
    double xSpeed;
    double ySpeed;
    int size;
    Color color = Color.WHITE;

    public Ball(int x, int y, double xSpeed, double ySpeed, int size) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.size = size;
    }

    public boolean update() {
        this.x += xSpeed;
        this.y += ySpeed;

        if (this.x - this.size <= 0) {
            this.x = this.size;
            xSpeed = -xSpeed;
            return true;
        } else if (this.x + this.size > Gdx.graphics.getWidth()) {
            this.x = Gdx.graphics.getWidth() - this.size;
            xSpeed = -xSpeed;
            return true;
        }

        if (this.y + this.size > Gdx.graphics.getHeight()) {
            ySpeed = -ySpeed;
            return true;
        }

        if (this.y < 0) {
                return false;
        }

        return true;
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        shape.circle(x, y, size);
    }


    public boolean checkCollision(Paddle paddle) {
        if((paddle.x < this.x + this.size && paddle.x + paddle.width > this.x - this.size)
                && (paddle.y < this.y + this.size && paddle.y + paddle.height > this.y - this.size)) {

            // если шар пытается пролезть слева и его край находится левее центра платформы
            if (this.x + this.size > paddle.x
                    && this.x + this.size < paddle.x + paddle.width / 2
                    && this.y < paddle.y + paddle.height
                    && this.y > paddle.y) {
                this.x = paddle.x - this.size;
                this.xSpeed = -xSpeed;
                return true;

            // если шар пытается пролезть справа
            } else if (this.x - this.size < paddle.x + paddle.width
                    && this.x - this.size > paddle.x + paddle.width / 2
                    && this.y < paddle.y + paddle.height
                    && this.y > paddle.y) {
                this.x = paddle.x + paddle.width + this.size;
                this.xSpeed = -xSpeed;
                return true;

            // сверху
            } else if(this.y - this.size <= paddle.y + paddle.height
                    && this.y - this.size > paddle.y) {
                //color = Color.GREEN;
                this.y = this.size + paddle.y + paddle.height;
                this.ySpeed = -ySpeed;
                return true;
            }

            else if(this.y + this.size >= paddle.y
                    && this.y + this.size < paddle.y + paddle.height) {
                //color = Color.GREEN;
                this.y = paddle.y - this.size;
                this.ySpeed = -ySpeed;
                return true;
            }
        }

        return false;
    }

    public boolean checkCollision(Block block) {
        if((block.x < this.x + this.size && block.x + block.width > this.x - this.size)
                && (block.y < this.y + this.size && block.y + block.height > this.y - this.size)) {

            // если шар пытается пролезть слева и его край находится левее центра блока
            if (this.x + this.size > block.x
                    && this.x + this.size < block.x + block.width / 2
                    && this.y < block.y + block.height
                    && this.y > block.y) {
                this.x = block.x - this.size;
                this.xSpeed = -xSpeed;
                block.destroyed = true;
                return true;

                // если шар пытается пролезть справа
            } else if (this.x - this.size < block.x + block.width
                    && this.x - this.size > block.x + block.width / 2
                    && this.y < block.y + block.height
                    && this.y > block.y) {
                this.x = block.x + block.width + this.size;
                this.xSpeed = -xSpeed;
                block.destroyed = true;
                return true;

                // сверху
            } else if(this.y - this.size <= block.y + block.height
                    && this.y - this.size > block.y) {
                //color = Color.GREEN;
                this.y = this.size + block.y + block.height;
                this.ySpeed = -ySpeed;
                block.destroyed = true;
                return true;
            }

            else if(this.y + this.size >= block.y
                    && this.y + this.size < block.y + block.height) {
                //color = Color.GREEN;
                this.y = block.y - this.size;
                this.ySpeed = -ySpeed;
                block.destroyed = true;
                return true;
            }
        }
        return false;
    }
}

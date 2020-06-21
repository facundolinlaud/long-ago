package com.facundolinlaud.supergame.utils.shape;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.model.status.Direction;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public abstract class Shape {
    protected float x;
    protected float y;

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void traslate(Direction direction, float offset) {
        switch(direction){
            case UP:
                this.y += offset;
                break;
            case DOWN:
                this.y -= offset;
                break;
            case RIGHT:
                this.x += offset;
                break;
            case LEFT:
                this.x -= offset;
                break;
        }
    }

    public abstract void setPosition(Vector2 position);

    public abstract boolean contains(float x, float y);
}

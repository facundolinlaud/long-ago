package com.facundolinlaud.supergame.utils.shape;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.model.status.Direction;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public abstract class Shape {
    private float x;
    private float y;

    public void setPosition(Vector2 position) {
        this.x = position.x;
        this.y = position.y;
    }

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

    public abstract boolean contains(float x, float y);
}

package com.facundolinlaud.supergame.utils.shape;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class Rectangle extends Shape {
    private float width;
    private float height;

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void setPosition(Vector2 position) {
        setX(position.x - width / 2);
        setY(position.y - height / 2);
    }

    @Override
    public boolean contains(float x, float y) {
        return getX() <= x && x <= getX() + width && getY() <= y && y <= getY() + height;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(x, y, width, height);
    }
}

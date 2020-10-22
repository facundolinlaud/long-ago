package com.facundolinlaud.supergame.utils.shape;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class Circle extends Shape {
    public static final int CIRCLE_SEGMENTS = 30;
    private float radius;

    public Circle() {}

    public Circle(float radius, Vector2 position) {
        this.radius = radius;
        setPosition(position);
    }

    @Override
    public void setPosition(Vector2 position) {
        setX(position.x);
        setY(position.y);
    }

    @Override
    public boolean contains(float x2, float y2) {
        Vector2 center = new Vector2(x, y);
        Vector2 point = new Vector2(x2, y2);

        float distance = center.dst(point);

        return distance <= radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(x, y, radius, CIRCLE_SEGMENTS);
    }
}

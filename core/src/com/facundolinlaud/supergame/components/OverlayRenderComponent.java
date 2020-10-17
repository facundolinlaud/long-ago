package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.utils.shape.Shape;

public class OverlayRenderComponent implements Component {
    private ShapeRenderer.ShapeType shapeType;
    private Shape shape;
    private Color color;

    public OverlayRenderComponent(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.shapeType = ShapeRenderer.ShapeType.Filled;
    }

    public void setPosition(Vector2 position) {
        shape.setPosition(position);
    }

    public Shape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public ShapeRenderer.ShapeType getShapeType() {
        return shapeType;
    }
}

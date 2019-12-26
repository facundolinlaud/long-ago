package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class RenderBoundsComponent implements Component {
    private Rectangle rectangle;

    public RenderBoundsComponent(int x, int y, int width, int height){
        this.rectangle = new Rectangle(x, y, width, height);
    }

    public float getX() {
        return rectangle.getX();
    }

    public float getY() {
        return rectangle.getY();
    }

    public float getWidth() {
        return rectangle.getWidth();
    }

    public float getHeight() {
        return rectangle.getHeight();
    }
}

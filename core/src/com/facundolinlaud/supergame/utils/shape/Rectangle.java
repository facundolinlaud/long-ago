package com.facundolinlaud.supergame.utils.shape;

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
    public boolean contains(float x, float y) {
        return getX() <= x && getX() + width >= x && getY() <= y && getY() + height <= y;
    }
}

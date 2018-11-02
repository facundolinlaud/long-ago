package com.facundolinlaud.supergame.utils;

public class Position {
    public float x;
    public float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public static Position toScreenPosition(Position worldPosition) {
        return new Position(Dimensions.toPixels(worldPosition.x), Dimensions.toPixels(worldPosition.y));
    }

    public static Position toScreenPosition(float x, float y){
        return new Position(Dimensions.toPixels(x), Dimensions.toPixels(y));
    }

    @Override
    public String toString() {
        return "(" +
                "x=" + x +
                ", y=" + y +
                ')';
    }
}

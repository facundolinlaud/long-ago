package com.facundolinlaud.supergame.utils;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(float x, float y){
        this.x = (int) x;
        this.x = (int) y;
    }

    public static Point createScreenPointFromWorldPosition(Position position){
        return new Point(Dimensions.toPixels(position.getX()), Dimensions.toPixels(position.getY()));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getXFloat() {
        return (float) x;
    }

    public float getYFloat() {
        return (float) y;
    }
}

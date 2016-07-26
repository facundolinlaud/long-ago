package com.facundolinlaud.supergame.refactor.model;

/**
 * Created by facundo on 7/5/16.
 */
public class SubAnimationModel {
    private int x, y;
    private int length;

    public SubAnimationModel() {}

    public SubAnimationModel(int x, int y, int length) {
        this.x = x;
        this.y = y;
        this.length = length;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "SubAnimationModel{" +
                "x=" + x +
                ", y=" + y +
                ", length=" + length +
                '}';
    }
}

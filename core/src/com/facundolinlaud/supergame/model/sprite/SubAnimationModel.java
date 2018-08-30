package com.facundolinlaud.supergame.model.sprite;

import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by facundo on 7/5/16.
 */
public class SubAnimationModel {
    private int x, y;
    private int length;
    private Animation.PlayMode playMode;

    public SubAnimationModel() {}

    public SubAnimationModel(int x, int y, int length, String playMode) {
        this.x = x;
        this.y = y;
        this.length = length;
        setPlayMode(playMode);
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLength() {
        return length;
    }

    public Animation.PlayMode getPlayMode() {
        return playMode;
    }

    public void setPlayMode(String playMode) {
        this.playMode = Animation.PlayMode.valueOf(playMode);
    }
}

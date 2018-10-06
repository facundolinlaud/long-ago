package com.facundolinlaud.supergame.model.light;

import com.sun.istack.internal.Nullable;

public class LightModel {
    private float distance;
    private LightType type;
    @Nullable private float x;
    @Nullable private float y;
    @Nullable private float duration;

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

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public LightType getType() {
        return type;
    }

    public void setType(LightType type) {
        this.type = type;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}

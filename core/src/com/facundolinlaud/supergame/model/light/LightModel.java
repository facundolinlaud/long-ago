package com.facundolinlaud.supergame.model.light;

public class LightModel {
    private float distance;
    private LightType type;
    private float x;
    private float y;
    private float duration;

    public LightModel() {}

    public LightModel(float distance, float duration, LightType lightType) {
        this.distance = distance;
        this.duration = duration;
        this.type = lightType;
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

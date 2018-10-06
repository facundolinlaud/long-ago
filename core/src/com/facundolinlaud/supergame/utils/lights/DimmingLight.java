package com.facundolinlaud.supergame.utils.lights;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class DimmingLight extends FlickeringLight {
    private float originalDuration;
    private float distanceOnCreation;

    public DimmingLight(RayHandler rayHandler, int rays, Color color, float distance, float x, float y, float duration) {
        super(rayHandler, rays, color, distance, x, y);
        this.originalDuration = duration;
        this.distanceOnCreation = distance;
    }

    @Override
    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        float originalDistance = getOriginalDistance();
        float newDistance = originalDistance - delta * distanceOnCreation / originalDuration;

        if(newDistance < 0) {
            this.remove();
        }else{
            super.setOriginalDistance(newDistance);
            super.update();
        }
    }
}

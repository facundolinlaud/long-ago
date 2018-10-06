package com.facundolinlaud.supergame.utils.lights;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import java.util.Random;

public class FlickeringLight extends PointLight {
    private static float minOffset = -0.2f;
    private static float maxOffset = 0.5f;
    private static float DEFAULT_FPS = 1/24f;

    private float originalDistance;
    private Random random;

    private float fps = DEFAULT_FPS;

    public FlickeringLight(RayHandler rayHandler, int rays, Color color, float distance, float x, float y) {
        super(rayHandler, rays, color, distance, x, y);
        this.originalDistance = distance;
        this.random = new Random();
    }

    @Override
    public void update() {
        this.fps -= Gdx.graphics.getDeltaTime();

        if(this.fps > 0)
            return;

        this.fps = DEFAULT_FPS;
        float flickerDistance = originalDistance + getRandomOffset();
        super.setDistance(flickerDistance);

        super.update();
    }

    private float getRandomOffset(){
        return random.nextFloat() * (maxOffset - minOffset) + minOffset;
    }

    protected void setOriginalDistance(float originalDistance){
        this.originalDistance = originalDistance;
    }

    protected float getOriginalDistance(){
        return this.originalDistance;
    }
}

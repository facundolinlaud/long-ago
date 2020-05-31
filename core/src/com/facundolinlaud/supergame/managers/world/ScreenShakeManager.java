package com.facundolinlaud.supergame.managers.world;

import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class ScreenShakeManager {
    private float timeLeft = 0;
    private float currentTime = 0;
    private float power = 0;
    private float currentPower = 0;
    private Random random;
    private Vector3 position;

    public ScreenShakeManager() {
        this.position = new Vector3();
    }

    public void shake(float power, float duration) {
        this.power = power;
        random = new Random();
        timeLeft = duration;
        currentTime = 0;
    }

    public Vector3 tick(float delta) {
        if (currentTime <= timeLeft) {
            currentPower = power * ((timeLeft - currentTime) / timeLeft);

            position.x = (random.nextFloat() - 0.5f) * 2 * currentPower;
            position.y = (random.nextFloat() - 0.5f) * 2 * currentPower;

            currentTime += delta;
        } else {
            timeLeft = 0;
        }

        return position;
    }


    public float getShakingTimeLeft() {
        return timeLeft;
    }

    public Vector3 getPosition() {
        return position;
    }
}

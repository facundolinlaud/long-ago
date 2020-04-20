package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.managers.world.CameraManager;
import com.facundolinlaud.supergame.skills.SkillBlackboard;

/**
 * Pops: two float-values corresponding to the x and y agent position values respectively
 * Pushes: nothing
 */
public class ShakeScreenTask extends Task<SkillBlackboard> {
    private float power;
    private float duration;

    public ShakeScreenTask(float power, float duration) {
        this.power = power;
        this.duration = duration;
    }

    @Override
    public void activate() {
        System.out.println("Activating ShakeScreen");

        float y = getBlackboard().popFloat();
        float x = getBlackboard().popFloat();
        Vector2 position = new Vector2(x, y);

        CameraManager cameraManager = getBlackboard().getCameraManager();
        cameraManager.shake(power, duration, position);

        completed();
    }
}

package com.facundolinlaud.supergame.behaviortree.leaves;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.managers.world.CameraManager;

/**
 * Pops: two float-values corresponding to the x and y agent position values respectively
 * Pushes: nothing
 */
public class ScreenShakeTask extends Task<Blackboard> {
    private CameraManager cameraManager;

    private float power;
    private float duration;

    public ScreenShakeTask(float power, float duration) {
        this.power = power;
        this.duration = duration;
    }

    @Override
    protected void onBlackboardAvailable(Blackboard blackboard) {
        cameraManager = blackboard.getCameraManager();
    }

    @Override
    public void activate() {
        float y = stack.pop().getFloat();
        float x = stack.pop().getFloat();
        Vector2 position = new Vector2(x, y);

        cameraManager.shake(power, duration, position);

        completed();
    }
}

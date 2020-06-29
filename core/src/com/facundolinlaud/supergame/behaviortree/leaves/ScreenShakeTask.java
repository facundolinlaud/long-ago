package com.facundolinlaud.supergame.behaviortree.leaves;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.LeafTask;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.managers.world.CameraManager;

/**
 * Pops: a position-value corresponding to the screen shake position
 * Pushes: nothing
 */
public class ScreenShakeTask extends LeafTask<Blackboard> {
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
        Vector2 position = stack.pop().getPosition();
        cameraManager.shake(power, duration, position);

        completed();
    }
}

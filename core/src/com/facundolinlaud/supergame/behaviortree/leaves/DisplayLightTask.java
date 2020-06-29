package com.facundolinlaud.supergame.behaviortree.leaves;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.LeafTask;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.model.light.LightModel;
import com.facundolinlaud.supergame.model.light.LightType;

/**
 * Pops: a position-value corresponding to the light position
 * Pushes: nothing
 */
public class DisplayLightTask extends LeafTask<Blackboard> {
    private LightsManager lightsManager;

    private LightType lightType;
    private float distance;
    private float duration;

    public DisplayLightTask(LightType lightType, float distance, float duration) {
        this.lightType = lightType;
        this.distance = distance;
        this.duration = duration;
    }

    @Override
    protected void onBlackboardAvailable(Blackboard blackboard) {
        lightsManager = blackboard.getLightsManager();
    }

    @Override
    public void activate() {
        Vector2 position = stack.pop().getPosition();
        LightModel lightModel = new LightModel(distance, duration, lightType);
        lightsManager.create(lightModel, position.x, position.y);
        completed();
    }
}

package com.facundolinlaud.supergame.skills.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.managers.world.LightsManager;
import com.facundolinlaud.supergame.model.light.LightModel;
import com.facundolinlaud.supergame.model.light.LightType;
import com.facundolinlaud.supergame.skills.SkillBlackboard;

/**
 * Pops: two float-values corresponding to the x and y agent position values respectively
 * Pushes: nothing
 */
public class DisplayLightTask extends Task<SkillBlackboard> {
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
    protected void onBlackboardAvailable(SkillBlackboard blackboard) {
        lightsManager = blackboard.getLightsManager();
    }

    @Override
    public void activate() {
        float y = stack.pop().getFloat();
        float x = stack.pop().getFloat();

        LightModel lightModel = new LightModel(distance, duration, lightType);
        lightsManager.create(lightModel, x, y);
        completed();
    }
}

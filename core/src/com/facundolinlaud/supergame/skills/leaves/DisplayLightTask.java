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
    private LightType lightType;
    private float distance;
    private float duration;

    public DisplayLightTask(LightType lightType, float distance, float duration) {
        this.lightType = lightType;
        this.distance = distance;
        this.duration = duration;
    }

    @Override
    public void activate() {
        System.out.println("Activating DisplayLight");

        float y = getBlackboard().popFloat();
        float x = getBlackboard().popFloat();

        LightsManager lightsManager = getBlackboard().getLightsManager();
        LightModel lightModel = new LightModel();

        // refactor
        lightModel.setDistance(distance);
        lightModel.setDuration(duration);
        lightModel.setType(lightType);
        lightsManager.create(lightModel, x, y);
        completed();
    }

    @Override
    public void completed() {
        System.out.println("Completing DisplayLight");
        super.completed();
    }
}

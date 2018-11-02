package com.facundolinlaud.supergame.components.ai;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.ai.BehaviourType;

public class AIComponent implements Component {
    private float viewDistance;

    public AIComponent(float viewDistance) {
        this.viewDistance = viewDistance;
    }

    public float getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(float viewDistance) {
        this.viewDistance = viewDistance;
    }
}

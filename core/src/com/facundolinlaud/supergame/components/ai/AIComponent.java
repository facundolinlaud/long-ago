package com.facundolinlaud.supergame.components.ai;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.ai.BehaviourType;

public class AIComponent implements Component {
    private BehaviourType behaviourType;

    public AIComponent(BehaviourType behaviourType) {
        this.behaviourType = behaviourType;
    }
    public BehaviourType getBehaviourType() {
        return this.behaviourType;
    }
}

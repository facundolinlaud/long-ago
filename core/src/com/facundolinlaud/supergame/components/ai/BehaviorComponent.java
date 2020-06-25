package com.facundolinlaud.supergame.components.ai;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.ai.behavior.BehaviorTask;

public class BehaviorComponent implements Component {
    private BehaviorTask behaviorTask;

    public BehaviorComponent(BehaviorTask behaviorTask) {
        this.behaviorTask = behaviorTask;
    }

    public BehaviorTask getBehaviorTask() {
        return behaviorTask;
    }
}

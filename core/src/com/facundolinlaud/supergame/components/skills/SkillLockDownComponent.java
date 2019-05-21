package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;

public class SkillLockDownComponent implements Component {
    public float timeLeft;

    public SkillLockDownComponent(float timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void tick(float deltaTime){
        timeLeft -= deltaTime;
    }

    public boolean isOver(){
        return timeLeft <= 0;
    }
}

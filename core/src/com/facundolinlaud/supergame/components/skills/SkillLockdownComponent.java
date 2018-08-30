package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;

public class SkillLockdownComponent implements Component {
    public float timeLeft;

    public SkillLockdownComponent(float timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void tick(float deltaTime){
        timeLeft -= deltaTime;
    }

    public boolean isOver(){
        return timeLeft <= 0;
    }
}

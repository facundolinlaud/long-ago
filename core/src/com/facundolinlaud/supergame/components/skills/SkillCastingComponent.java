package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.skill.Skill;

public class SkillCastingComponent implements Component {
    public Skill skill;
    public float timeToCast;

    public SkillCastingComponent(Skill skill) {
        this.skill = skill;
        this.timeToCast = skill.getCastTime();
    }

    public void tick(float delta){
        this.timeToCast -= delta;
    }

    public boolean hasCastingTimeEnded(){
        return this.timeToCast <= 0;
    }

    public boolean hasJustStarted(){
        return this.timeToCast >= skill.getCastTime();
    }
}

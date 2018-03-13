package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.skill.BasicSkill;
import com.facundolinlaud.supergame.model.skill.SkillType;

public class SkillCastingComponent implements Component {
    public BasicSkill basicSkill;
    public SkillType skillType;
    public float timeToCast;

    public SkillCastingComponent(BasicSkill basicSkill, SkillType skillType) {
        this.basicSkill = basicSkill;
        this.skillType = skillType;
        this.timeToCast = basicSkill.getCastTime();
    }

    public void tick(float delta){
        this.timeToCast -= delta;
    }

    public boolean hasCastingTimeEnded(){
        return this.timeToCast <= 0;
    }

    public boolean hasJustStarted(){
        return this.timeToCast >= basicSkill.getCastTime();
    }
}

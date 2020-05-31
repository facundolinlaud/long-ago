package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.facundolinlaud.supergame.components.SkillsComponent;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.Iterator;
import java.util.Map;

public class SkillCoolDownSystem extends IteratingSystem {
    private ComponentMapper<SkillsComponent> sm = Mappers.skills;

    public SkillCoolDownSystem() {
        super(Family.all(SkillsComponent.class).get());
    }

    @Override
    protected void processEntity(Entity caster, float deltaTime) {
        SkillsComponent skillsComponent = sm.get(caster);
        Map<Skill, Float> skillsInCoolDown = skillsComponent.getSkillsInCooldown();

        Iterator<Map.Entry<Skill, Float>> iterator = skillsInCoolDown.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Skill, Float> entry = iterator.next();
            Skill s = entry.getKey();
            float coolDownLeft = entry.getValue();

            if(coolDownLeft > 0)
                skillsInCoolDown.replace(s, coolDownLeft - deltaTime);
            else
                iterator.remove();
        }
    }
}
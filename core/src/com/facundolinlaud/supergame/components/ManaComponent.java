package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.skill.Skill;

public class ManaComponent implements Component {
    private float totalMana;
    private float currentMana;

    public ManaComponent(float totalMana) {
        this.totalMana = totalMana;
        this.currentMana = totalMana;
    }

    public boolean canCast(int mana){
        return currentMana >= mana;
    }

    public boolean canCast(Skill skill){
        return currentMana >= skill.getManaConsumption();
    }

    public void cast(Skill skill){
        this.currentMana = Math.max(0, currentMana - skill.getManaConsumption());
    }

    public float getCurrentMana() {
        return currentMana;
    }

    public float getTotalMana() {
        return this.totalMana;
    }

    public void regenerate(float points){
        this.currentMana = Math.min(currentMana + points, totalMana);
    }
}

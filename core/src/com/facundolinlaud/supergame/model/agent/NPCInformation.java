package com.facundolinlaud.supergame.model.agent;

import com.facundolinlaud.supergame.model.ai.BehaviorType;

import java.util.List;

public class NPCInformation {
    private float viewDistance;
    private List<Integer> meleeSkills;
    private List<Integer> rangedSkills;
    private BehaviorType behaviorType;

    public float getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(float viewDistance) {
        this.viewDistance = viewDistance;
    }

    public List<Integer> getMeleeSkills() {
        return meleeSkills;
    }

    public void setMeleeSkills(List<Integer> meleeSkills) {
        this.meleeSkills = meleeSkills;
    }

    public List<Integer> getRangedSkills() {
        return rangedSkills;
    }

    public void setRangedSkills(List<Integer> rangedSkills) {
        this.rangedSkills = rangedSkills;
    }

    public BehaviorType getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(BehaviorType behaviorType) {
        this.behaviorType = behaviorType;
    }
}

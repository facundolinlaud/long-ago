package com.facundolinlaud.supergame.dto.agent;

import java.util.List;

public class CombatInformation {
    private List<String> skills;
    private int assignablePoints;
    private Attributes attributes;

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public int getAssignablePoints() {
        return assignablePoints;
    }

    public void setAssignablePoints(int assignablePoints) {
        this.assignablePoints = assignablePoints;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}

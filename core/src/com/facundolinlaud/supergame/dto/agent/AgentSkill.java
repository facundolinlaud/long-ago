package com.facundolinlaud.supergame.dto.agent;

public class AgentSkill {
    private int skillId;
    private int level;
    private float coolDownLeft;

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getCoolDownLeft() {
        return coolDownLeft;
    }

    public void setCoolDownLeft(float coolDownLeft) {
        this.coolDownLeft = coolDownLeft;
    }
}

package com.facundolinlaud.supergame.model.skill;

import com.facundolinlaud.supergame.dto.skills.SkillTaskDto;

public class Skill {
    private String id;
    private String name;
    private float cooldown;
    private String picturePath;
    private String description;
    private SkillType skillType; // to be removed eventually, only used by ai
    private float manaConsumption; // to be removed eventually, only used by ai
    private SkillTaskDto skillDto;
    private String disabledPicturePath;

    public Skill() {
    }

    public float getCooldown() {
        return cooldown;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillTaskDto getSkillDto() {
        return skillDto;
    }

    public void setSkillDto(SkillTaskDto skillDto) {
        this.skillDto = skillDto;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisabledPicturePath() {
        return disabledPicturePath;
    }

    public void setDisabledPicturePath(String disabledPicturePath) {
        this.disabledPicturePath = disabledPicturePath;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    public float getManaConsumption() {
        return manaConsumption;
    }

    public void setManaConsumption(float manaConsumption) {
        this.manaConsumption = manaConsumption;
    }
}

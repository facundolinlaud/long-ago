package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SkillsComponent implements Component {
    private List<Skill> skills;
    private Map<Skill, Float> skillsInCooldown;

    public SkillsComponent(List<Skill> skills) {
        this.skills = skills;
        this.skillsInCooldown = new HashMap();
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Skill> getMeleeSkills(){
        return skills.stream().filter(skill -> SkillType.NORMAL.equals(skill.getSkillType()))
                .collect(Collectors.toList());
    }

    public List<Skill> getRangedSkills(){
        return skills.stream().filter(skill -> !SkillType.NORMAL.equals(skill.getSkillType()))
                .collect(Collectors.toList());
    }

    public void add(Skill skill){
        this.skills.add(skill);
    }

    public List<Skill> getAvailableSkills(){
        return skills.stream().filter(skill -> !skillsInCooldown.containsKey(skill)).collect(Collectors.toList());
    }

    public boolean isCoolingDown(Skill skill){
        return skillsInCooldown.containsKey(skill);
    }

    public void startCoolDown(Skill skill){
        skillsInCooldown.put(skill, skill.getCooldown());
    }

    public boolean hasSkill(Skill skill){
        return skills.contains(skill);
    }

    public Map<Skill, Float> getSkillsInCooldown() {
        return skillsInCooldown;
    }

    public boolean canCast(Skill skill) {
        return this.getAvailableSkills().contains(skill);
    }
}

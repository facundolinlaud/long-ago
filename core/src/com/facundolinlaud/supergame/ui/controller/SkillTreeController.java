package com.facundolinlaud.supergame.ui.controller;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.facundolinlaud.supergame.components.SkillsComponent;
import com.facundolinlaud.supergame.factory.ModelFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.SkillTreeUI;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.List;
import java.util.Map;

import static com.facundolinlaud.supergame.utils.events.Messages.SKILLS_CHANGED;
import static com.facundolinlaud.supergame.utils.events.Messages.SKILL_UNLOCK_REQUEST;

public class SkillTreeController implements Telegraph {
    private ComponentMapper<SkillsComponent> sm = Mappers.skills;

    private SkillTreeUI skillTreeUI;
    private Entity player;

    public SkillTreeController(SkillTreeUI skillTreeUI, Entity player) {
        this.skillTreeUI = skillTreeUI;
        this.player = player;

        updateSkillTreeUI();
    }

    public void updateSkillTreeUI() {
        SkillsComponent skillsComponent = sm.get(player);
        Map<Integer, Skill> allSkills = ModelFactory.getSkillsModel();
        List<Skill> playerSkills = skillsComponent.getSkills();
        int assignablePoints = skillsComponent.getAssignablePoints();

        skillTreeUI.update(allSkills, playerSkills, assignablePoints);
    }


    @Override
    public boolean handleMessage(Telegram msg) {
        switch(msg.message){
            case SKILL_UNLOCK_REQUEST:
                onSkillUnlockRequest((Skill) msg.extraInfo);
                break;
            case SKILLS_CHANGED:
                this.updateSkillTreeUI();
                break;
        }

        return false;
    }

    private void onSkillUnlockRequest(Skill skill) {
        SkillsComponent skills = sm.get(player);
        List<Skill> allPlayerSkills = skills.getSkills();

        if(!allPlayerSkills.contains(skill) && skills.getAssignablePoints() > 0) {
            skills.consumeAssignablePoint();
            allPlayerSkills.add(skill);
        }
    }
}

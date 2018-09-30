package com.facundolinlaud.supergame.ui.view.skillsbar;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.facundolinlaud.supergame.model.skill.Skill;

import java.util.Map;

public class Skillbar extends Table {
    private static final int SIZE = 5;

    private Array<SkillbarSlot> slots;

    public Skillbar(Skin skin, Map<Integer, Skill> skills) {
        this.slots = new Array<>(false, SIZE);

        for(int i = 0; i < SIZE; i++){
            SkillbarSlot slot = new SkillbarSlot(skin);
            this.slots.add(slot);
            this.add(slot);

            if(skills.containsKey(i)){
                slot.setContent(skills.get(i));
            }
        }
    }

    public void beginCooldown(Skill skill){
        float cooldown = skill.getCooldown();
        for(SkillbarSlot s : slots){
            if(skill == s.getContent()){
                s.beginCooldown(cooldown);
            } else if(!s.isEmpty()){
                s.beginCooldown(0.5f);
            }
        }
    }
}

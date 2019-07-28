package com.facundolinlaud.supergame.ui.view.skilltree;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Tooltip;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.ItemToolTipContent;
import com.facundolinlaud.supergame.ui.view.cross.SkillSlot;

public class SkillTreeFramedSlot extends ImageButton {
    private SkillSlot skillSlot;
    private Tooltip<SkillToolTipContent> tooltip;
    private Skin skin;

    public SkillTreeFramedSlot(Skin skin, Skill skill, boolean disabled) {
        super(skin, "skill-slot");
        this.setBackground("gothic-slot-skill");
        this.skillSlot = new SkillSlot(skin);
        this.skin = skin;

        add(skillSlot).align(Align.center).size(32, 32);
        setContent(skill, disabled);
    }

    private void buildToolTip(Skill skill, Skin skin, boolean disabled) {
        if(this.tooltip != null)
            removeListener(this.tooltip);

        SkillToolTipContent content = new SkillToolTipContent(skill, skin, disabled);
        this.tooltip = new Tooltip(content);
        addListener(this.tooltip);
    }

    public void setContent(Skill skill, boolean disabled) {
        skillSlot.setContent(skill, disabled);
        buildToolTip(skill, skin, disabled);
    }

    public SkillSlot getSlot(){
        return this.skillSlot;
    }
}

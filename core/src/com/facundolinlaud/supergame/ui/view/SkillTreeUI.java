package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.skilltree.SkillTreeWindow;

import java.util.List;
import java.util.Map;

public class SkillTreeUI implements UI {
    private SkillTreeWindow window;

    public SkillTreeUI(Skin skin, DragAndDrop dragAndDrop) {
        this.window = new SkillTreeWindow(skin, dragAndDrop);
        this.window.setPosition(980, 0);
    }

    public void update(Map<Integer, Skill> allSkills, List<Skill> playerSkills, int assignablePoints){
        this.window.update(allSkills, playerSkills, assignablePoints);
    }

    @Override
    public Group get() {
        return this.window;
    }
}

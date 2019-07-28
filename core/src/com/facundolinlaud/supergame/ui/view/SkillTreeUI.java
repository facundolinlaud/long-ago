package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.skilltree.SkillTreeWindow;
import com.facundolinlaud.supergame.ui.view.utils.ToggleWindowListener;

import java.util.List;
import java.util.Map;

public class SkillTreeUI implements UI {
    private SkillTreeWindow skillTreeWindow;

    public SkillTreeUI(Stage stage, Skin skin, DragAndDrop dragAndDrop) {
        this.skillTreeWindow = new SkillTreeWindow(skin, dragAndDrop);

        stage.addActor(skillTreeWindow);
        stage.addListener(new ToggleWindowListener(skillTreeWindow, Input.Keys.K));
    }

    public void update(Map<Integer, Skill> allSkills, List<Skill> playerSkills, int assignablePoints){
        this.skillTreeWindow.update(allSkills, playerSkills, assignablePoints);
    }

    @Override
    public Group get() {
        return this.skillTreeWindow;
    }
}

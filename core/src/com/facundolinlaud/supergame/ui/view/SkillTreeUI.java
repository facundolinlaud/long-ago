package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.ui.view.skilltree.SkillTreeWindow;
import com.facundolinlaud.supergame.ui.view.utils.ToggleWindowListener;

public class SkillTreeUI implements UI {
    private SkillTreeWindow skillTreeWindow;

    public SkillTreeUI(Stage stage, Skin skin, DragAndDrop dragAndDrop, SkillsFactory skillsFactory) {
        this.skillTreeWindow = new SkillTreeWindow(skin, skillsFactory, dragAndDrop);

        stage.addActor(skillTreeWindow);
        stage.addListener(new ToggleWindowListener(skillTreeWindow, Input.Keys.K));
    }

    @Override
    public Group get() {
        return this.skillTreeWindow;
    }
}

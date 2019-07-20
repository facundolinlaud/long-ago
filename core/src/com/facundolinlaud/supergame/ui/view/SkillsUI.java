package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.ui.view.skills.SkillsWindow;
import com.facundolinlaud.supergame.ui.view.utils.ToggleWindowListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class SkillsUI implements UI {
    private SkillsWindow skillsWindow;

    public SkillsUI(Stage stage, Skin skin, DragAndDrop dragAndDrop, SkillsFactory skillsFactory) {
        this.skillsWindow = new SkillsWindow(skin, skillsFactory, dragAndDrop);

        stage.addActor(skillsWindow);
        stage.addListener(new ToggleWindowListener(skillsWindow, Input.Keys.K));
    }

    @Override
    public Group get() {
        return this.skillsWindow;
    }
}

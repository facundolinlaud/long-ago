package com.facundolinlaud.supergame.ui.view.overlay.skillsbar;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelCountdownActor extends Action {
    private float cooldown;
    private Label label;

    public LabelCountdownActor(float cooldown, Label label) {
        this.cooldown = cooldown;
        super.target = label;
        this.label = label;
    }

    @Override
    public boolean act(float delta) {
        if(cooldown <= 0){
            label.setText("");
            return true;
        }

        String cooldownText = String.format("%.1f", cooldown);
        this.label.setText(cooldownText);
        cooldown -= delta;

        return false;
    }
}

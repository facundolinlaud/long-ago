package com.facundolinlaud.supergame.ui.view.labeldamages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.utils.Position;

public class DamageLabel extends Label {
    public DamageLabel(String text, Skin skin, Position position) {
        super(text, skin);

        this.setColor(Color.FIREBRICK);
        this.setPosition(position.getX(), position.getY());
    }
}

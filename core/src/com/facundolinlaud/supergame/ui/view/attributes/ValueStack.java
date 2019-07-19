package com.facundolinlaud.supergame.ui.view.attributes;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 3/31/16.
 */
public class ValueStack extends Stack {
    private Label valueLabel;

    public ValueStack(Skin skin, int value) {
        this.valueLabel = new Label(String.valueOf(value), skin, Themes.Label.REGULAR_14);
        this.valueLabel.setAlignment(Align.center);

        add(new ImageButton(skin, Themes.ImageButton.SLOT));
        add(valueLabel);
    }

    public void setAttributeValue(int value){
        this.valueLabel.setText(String.valueOf(value));
    }
}

package com.facundolinlaud.supergame.ui.view.attributes;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 3/31/16.
 */
public class ValueLabel extends Label {
    public ValueLabel(int value, Skin skin) {
        super(String.valueOf(value), skin, Themes.DEFAULT.toString());
        setAlignment(Align.center);
    }

    public void setAttributeValue(int attributeValue) {
        setText(String.valueOf(attributeValue));
    }
}

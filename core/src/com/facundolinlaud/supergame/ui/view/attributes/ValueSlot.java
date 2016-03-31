package com.facundolinlaud.supergame.ui.view.attributes;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 3/31/16.
 */
public class ValueSlot extends ImageButton {
    public ValueSlot(Skin skin) {
        super(skin, Themes.SLOT.toString());
    }
}
